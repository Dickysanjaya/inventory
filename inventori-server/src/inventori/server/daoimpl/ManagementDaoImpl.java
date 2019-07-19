/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.GudangDao;
import dao.ManajementDao;
import entity.Gudang;
import entity.Kategori;
import entity.Manajemen;
import inventori.server.db.DatabaseUtilities;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
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
public class ManagementDaoImpl extends UnicastRemoteObject implements ManajementDao{
    
    private Connection connection;
    private KategoriDaoImpl kategoriDaoImpl;
    private BarangDaoImpl barangDao;

    public ManagementDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
        kategoriDaoImpl=new KategoriDaoImpl();
        barangDao=new BarangDaoImpl();
    }
    
    

    @Override
    public boolean insert(Manajemen manajemen) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO mangement (manag_id,qty_tahun,"
                + "     biy_pesan,biy_simpan,qty_hari,qty_hari_max,qty_eoq,lead_time,rop_safety, barang_kd_brg) "
                + "     values (?,?,?,?,?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, manajemen.getKdManag());
            statement.setDouble(2, manajemen.getQty_tahun());
            statement.setDouble(3, manajemen.getBiy_pesan());
            statement.setDouble(4, manajemen.getBiy_simpan());
            statement.setDouble(5, manajemen.getQty_hari());
            statement.setDouble(6, manajemen.getQty_hari_max());
            statement.setDouble(7, manajemen.getQty_eoq());
            statement.setDouble(8, manajemen.getLead_time());
            statement.setDouble(9, manajemen.getRop_safety());
            statement.setString(10, manajemen.getBarang().getKd_brg());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Manajemen manajemen) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE  mangement set qty_tahun=?, biy_pesan=?,"
                + " biy_simpan=?, qty_hari=?, qty_hari_max=?, qty_eoq=?, lead_time=?, "
                + "rop_safety=?, barang_kd_brg=? where manag_id=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setDouble(1, manajemen.getQty_tahun());
            statement.setDouble(2, manajemen.getBiy_pesan());
            statement.setDouble(3, manajemen.getBiy_simpan());
            statement.setDouble(4, manajemen.getQty_hari());
            statement.setDouble(5, manajemen.getQty_hari_max());
            statement.setDouble(6, manajemen.getQty_eoq());
            statement.setDouble(7, manajemen.getLead_time());
            statement.setDouble(8, manajemen.getRop_safety());
            statement.setString(9, manajemen.getBarang().getKd_brg());
            statement.setString(10, manajemen.getKdManag());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Manajemen manajemen) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from mangement where manag_id = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, manajemen.getKdManag());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }


    

    @Override
    public List<Manajemen> getManajemen() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Manajemen> list = new ArrayList<>();
        
        String sql = "SELECT * from mangement ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Manajemen manajemen = new Manajemen();
                manajemen.setKdManag(rs.getString("manag_id"));
                manajemen.setQty_tahun(rs.getDouble("qty_tahun"));
                manajemen.setBiy_pesan(rs.getDouble("biy_pesan"));
                manajemen.setBiy_simpan(rs.getDouble("biy_simpan"));
                manajemen.setQty_hari(rs.getDouble("qty_hari"));
                manajemen.setQty_hari_max(rs.getDouble("qty_hari_max"));
                manajemen.setQty_eoq(rs.getDouble("qty_eoq"));
                manajemen.setLead_time(rs.getDouble("lead_time"));
                manajemen.setRop_safety(rs.getDouble("rop_safety"));
                manajemen.setBarang(barangDao.getById(rs.getString("barang_kd_brg")));
                list.add(manajemen);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public Manajemen getById(String Id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Manajemen manajemen=null;
        
        String sql = "SELECT * from mangement where manag_id=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, Id);
            rs=statement.executeQuery();
            while (rs.next()){
                manajemen = new Manajemen();
                manajemen.setKdManag(rs.getString("manag_id"));
                manajemen.setQty_tahun(rs.getDouble("qty_tahun"));
                manajemen.setBiy_pesan(rs.getDouble("biy_pesan"));
                manajemen.setBiy_simpan(rs.getDouble("biy_simpan"));
                manajemen.setQty_hari(rs.getDouble("qty_hari"));
                manajemen.setQty_hari_max(rs.getDouble("qty_hari_max"));
                manajemen.setQty_eoq(rs.getDouble("qty_eoq"));
                manajemen.setLead_time(rs.getDouble("lead_time"));
                manajemen.setRop_safety(rs.getDouble("rop_safety"));
                manajemen.setBarang(barangDao.getById(rs.getString("barang_kd_brg")));
                
            }
            return manajemen;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Manajemen getRofSafety(String barangId) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Manajemen manajemen=null;
        
        String sql = "SELECT * from mangement where barang_kd_brg=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, barangId);
            rs=statement.executeQuery();
            while (rs.next()){
                manajemen = new Manajemen();
                manajemen.setKdManag(rs.getString("manag_id"));
                manajemen.setQty_tahun(rs.getDouble("qty_tahun"));
                manajemen.setBiy_pesan(rs.getDouble("biy_pesan"));
                manajemen.setBiy_simpan(rs.getDouble("biy_simpan"));
                manajemen.setQty_hari(rs.getDouble("qty_hari"));
                manajemen.setQty_hari_max(rs.getDouble("qty_hari_max"));
                manajemen.setQty_eoq(rs.getDouble("qty_eoq"));
                manajemen.setLead_time(rs.getDouble("lead_time"));
                manajemen.setRop_safety(rs.getDouble("rop_safety"));
                manajemen.setBarang(barangDao.getById(rs.getString("barang_kd_brg")));
                
            }
            return manajemen;
        } catch (SQLException ex) {
            Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagementDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    
}
