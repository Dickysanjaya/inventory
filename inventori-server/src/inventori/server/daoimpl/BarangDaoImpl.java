/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server.daoimpl;

import com.mysql.jdbc.PreparedStatement;
import dao.BarangDao;
import dao.GudangDao;
import entity.Barang;
import entity.Gudang;
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
public class BarangDaoImpl extends UnicastRemoteObject implements BarangDao{
    
    private Connection connection;
    private KategoriDaoImpl kategoriDaoImpl;
    private GudangDao gudangDao;

    public BarangDaoImpl() throws RemoteException {
        connection = DatabaseUtilities.getConnection();
        kategoriDaoImpl=new KategoriDaoImpl();
        gudangDao=new GudangDaoImpl();
    }
    
    

    @Override
    public boolean insert(Barang barang) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "INSERT INTO barang (kd_brg,nm_brg,kd_kat,harga_beli,harga_jual,"
                + "    jumlah, gudang_id) "
                + "     values (?,?,?,?,?,?,?)";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, barang.getKd_brg());
            statement.setString(2, barang.getNm_brg());
            statement.setString(3, barang.getKategori().getKd_kat());
            statement.setDouble(4, barang.getHarga_beli());
            statement.setDouble(5, barang.getHarga_jual());
            statement.setInt(6, barang.getJumlah());
            statement.setString(7, barang.getGudang().getKd_gd());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean update(Barang barang) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE  barang set nm_brg=?, kd_kat=?, harga_beli=?, harga_jual=?,"
                + " jumlah=?, gudang_id=? where kd_brg=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
           
            statement.setString(1, barang.getNm_brg());
            statement.setString(2, barang.getKategori().getKd_kat());
            statement.setDouble(3, barang.getHarga_beli());
            statement.setDouble(4, barang.getHarga_jual());
            statement.setInt(5, barang.getJumlah());
            statement.setString(6, barang.getGudang().getKd_gd());
            statement.setString(7, barang.getKd_brg());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean delete(Barang barang) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
        
        String sql = "DELETE from barang where kd_brg = ?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
           
            statement.setString(1, barang.getKd_brg());
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }


    

    @Override
    public List<Barang> getBarang() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Barang> list = new ArrayList<>();
        
        String sql = "SELECT * from barang ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Barang barang = new Barang();
                barang.setKd_brg(rs.getString("kd_brg"));
                barang.setNm_brg(rs.getString("nm_brg"));
                Kategori id = kategoriDaoImpl.getKategori(rs.getString("kd_kat"));
                barang.setKategori(id);
                barang.setHarga_beli(rs.getDouble("harga_beli"));
                barang.setHarga_jual(rs.getDouble("harga_jual"));
                barang.setJumlah(rs.getInt("jumlah"));
                barang.setGudang(gudangDao.getGudang(rs.getString("gudang_id")));
               
                list.add(barang);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean kurangJumlahStok(double jumlah, Barang barang) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE barang_digudang set jumlah=jumlah-? where kd_brg=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setDouble(1, jumlah);
            statement.setString(2, barang.getKd_brg());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public boolean tambahJumlahStok(double jumlah, Barang barang) throws RemoteException {
        PreparedStatement statement = null;
        boolean valid = false;
       
        String sql = "UPDATE barang set jumlah=jumlah+? where kd_brg=?";
        try {
            statement = (PreparedStatement) connection.prepareStatement(sql);
            statement.setDouble(1, jumlah);
            statement.setString(2, barang.getKd_brg());
            
            statement.executeUpdate();
            valid = true;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return valid;
    }

    @Override
    public Barang getById(String Id) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Barang barang=null;
        
        String sql = "SELECT * from barang where kd_brg=?";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, Id);
            rs=statement.executeQuery();
            while (rs.next()){
                barang = new Barang();
                barang.setKd_brg(rs.getString("kd_brg"));
                barang.setNm_brg(rs.getString("nm_brg"));
                String kd_kat=rs.getString("kd_kat");
                //System.out.println(kd_kat);
                Kategori id = kategoriDaoImpl.getKategori(kd_kat);
                barang.setKategori(id);
                barang.setHarga_beli(rs.getDouble("harga_beli"));
                barang.setHarga_jual(rs.getDouble("harga_jual"));
                barang.setJumlah(rs.getInt("jumlah"));
                barang.setGudang(gudangDao.getGudang(rs.getString("gudang_id")));
                
            }
            return barang;
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Barang> getBarang(Gudang g) throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Barang> list = new ArrayList<>();
        
        String sql = "select * from barang as b inner join gudang as g on (b.gudang_id=g.kd_gd) WHERE g.kd_gd=?;";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            statement.setString(1, g.getKd_gd());
            rs=statement.executeQuery();
            while (rs.next()){
                Barang barang = new Barang();
                barang.setKd_brg(rs.getString("b.kd_brg"));
                barang.setNm_brg(rs.getString("b.nm_brg"));
                Kategori id = kategoriDaoImpl.getKategori(rs.getString("b.kd_kat"));
                //System.out.println(id.getNm_kat());
                barang.setKategori(id);
                barang.setHarga_beli(rs.getDouble("b.harga_beli"));
                barang.setHarga_jual(rs.getDouble("b.harga_jual"));
                barang.setJumlah(rs.getInt("b.jumlah"));
                barang.setGudang(gudangDao.getGudang(rs.getString("b.gudang_id")));
               
                list.add(barang);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Barang> getBarangNotIn() throws RemoteException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Barang> list = new ArrayList<>();
        
        String sql = "select * from barang WHERE kd_brg NOT IN ( SELECT barang_kd_brg FROM mangement ); ";
        try {
            statement =(PreparedStatement) connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while (rs.next()){
                Barang barang = new Barang();
                barang.setKd_brg(rs.getString("kd_brg"));
                barang.setNm_brg(rs.getString("nm_brg"));
                Kategori id = kategoriDaoImpl.getKategori(rs.getString("kd_kat"));
                barang.setKategori(id);
                barang.setHarga_beli(rs.getDouble("harga_beli"));
                barang.setHarga_jual(rs.getDouble("harga_jual"));
                barang.setJumlah(rs.getInt("jumlah"));
                barang.setGudang(gudangDao.getGudang(rs.getString("gudang_id")));
               
                list.add(barang);
            }
            return list;
           
        } catch (SQLException ex) {
            Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BarangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
