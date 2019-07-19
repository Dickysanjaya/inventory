/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client;

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
import inventori.client.dialog.Splash;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ayu
 */
public class InventoriClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException{
        // TODO code application logic here
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        Registry client = LocateRegistry.getRegistry("localhost", 2222);
        final AdminDao adminDao = (AdminDao) client.lookup("inventori");
        final AutoNumber autoNumber=(AutoNumber) client.lookup("auto");
        final PemasokDao pemasokDao=(PemasokDao) client.lookup("pemasok");
        final GudangDao gudangDao=(GudangDao) client.lookup("gudang");
        final PelangganDao pelangganDao=(PelangganDao) client.lookup("pelanggan");
        final KategoriDao kategoriDao=(KategoriDao) client.lookup("kategori");
        final BarangDao barangDao=(BarangDao) client.lookup("barang");
        final PenjualanDao penjualanDao=(PenjualanDao) client.lookup("penjualan");
        final PembelianDao pembelianDao=(PembelianDao) client.lookup("pembelian");
        final ReturPembelianDao returPembelianDao = (ReturPembelianDao) client.lookup("retur_pembelian");
        final ReturPenjualanDao returPenjualanDao = (ReturPenjualanDao) client.lookup("return_penjualan");
        final PenyesuaianDao penyesuaianDao=(PenyesuaianDao) client.lookup("penyesuaian");
        final ManajementDao manajementDao=(ManajementDao) client.lookup("manajemen");
        
        Splash splash=new Splash();
                splash.setVisible(true);
                for(int i=0;i<=100;i++){
                    splash.getProgressBar().setValue(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InventoriClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
         splash.dispose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
             
             MainMenu menu=new MainMenu();
             menu.setAdminDao(adminDao);
             menu.setAutoNumber(autoNumber);
             menu.setPemasokDao(pemasokDao);
             menu.setGudangDao(gudangDao);
             menu.setPelangganDao(pelangganDao);
             menu.setKategoriDao(kategoriDao);
             menu.setBarangDao(barangDao);
             menu.setPenjualanDao(penjualanDao);
             menu.setPembelianDao(pembelianDao);
             menu.setReturPembelianDao(returPembelianDao);
             menu.setReturPenjualanDao(returPenjualanDao);
             menu.setPenyesuaianDao(penyesuaianDao);
             menu.setVisible(true);
             menu.setManajementDao(manajementDao);
             menu.tampilAwal();
            }
        });
    }
}
