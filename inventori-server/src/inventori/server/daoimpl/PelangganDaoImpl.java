/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.PelangganDao;
import entity.Pelanggan;
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
public class PelangganDaoImpl extends UnicastRemoteObject implements PelangganDao{

    private Connection connection;
    public PelangganDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
    }

    @Override
    public boolean insert(Pelanggan pelanggan) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO pelanggan (kd_plg,nm_plg,telp,alamat) values (?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, pelanggan.getKd_plg());
            statement.setString(2, pelanggan.getNm_plg());
            statement.setString(3, pelanggan.getTelp());
            statement.setString(4, pelanggan.getAlamat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Pelanggan pelanggan) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "UPDATE  pelanggan set nm_plg=?, telp = ?, alamat = ? where kd_plg = ? ";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, pelanggan.getNm_plg());
            statement.setString(2, pelanggan.getTelp());
            statement.setString(3, pelanggan.getAlamat());
            statement.setString(4, pelanggan.getKd_plg());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Pelanggan pelanggan) throws RemoteException {
         PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from Pelanggan where kd_plg = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
           
            statement.setString(1, pelanggan.getKd_plg());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Pelanggan getPelanggan(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Pelanggan pelanggan=null;
        
        String sql = "SELECT * from pelanggan where kd_plg=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                pelanggan = new Pelanggan();
                pelanggan.setKd_plg(rs.getString("kd_plg"));
                pelanggan.setNm_plg(rs.getString("nm_plg"));
                pelanggan.setTelp(rs.getString("telp"));
                pelanggan.setAlamat(rs.getString("alamat"));
            }
            return pelanggan;
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Pelanggan> getPelanggan() throws RemoteException {
       PreparedStatement statement = null;
        ResultSet rs = null;
        List<Pelanggan> list = new ArrayList<>();
        
        String sql = "SELECT * from pelanggan ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setKd_plg(rs.getString("kd_plg"));
                pelanggan.setNm_plg(rs.getString("nm_plg"));
                pelanggan.setTelp(rs.getString("telp"));
                pelanggan.setAlamat(rs.getString("alamat"));
                list.add(pelanggan);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PelangganDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    }
    

