/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.ReturPenjualanDao;
import entity.ReturPenjualan;
import entity.ReturnDetil;
import inventori.server.db.DatabaseUtilities;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayu
 */
public class ReturPenjualanDaoImpl extends UnicastRemoteObject implements ReturPenjualanDao{

   private Connection connection;
     private PenjualanDaoImpl penjualanDaoImpl;
     private BarangDaoImpl barangDaoImpl;
     private AdminDaoImpl adminDaoImpl;

    public ReturPenjualanDaoImpl() throws RemoteException {
        penjualanDaoImpl = new PenjualanDaoImpl();
        barangDaoImpl = new BarangDaoImpl();
        adminDaoImpl = new AdminDaoImpl();
        connection = DatabaseUtilities.getConnection();
    }
    

    @Override
    public boolean insertReturn(ReturPenjualan kembali, String noJual) throws RemoteException {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "INSERT INTO return_penjualan (kd_retur_jual, tgl, kd_user, keterangan) "
                    + "values (?,?,?,?)";
        try {
            connection.setAutoCommit(false);
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, kembali.getKd_retur_jual());
            statement.setDate(2, new Date(kembali.getTgl().getTime()));
            statement.setString(3, kembali.getAdmin().getKd_user());
            statement.setString(4, kembali.getKeterangan());
            statement.executeUpdate();
            for(ReturnDetil rd: kembali.getReturnDetils()){
                String sql2="insert into return_detil (barang_id, return_id, jumlah) values(?, ?, ?)";
                statement=(PreparedStatement) connection.prepareStatement(sql2);
                statement.setString(1, rd.getBarang().getKd_brg());
                statement.setString(2, rd.getReturPenjualan().getKd_retur_jual());
                statement.setInt(3, rd.getJumlah());
                statement.executeUpdate();
                
                String sql3 = null;
                if (rd.getStatus()==1) {
                    sql3 = " update penjualan_detail set jumlah=jumlah-? where no_jual=? and kd_brg=?";
                    statement = (PreparedStatement) connection.prepareStatement(sql3);
                    statement.setInt(1, rd.getJumlah());
                    statement.setString(2, noJual);
                    statement.setString(3, rd.getBarang().getKd_brg());
                    statement.executeUpdate();
                }else{
                    String sql5="DELETE FROM penjualan_detail WHERE no_jual=? and kd_brg=?"; 
                    statement=(PreparedStatement) connection.prepareStatement(sql5);
                    statement.setString(1, noJual);
                    statement.setString(2, rd.getBarang().getKd_brg());
                    statement.executeUpdate();
                }
                
                String sql4 = "UPDATE barang set jumlah=jumlah+? where kd_brg=?";
                statement = (PreparedStatement) connection.prepareStatement(sql4);
                statement.setDouble(1, rd.getJumlah());
                statement.setString(2, rd.getBarang().getKd_brg());
                statement.executeUpdate();
                
                
            }
            connection.commit();
            connection.setAutoCommit(true);
            return valid=true;
          
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                Logger.getLogger(ReturPenjualanDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<ReturPenjualan> getReturPenjualan(java.util.Date awal, java.util.Date akhir) throws RemoteException {
        PreparedStatement statement = null;
       ResultSet rs = null;
       List list = new ArrayList();
       String sql ="select * from return_penjualan where (tgl>=?) and (tgl<=?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setDate(1, new Date(awal.getTime()));
            statement.setDate(2, new Date(akhir.getTime()));
            rs=statement.executeQuery();
            while(rs.next()){
                ReturPenjualan r = new ReturPenjualan();
                r.setKd_retur_jual(rs.getString("kd_retur_jual"));
                r.setTgl(rs.getDate("tgl"));
                r.setAdmin(adminDaoImpl.getAdmin(rs.getString("kd_user")));
                r.setKeterangan(rs.getString("keterangan"));
                r.setReturnDetils(getReturDetil(r));
                list.add(r);   
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
   @Override
    public List<ReturPenjualan> getReturPenjualan() throws RemoteException {
        PreparedStatement statement = null;
       ResultSet rs = null;
       List list = new ArrayList();
       String sql ="select * from return_penjualan";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                ReturPenjualan r = new ReturPenjualan();
                r.setKd_retur_jual(rs.getString("kd_retur_jual"));
                r.setTgl(rs.getDate("tgl"));
                r.setAdmin(adminDaoImpl.getAdmin(rs.getString("kd_user")));
                r.setReturnDetils(getReturDetil(r));
                list.add(r);   
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public ReturPenjualan getReturPenjual(String id) throws RemoteException {
       PreparedStatement statement = null;
       ResultSet rs = null;
       ReturPenjualan r=null;
       String sql ="select * from return_penjualan where kd_retur_jual=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while(rs.next()){
                r = new ReturPenjualan();
                r.setKd_retur_jual(rs.getString("kd_retur_jual"));
                r.setTgl(rs.getDate("tgl"));
                r.setAdmin(adminDaoImpl.getAdmin(rs.getString("kd_user")));
                r.setKeterangan(rs.getString("keterangan"));
            }
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    public List getReturDetil(ReturPenjualan rp) throws RemoteException {
       PreparedStatement statement = null;
       ResultSet rs = null;
       List list = new ArrayList();
       String sql ="select * from return_detil where return_id=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, rp.getKd_retur_jual());
            rs=statement.executeQuery();
            while(rs.next()){
                ReturnDetil rd = new ReturnDetil();
                rd.setBarang(barangDaoImpl.getById(rs.getString("barang_id")));
                rd.setReturPenjualan(getReturPenjual(rs.getString("return_id")));
                rd.setJumlah(rs.getInt("jumlah"));
                list.add(rd);   
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPenjualanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    }
    

