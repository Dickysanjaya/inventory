/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.BarangDao;
import dao.PenyesuaianDao;
import entity.Penyesuaian;
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
public class PenyesuaianDaoImpl extends UnicastRemoteObject implements PenyesuaianDao{
    
    private  Connection connection;
    private BarangDao barangDao;
    
    public PenyesuaianDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
        barangDao=new BarangDaoImpl();
    }

    
    @Override
    public boolean insert(Penyesuaian penyesuaian) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO penyesuaian (no_penyesuaian, tgl, kd_brg, status, jumlah) values (?,?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, penyesuaian.getNoPenyesiaian());
            statement.setDate(2, new Date(penyesuaian.getTanggal().getTime()));
            statement.setString(3, penyesuaian.getBarang().getKd_brg());
            statement.setString(4, penyesuaian.getKeterangan());
            statement.setInt(5, penyesuaian.getJumlah());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Penyesuaian penyesuaian) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE  penyesuaian set tgl=?, kd_brg = ? , status = ?, jumlah=? where no_penyesuaian = ? ";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setDate(1, new Date(penyesuaian.getTanggal().getTime()));
            statement.setString(2, penyesuaian.getBarang().getKd_brg());
            statement.setString(3, penyesuaian.getKeterangan());
            statement.setInt(4, penyesuaian.getJumlah());
            statement.setString(5, penyesuaian.getNoPenyesiaian());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Penyesuaian penyesuaian) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        String sql = "DELETE from penyesuaian where no_penyesuaian = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, penyesuaian.getNoPenyesiaian());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    

    @Override
    public List<Penyesuaian> getPenyesuaian() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Penyesuaian> list = new ArrayList<>();
        
        String sql = "SELECT * from penyesuaian ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Penyesuaian penyesuaian = new Penyesuaian();
                penyesuaian.setNoPenyesiaian(rs.getString("no_penyesuaian"));
                penyesuaian.setTanggal(rs.getDate("tgl"));
                penyesuaian.setBarang(barangDao.getById(rs.getString("kd_brg")));
                penyesuaian.setKeterangan(rs.getString("status"));
                penyesuaian.setJumlah(rs.getInt("jumlah"));
                list.add(penyesuaian);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Penyesuaian getPenyesuaian(String id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Penyesuaian penyesuaian=null;
        
        String sql = "SELECT * from penyesuaian where no_penyesuaian=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, id);
            rs=statement.executeQuery();
            while (rs.next()){
                penyesuaian = new Penyesuaian();
                penyesuaian.setNoPenyesiaian(rs.getString("no_penyesuaian"));
                penyesuaian.setTanggal(rs.getDate("tgl"));
                penyesuaian.setBarang(barangDao.getById(rs.getString("kd_brg")));
                penyesuaian.setKeterangan(rs.getString("status"));
                penyesuaian.setJumlah(rs.getInt("jumlah"));
            }
            return penyesuaian;
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Penyesuaian> getPenyesuaian(java.util.Date awal, java.util.Date akhir) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Penyesuaian> list = new ArrayList<>();
        
        String sql = "SELECT * from penyesuaian where (tgl>=?) and (tgl<=?)";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setDate(1, new Date(awal.getTime()));
            statement.setDate(2, new Date(akhir.getTime()));
            rs=statement.executeQuery();
            while (rs.next()){
                Penyesuaian penyesuaian = new Penyesuaian();
                penyesuaian.setNoPenyesiaian(rs.getString("no_penyesuaian"));
                penyesuaian.setTanggal(rs.getDate("tgl"));
                penyesuaian.setBarang(barangDao.getById(rs.getString("kd_brg")));
                penyesuaian.setKeterangan(rs.getString("status"));
                penyesuaian.setJumlah(rs.getInt("jumlah"));
                list.add(penyesuaian);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PenyesuaianDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
   }
    

