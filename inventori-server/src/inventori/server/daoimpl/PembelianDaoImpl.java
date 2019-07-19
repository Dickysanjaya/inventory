/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import dao.PembelianDao;
import entity.Gudang;
import entity.Pemasok;
import entity.PembelianDetail;
import entity.Pembelian;
import inventori.server.db.DatabaseUtilities;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayu
 */
public class PembelianDaoImpl extends UnicastRemoteObject implements PembelianDao{
    
    private Connection connection;
    private BarangDaoImpl barangDaoImpl;
    private GudangDaoImpl gudangDaoImpl;
    private PemasokDaoImpl pemasokDaoImpl;

    public PembelianDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
        barangDaoImpl = new BarangDaoImpl();
        gudangDaoImpl = new GudangDaoImpl();
        pemasokDaoImpl = new PemasokDaoImpl();
        
    }
    
    

    @Override
    public boolean insert(Pembelian pembelian) throws RemoteException {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into pembelian (no_beli, tgl, kd_pemasok, kd_gd, total) values(?,?,?,?,?)";
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            statement.setString(1, pembelian.getNo_beli());
            statement.setDate(2, new java.sql.Date(pembelian.getTgl().getTime()));
            statement.setString(3, pembelian.getPemasok().getKd_pemasok());
            statement.setString(4, pembelian.getGudang().getKd_gd());
            statement.setDouble(5, pembelian.getTotal());
            statement.executeUpdate();
            
            // proses insert ke barang transaki
            String TRANSAKSI_SQL="insert into pembelian_detail(no_beli,kd_brg,jumlah,harga_beli,harga_beli_total ) values"
                    +"(?, ?, ?, ?, ?)";
          
                String sqlupdate = "UPDATE barang set jumlah=+? where kd_brg=?";
                List<PembelianDetail> detailPembelian = pembelian.getDetailPembelian();
                
                for(PembelianDetail pembelianDetail: detailPembelian ){
                    statement=connection.prepareStatement(TRANSAKSI_SQL);
                    statement.setString(1, pembelianDetail.getNo_beli());
                    statement.setString(2, pembelianDetail.getBarang().getKd_brg());
                    statement.setDouble(3,pembelianDetail.getJumlah());
                    statement.setDouble(4, pembelianDetail.getHarga_beli());
                    statement.setDouble(5, pembelianDetail.getHarga_beli_total());
                    
                    statement.executeUpdate();
                }
      
            connection.commit();
            connection.setAutoCommit(true);
            valid=true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        }
        return valid;
    }

    @Override
    public List<Pembelian> getPembelian() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list = new ArrayList();
        String sql="SELECT * FROM pembelian";
        try {
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Pembelian p=new Pembelian();
                p.setNo_beli(rs.getString("no_beli"));
                p.setTgl(rs.getDate("tgl"));
                String kd_pemasok = rs.getString("kd_pemasok");
                Pemasok getPemasok = pemasokDaoImpl.getPemasok(kd_pemasok);
                p.setPemasok(getPemasok);
                String kd_gd = rs.getString("kd_gd");
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
                p.setDetailPembelian(getPembelianDetil(rs.getString("no_beli")));
                p.setTotal(rs.getDouble("total"));
                list.add(p);     
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
     public List<PembelianDetail> getPembelianDetil(String noBeli ) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list = new ArrayList();
        String sql="SELECT * FROM pembelian_detail where no_beli=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, noBeli);
            rs=statement.executeQuery();
            while(rs.next()){
                PembelianDetail p=new PembelianDetail();
                p.setNo_beli(rs.getString("no_beli"));
                p.setJumlah(rs.getDouble("jumlah"));
                p.setBarang(barangDaoImpl.getById(rs.getString("kd_brg")));
                p.setHarga_beli(rs.getDouble("harga_beli"));
                p.setHarga_beli_total(rs.getDouble("harga_beli_total"));
                
                list.add(p);     
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    @Override
    public List<Pembelian> getPembelian(Date tglMulai, Date tgAkhir) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list = new ArrayList();
        String sql="SELECT * FROM pembelian"
                +" where (pembelian.tgl>=?) and (pembelian.tgl<=?)";
        try {
            statement=connection.prepareStatement(sql);
            
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tgAkhir.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                Pembelian p=new Pembelian();
                p.setNo_beli(rs.getString("pembelian.no_beli"));
                p.setTgl(rs.getDate("pembelian.tgl"));
                String kd_pemasok = rs.getString("pembelian.kd_pemasok");
                Pemasok getPemasok = pemasokDaoImpl.getPemasok(kd_pemasok);
                p.setPemasok(getPemasok);
                String kd_gd = rs.getString("pembelian.kd_gd");
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
                p.setTotal(rs.getDouble("total"));
                p.setDetailPembelian(getPembelianDetil(p.getNo_beli()));
                list.add(p);     
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Pembelian getpPembelian(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        Pembelian p=null;
        String sql="SELECT * FROM pembelian where no_beli=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                p=new Pembelian();
                p.setNo_beli(rs.getString("no_beli"));
                p.setTgl(rs.getDate("tgl"));
                String kd_pemasok = rs.getString("kd_pemasok");
                Pemasok getPemasok = pemasokDaoImpl.getPemasok(kd_pemasok);
                p.setPemasok(getPemasok);
                String kd_gd = rs.getString("kd_gd");
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
                p.setDetailPembelian(getPembelianDetil(rs.getString("no_beli")));
                p.setTotal(rs.getDouble("total"));
                    
         }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}

   
