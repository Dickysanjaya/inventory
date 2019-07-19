/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import dao.GudangDao;
import entity.Gudang;
import inventori.server.db.DatabaseUtilities;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class GudangDaoImpl extends UnicastRemoteObject implements GudangDao{
    
    private Connection connection;

    public GudangDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
    }
 
    @Override
    public boolean insert(Gudang gudang) throws RemoteException {
        
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO gudang (kd_gd, nm_gd, jenis) values (?,?,?)";
        try{
           statement=(PreparedStatement)connection.prepareStatement(sql);
           statement.setString(1, gudang.getKd_gd());
           statement.setString(2, gudang.getNm_gd());
           statement.setString(3, gudang.getJenis());
           
           statement.executeUpdate();
           valid=true;
        }catch(SQLException ex){
            Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Gudang gudang) throws RemoteException {
         PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "UPDATE gudang set nm_gd=?, jenis=? where kd_gd=?";
        try{
           statement=(PreparedStatement)connection.prepareStatement(sql);
           
           statement.setString(1, gudang.getNm_gd());
           statement.setString(2, gudang.getJenis());
           statement.setString(3, gudang.getKd_gd());
           
           statement.executeUpdate();
           valid=true;
        }catch(SQLException ex){
            Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
        
    }

    @Override
    public boolean delete(Gudang gudang) throws RemoteException {
           
            PreparedStatement statement = null;
            boolean valid = false;
        String sql = "DELETE FROM gudang where kd_gd = ?";
        try {
            statement = (PreparedStatement)connection.prepareStatement(sql);
            statement.setString(1, gudang.getKd_gd()); 
            statement.executeUpdate();
            valid= true;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
          return valid; 
       
        
        }
    

    @Override
    public Gudang getGudang(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Gudang gudang = null;
        
        String sql = "SELECT * FROM gudang where kd_gd = ?";
        try {
            statement = (PreparedStatement)connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                gudang= new Gudang();
                gudang.setKd_gd(rs.getString("kd_gd"));
                gudang.setNm_gd(rs.getString("nm_gd"));
                gudang.setJenis(rs.getString("jenis"));
               
            }
           return gudang;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Gudang> getGudang() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Gudang> list = new ArrayList<>();
        
        String sql = "SELECT * FROM gudang";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
               Gudang gudang = new Gudang();
               gudang.setKd_gd(rs.getString("kd_gd"));
               gudang.setNm_gd(rs.getString("nm_gd"));
               gudang.setJenis(rs.getString("jenis"));
               list.add(gudang);
            }
            return  list;
        } catch (SQLException ex) {
            Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GudangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
