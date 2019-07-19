/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.AdminDao;
import entity.Admin;
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
public class AdminDaoImpl extends UnicastRemoteObject implements AdminDao{
    
    private  Connection connection;
    
    public AdminDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
    }

    
    @Override
    public boolean insert(Admin admin) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO admin (kd_user,nm_user,user_name,user_pass,bagian,telepon,alamat) values (?,?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, admin.getKd_user());
            statement.setString(2, admin.getNm_user());
            statement.setString(3, admin.getUser_name());
            statement.setString(4, admin.getUser_pass());
            statement.setString(5, admin.getBagian());
            statement.setString(6, admin.getTelepon());
            statement.setString(7, admin.getAlamat());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Admin admin) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE  admin set nm_user=?, user_name = ? , user_pass = ?, bagian = ?,telepon = ?, alamat=? where kd_user = ? ";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, admin.getNm_user());
            statement.setString(2, admin.getUser_name());
            statement.setString(3, admin.getUser_pass());
            statement.setString(4, admin.getBagian());
            statement.setString(5, admin.getTelepon());
            statement.setString(6, admin.getAlamat());
            statement.setString(7, admin.getKd_user());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Admin admin) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from admin where kd_user = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
           
            statement.setString(1, admin.getKd_user());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    

    @Override
    public List<Admin> getAdmin() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Admin> list = new ArrayList<>();
        
        String sql = "SELECT * from admin ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Admin admin = new Admin();
                admin.setKd_user(rs.getString("kd_user"));
                admin.setNm_user(rs.getString("nm_user"));
                admin.setUser_name(rs.getString("user_name"));
                admin.setUser_pass(rs.getString("user_pass"));
                admin.setBagian(rs.getString("bagian"));
                admin.setTelepon(rs.getString("telepon"));
                admin.setAlamat(rs.getString("alamat"));
                list.add(admin);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Admin getAdmin(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Admin admin=null;
        
        String sql = "SELECT * from admin where kd_user=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                admin = new Admin();
                admin.setKd_user(rs.getString("kd_user"));
                admin.setNm_user(rs.getString("nm_user"));
                admin.setUser_name(rs.getString("user_name"));
                admin.setUser_pass(rs.getString("user_pass"));
                admin.setBagian(rs.getString("bagian"));
                admin.setTelepon(rs.getString("telepon"));
                admin.setAlamat(rs.getString("alamat"));
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Admin getAdminLogin(Admin adm) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Admin admin=null;
        
        String sql = "SELECT * from admin where user_name=? and user_pass=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1,adm.getUser_name());
            statement.setString(2, adm.getUser_pass());
            rs=statement.executeQuery();
            while (rs.next()){
                admin = new Admin();
                admin.setKd_user(rs.getString("kd_user"));
                admin.setNm_user(rs.getString("nm_user"));
                admin.setUser_name(rs.getString("user_name"));
                admin.setUser_pass(rs.getString("user_pass"));
                admin.setBagian(rs.getString("bagian"));
                admin.setTelepon(rs.getString("telepon"));
                admin.setAlamat(rs.getString("alamat"));
            }
            return admin;
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   }
    

