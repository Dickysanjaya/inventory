/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.ReturPembelianDao;
import entity.Barang;
import entity.ReportPembelian;
import entity.ReturPembelian;
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
public class ReturPembelianDaoImpl extends UnicastRemoteObject implements ReturPembelianDao{
    
     private Connection connection;
     private PembelianDaoImpl pembelianDaoImpl;
     private BarangDaoImpl barangDaoImpl;
     private AdminDaoImpl adminDaoImpl;

    public ReturPembelianDaoImpl() throws RemoteException {
        pembelianDaoImpl = new PembelianDaoImpl();
        barangDaoImpl = new BarangDaoImpl();
        adminDaoImpl = new AdminDaoImpl();
        connection = DatabaseUtilities.getConnection();
    }
    
    

    @Override
    public boolean insertReturn(ReturPembelian kembali) throws RemoteException {
        boolean valid = false;
        PreparedStatement statement = null;
        String sql = "INSERT INTO retur_pembelian (kd_retur_beli, kd_brg, jumlah, tgl, kd_user, keterangan) "
                    + "values (?,?,?,?,?, ?)";
        try {
            connection.setAutoCommit(false);
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, kembali.getKd_retur_beli());
        
            statement.setString(2, kembali.getBarang().getKd_brg());
            statement.setDouble(3, kembali.getJumlah());
            statement.setDate(4, new Date(kembali.getTgl().getTime()));
            statement.setString(5, kembali.getAdmin().getKd_user());
            statement.setString(6, kembali.getKeterangan());
            statement.executeUpdate();
            
            barangDaoImpl=new BarangDaoImpl();
            
            barangDaoImpl.tambahJumlahStok(kembali.getJumlah(), kembali.getBarang());
            connection.commit();
            connection.setAutoCommit(true);
            return valid=true;
          
        } catch (SQLException ex) {
            Logger.getLogger(ReturPembelianDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public List<ReturPembelian> getReturPembelian() throws RemoteException {
        PreparedStatement statement = null;
       ResultSet rs = null;
       List list = new ArrayList();
       String sql ="select * from retur_pembelian";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                ReturPembelian r = new ReturPembelian();
                r.setKd_retur_beli(rs.getString("kd_retur_beli"));
                r.setJumlah(rs.getDouble("jumlah"));
                r.setTgl(rs.getDate("tgl"));
                r.setAdmin(adminDaoImpl.getAdmin(rs.getString("kd_user")));
                String kd_brg=rs.getString("kd_brg");
                List<Barang> barang = barangDaoImpl.getBarang();
             Barang getBarang = barangDaoImpl.getById(kd_brg);
               r.setBarang(getBarang);
                
                
                list.add(r);   
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ReturPembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReturPembelianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    }
    




       
   
