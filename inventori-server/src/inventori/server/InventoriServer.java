    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.server;

import asep.ws.service.AutoNumber;
import dao.AdminDao;
import dao.BarangDao;
import dao.GudangDao;
import dao.KategoriDao;
import dao.ManajementDao;
import dao.PelangganDao;
import dao.PemasokDao;
import dao.PembelianDao;
import dao.PenjualanDao;
import dao.PenyesuaianDao;
import dao.ReturPembelianDao;
import dao.ReturPenjualanDao;
import inventori.server.daoimpl.AdminDaoImpl;
import inventori.server.daoimpl.BarangDaoImpl;
import inventori.server.daoimpl.GudangDaoImpl;
import inventori.server.daoimpl.KategoriDaoImpl;
import inventori.server.daoimpl.ManagementDaoImpl;
import inventori.server.daoimpl.PelangganDaoImpl;
import inventori.server.daoimpl.PemasokDaoImpl;
import inventori.server.daoimpl.PembelianDaoImpl;
import inventori.server.daoimpl.PenjualanDaoImpl;
import inventori.server.daoimpl.PenyesuaianDaoImpl;
import inventori.server.daoimpl.ReturPembelianDaoImpl;
import inventori.server.daoimpl.ReturPenjualanDaoImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author ayu
 */
public class InventoriServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws RemoteException{
        // TODO code application logic here
        Registry server = LocateRegistry.createRegistry(2222);
        AdminDao adminDao = new AdminDaoImpl(); //interface diisi kelas
        AutoNumber autoNumber=new inventori.server.daoimpl.AutoNumber();
        PemasokDao pemasokDao=new PemasokDaoImpl();
        GudangDao gudangDao=new GudangDaoImpl();
        PelangganDao pelangganDao=new PelangganDaoImpl();
        KategoriDao kategoriDao=new KategoriDaoImpl();
        BarangDao barangDao=new BarangDaoImpl();
        PenjualanDao penjualanDao=new PenjualanDaoImpl();
        PembelianDao pembelianDao=new PembelianDaoImpl();
        ReturPembelianDao returPembelianDao = new ReturPembelianDaoImpl();
        ReturPenjualanDao returPenjualanDao = new ReturPenjualanDaoImpl();
        PenyesuaianDao penyesuaianDao=new PenyesuaianDaoImpl();
        ManajementDao manajementDao=new ManagementDaoImpl();
        
        server.rebind("inventori", adminDao);
        server.rebind("auto", autoNumber);
        server.rebind("pemasok", pemasokDao);
        server.rebind("gudang", gudangDao);
        server.rebind("pelanggan", pelangganDao);
        server.rebind("kategori", kategoriDao);
        server.rebind("barang", barangDao);
        server.rebind("penjualan", penjualanDao);
        server.rebind("pembelian", pembelianDao);
        server.rebind("retur_pembelian", returPembelianDao);
        server.rebind("return_penjualan", returPenjualanDao);
        server.rebind("penyesuaian", penyesuaianDao);
        server.rebind("manajemen", manajementDao);
        
        System.out.println("Server berjalan");
        
        
    }
}
