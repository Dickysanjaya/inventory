/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.KategoriDao;
import entity.Kategori;
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
public class KategoriDaoImpl extends UnicastRemoteObject implements KategoriDao{

    private Connection connection;

    public KategoriDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
    }
    
    
    @Override
    public boolean insert(Kategori kategori) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO kategori (kd_kat,nm_kat) values (?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, kategori.getKd_kat());
            statement.setString(2, kategori.getNm_kat());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Kategori kategori) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE  kategori set nm_kat=? where kd_kat = ? ";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, kategori.getNm_kat());
            statement.setString(2, kategori.getKd_kat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Kategori kategori) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from kategori where kd_kat = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
           
            statement.setString(1, kategori.getKd_kat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Kategori getKategori(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Kategori kategori=null;
        
        String sql = "SELECT * from kategori where kd_kat=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                kategori = new Kategori();
                kategori.setKd_kat(rs.getString("kd_kat"));
                kategori.setNm_kat(rs.getString("nm_kat"));
                
            }
            return kategori;
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Kategori> getKategori() throws RemoteException {
       PreparedStatement statement = null;
        ResultSet rs = null;
        List<Kategori> list = new ArrayList<>();
        
        String sql = "SELECT * from kategori ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Kategori kategori = new Kategori();
                kategori.setKd_kat(rs.getString("kd_kat"));
                kategori.setNm_kat(rs.getString("nm_kat"));
                
                list.add(kategori);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KategoriDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
