/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import dao.BarangDao;
import dao.PenjualanDao;
import entity.Gudang;
import entity.Pelanggan;
import entity.Penjualan;
import entity.PenjualanDetail;
import inventori.server.db.DatabaseUtilities;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayu
 */
public class PenjualanDaoImpl extends UnicastRemoteObject implements PenjualanDao{
    private Connection connection;
    private BarangDaoImpl barangDaoImpl;
    private GudangDaoImpl gudangDaoImpl;
    private PelangganDaoImpl pelangganDaoImpl;
    private BarangDao barangDao;
    

    public PenjualanDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
        barangDaoImpl = new BarangDaoImpl();
        gudangDaoImpl = new GudangDaoImpl();
        pelangganDaoImpl = new PelangganDaoImpl();
        barangDao=new BarangDaoImpl();
    }
    
    
    @Override
    public boolean insert(Penjualan penjualan) throws RemoteException {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "insert into penjualan (no_jual, tgl, kd_plg, kd_gd, total) values(?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            statement.setString(1, penjualan.getNo_jual());
            statement.setDate(2, new java.sql.Date(penjualan.getTgl().getTime()));
            statement.setString(3, penjualan.getPelanggan().getKd_plg());
            statement.setString(4, penjualan.getGudang().getKd_gd());
            statement.setDouble(5, penjualan.getTotal());
            statement.executeUpdate();
            
            // proses insert ke barang transaki
            String TRANSAKSI_SQL="insert into penjualan_detail(no_jual,kd_brg,jumlah,harga_jual,harga_jual_total ) values"
                    +"(?, ?, ?, ?, ?)";
           String sqlupdate = "UPDATE barang set jumlah=jumlah-? where kd_brg=?";
            List<PenjualanDetail> detailPenjualan = penjualan.getDetailPenjualan();
            
                for(PenjualanDetail penjualanDetail: detailPenjualan ){
                    statement=connection.prepareStatement(TRANSAKSI_SQL);
                    statement.setString(1, penjualanDetail.getPenjualan().getNo_jual());
                    statement.setString(2, penjualanDetail.getBarang().getKd_brg());
                    statement.setDouble(3,penjualanDetail.getJumlah());
                    statement.setDouble(4, penjualanDetail.getHarga_jual());
                    statement.setDouble(5, penjualanDetail.getHarga_jual_total());
                    statement.executeUpdate();
                    
                    statement = (PreparedStatement) connection.prepareStatement(sqlupdate);
                    statement.setDouble(1, penjualanDetail.getJumlah());
                    statement.setString(2, penjualanDetail.getBarang().getKd_brg());
                    statement.executeUpdate();
                }
            
            
            connection.commit();
            connection.setAutoCommit(true);
            valid=true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        }
        return valid;
    }
    
   

    @Override
    public List<Penjualan> getPenjualan() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list = new ArrayList();
        String sql="SELECT * FROM penjualan";
              
        try {
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Penjualan p=new Penjualan();
                p.setNo_jual(rs.getString("penjualan.no_jual"));
                p.setTgl(rs.getDate("penjualan.tgl"));
                String kd_plg = rs.getString("penjualan.kd_plg");
                Pelanggan getPelanggan = pelangganDaoImpl.getPelanggan(kd_plg);
                p.setPelanggan(getPelanggan);
                String kd_gd = rs.getString("penjualan.kd_gd");
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
                
                list.add(p);     
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Penjualan> getPenjualan(Date tglMulai, Date tgAkhir) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list = new ArrayList();
        String sql="SELECT * FROM penjualan"
                +" where (tgl>=?) and (tgl<=?)";
        try {
            statement=connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(tglMulai.getTime()));
            statement.setDate(2, new java.sql.Date(tgAkhir.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                Penjualan p=new Penjualan();
                p.setNo_jual(rs.getString("no_jual"));
                p.setTgl(rs.getDate("tgl"));
                String kd_plg = rs.getString("kd_plg");
                Pelanggan getPelanggan = pelangganDaoImpl.getPelanggan(kd_plg);
                p.setPelanggan(getPelanggan);
                String kd_gd = rs.getString("kd_gd");
                p.setTotal(rs.getDouble("total"));
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
                p.setDetailPenjualan(getpenjualanDetil(p));
                list.add(p);     
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    @Override
    public Penjualan getPenjualan(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        Penjualan p=null;
        String sql="SELECT * FROM penjualan where no_jual=?";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                p=new Penjualan();
                p.setNo_jual(rs.getString("penjualan.no_jual"));
                p.setTgl(rs.getDate("penjualan.tgl"));
                String kd_plg = rs.getString("penjualan.kd_plg");
                Pelanggan getPelanggan = pelangganDaoImpl.getPelanggan(kd_plg);
                p.setPelanggan(getPelanggan);
                String kd_gd = rs.getString("penjualan.kd_gd");
                Gudang getGudang = gudangDaoImpl.getGudang(kd_gd);
                p.setGudang(getGudang);
         }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public PenjualanDetail getPenjualanDetil(Penjualan penjualan, String barangId) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        PenjualanDetail pd=null;
        String sql="SELECT * FROM penjualan_detail"
                +" where no_jual=? and kd_brg=?";
        try {
            statement=connection.prepareStatement(sql);
            
            statement.setString(1,penjualan.getNo_jual() );
            statement.setString(2,barangId);
            rs=statement.executeQuery();
            while(rs.next()){
                  pd=new PenjualanDetail();
                  pd.setBarang(barangDao.getById(rs.getString("kd_brg")));
                  pd.setPenjualan(getPenjualan(rs.getString("no_jual")));
                  pd.setJumlah(rs.getDouble("jumlah"));
                  pd.setHarga_jual(rs.getDouble("harga_jual"));
         }
            return pd;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<PenjualanDetail> getpenjualanDetil(Penjualan penjualan) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        List list=new ArrayList();
        String sql="SELECT * FROM penjualan_detail"
                +" where no_jual=?";
        try {
            statement=connection.prepareStatement(sql);
            
            statement.setString(1,penjualan.getNo_jual() );
            rs=statement.executeQuery();
            while(rs.next()){
                  PenjualanDetail pd=new PenjualanDetail();
                  pd.setBarang(barangDao.getById(rs.getString("kd_brg")));
                  pd.setPenjualan(getPenjualan(rs.getString("no_jual")));
                  pd.setJumlah(rs.getDouble("jumlah"));
                  pd.setHarga_jual(rs.getDouble("harga_jual"));
                  list.add(pd);
         }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Integer penjualanTahunan(String barangId, Date Tanggal) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs=null;
        int total=0;
        DateFormat format=new SimpleDateFormat("YYYY");
        String tanggal = format.format(Tanggal);
        String sql="select sum(pj.jumlah) as total FROM\n" +
            " penjualan as p inner join penjualan_detail as pj on(p.no_jual=pj.no_jual) \n" +
            "WHERE pj.kd_brg=? and p.tgl like ? GROUP BY\n" +
            " DATE_FORMAT(p.tgl,'%YY'), pj.kd_brg;";
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1, barangId );
            statement.setString(2, tanggal+"%");
            rs=statement.executeQuery();
            while(rs.next()){
                  total=rs.getInt("total");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
