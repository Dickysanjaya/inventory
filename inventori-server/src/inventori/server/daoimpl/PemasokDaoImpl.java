/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.PemasokDao;
import entity.Pemasok;
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
public class PemasokDaoImpl extends UnicastRemoteObject implements PemasokDao{

    private Connection connection;

    public PemasokDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
    }

    @Override
    public boolean insert(Pemasok pemasok) throws RemoteException {
         PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO pemasok (kd_pemasok, nama, telp, alamat) values (?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, pemasok.getKd_pemasok());
            statement.setString(2, pemasok.getNama());
            statement.setString(3, pemasok.getTelp());
            statement.setString(4, pemasok.getAlamat());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Pemasok pemasok) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "UPDATE  pemasok set nama=?, telp = ? , alamat = ? where kd_pemasok = ? ";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, pemasok.getNama());
            statement.setString(2, pemasok.getTelp());
            statement.setString(3, pemasok.getAlamat());
            statement.setString(4, pemasok.getKd_pemasok());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Pemasok pemasok) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from Pemasok where kd_pemasok = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
           
            statement.setString(1, pemasok.getKd_pemasok());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Pemasok getPemasok(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Pemasok pemasok=null;
        
        String sql = "SELECT * from pemasok where kd_pemasok=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                pemasok = new Pemasok();
                pemasok.setKd_pemasok(rs.getString("kd_pemasok"));
                pemasok.setNama(rs.getString("nama"));
                pemasok.setTelp(rs.getString("telp"));
                pemasok.setAlamat(rs.getString("alamat"));
            }
            return pemasok;
        } catch (SQLException ex) {
            Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Pemasok> getPemasok() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Pemasok> list = new ArrayList<>();
        
        String sql = "SELECT * from pemasok ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Pemasok pemasok = new Pemasok();
                pemasok.setKd_pemasok(rs.getString("kd_pemasok"));
                pemasok.setNama(rs.getString("nama"));
                pemasok.setTelp(rs.getString("telp"));
                pemasok.setAlamat(rs.getString("alamat"));
                list.add(pemasok);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PemasokDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
