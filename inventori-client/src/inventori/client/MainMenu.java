/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client;

import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.model.DynamicTableModel;
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
import entity.Admin;
import entity.Barang;
import entity.Pelanggan;
import entity.Pemasok;
import entity.Pembelian;
import entity.Penjualan;
import entity.Penyesuaian;
import entity.ReturPenjualan;
import inventori.client.dialog.About;
import inventori.client.dialog.Login;
import inventori.client.dialog.Pencarian;
import inventori.client.dialog.PencarianTanggal;
import inventori.client.dialog.TampilReport;
import inventori.client.dialog.UbahPassword;
import inventori.client.laporan.path.Direktori;
import inventori.client.view.AdminView1;
import inventori.client.view.BarangView;
import inventori.client.view.GudangView;
import inventori.client.view.InsertPembelian;
import inventori.client.view.KategoriView;
import inventori.client.view.ManajemenView;
import inventori.client.view.PelangganView;
import inventori.client.view.PemasokView;
import inventori.client.view.PembelianView;
import inventori.client.view.PenjualanView;
import inventori.client.view.PenyesuaianView;
import inventori.client.view.ReturPenjualanView;
import java.awt.Dimension;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ayu
 */
public class MainMenu extends javax.swing.JFrame {

    private AdminDao adminDao;
    private PemasokDao pemasokDao;
    private PelangganDao pelangganDao;
    private GudangDao gudangDao;
    private KategoriDao kategoriDao;
    private AutoNumber autoNumber;
    private BarangDao barangDao;
    private PenjualanDao penjualanDao;
    private PembelianDao pembelianDao;
    private ReturPembelianDao returPembelianDao;
    private ReturPenjualanDao returPenjualanDao;
    private Admin admin;
    private PenyesuaianDao penyesuaianDao;
    private ManajementDao manajementDao;
    
    public MainMenu() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    //<editor-fold defaultstate="collapsed" desc="geter remote ">
    
    public void setPenyesuaianDao(PenyesuaianDao penyesuaianDao) {
        this.penyesuaianDao = penyesuaianDao;
    }

    public void setManajementDao(ManajementDao manajementDao) {
        this.manajementDao = manajementDao;
    }
    
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    
    public void setPemasokDao(PemasokDao pemasokDao){
        this.pemasokDao = pemasokDao;
    }
    
    public void setBarangDao(BarangDao barangDao) {
        this.barangDao = barangDao;
    }
    
    public void setPelangganDao(PelangganDao pelangganDao){
        this.pelangganDao = pelangganDao;
    }
    
    public void setGudangDao(GudangDao gudangDao) {
        this.gudangDao = gudangDao;
    }
    
    public void setKategoriDao(KategoriDao kategoriDao){
        this.kategoriDao = kategoriDao;
    }
    
    
    public void setAutoNumber(AutoNumber autoNumber) {
        this.autoNumber = autoNumber;
    }
    
    public void setPenjualanDao(PenjualanDao penjualanDao) {
        this.penjualanDao = penjualanDao;
    }
    
    public void setPembelianDao(PembelianDao pembelianDao){
        this.pembelianDao = pembelianDao;
    }
    
    public void setReturPembelianDao(ReturPembelianDao returPembelianDao){
        this.returPembelianDao = returPembelianDao;
    }
    
    public void setReturPenjualanDao(ReturPenjualanDao returPenjualanDao){
        this.returPenjualanDao = returPenjualanDao;
    }
    //</editor-fold>
    
    public void tampilAwal(){
        getAdmin(false);
        Login l = new Login(adminDao);
        admin = l.tampil();
            switch (admin.getBagian()) {
                case "Admin":
                    
                    getAdmin(true);
                    break;
                case "Gudang":
                    getGudang(true);
                    break;
                case "Kasir":
                    getKasir(true);
                    break;
                case "staff":
                    getStaff(true);
                    break;
            }
    }
    
    private void getAdmin( boolean valid){
        mnuAdmin.setEnabled(valid);
        mnuBarang.setEnabled(valid);
        mnuGudang.setEnabled(valid);
        mnuInputBarang.setEnabled(valid);
        mnuKategori.setEnabled(valid);
        mnuLapManagement.setEnabled(valid);
        mnuLapTransaksi.setEnabled(valid);
        mnuLapinventori.setEnabled(valid);
        mnuLihatGudang.setEnabled(valid);
        mnuPelanggan.setEnabled(valid);
        mnuPemasok.setEnabled(valid);
        mnuPembelian.setEnabled(valid);
        mnuPenjualan.setEnabled(valid);
        mnuPenyesuaian.setEnabled(valid);
        mnuReturnPenjualan.setEnabled(valid);
    }
    
    private void getKasir( boolean valid){
        //mnuAdmin.setEnabled(valid);
        //mnuBarang.setEnabled(valid);
        //mnuGudang.setEnabled(valid);
        //mnuInputBarang.setEnabled(valid);
       // mnuKategori.setEnabled(valid);
        mnuLapManagement.setEnabled(valid);
        mnuLapTransaksi.setEnabled(valid);
       // mnuLapinventori.setEnabled(valid);
        mnuLihatGudang.setEnabled(valid);
        mnuPelanggan.setEnabled(valid);
        //mnuPemasok.setEnabled(valid);
        //mnuPembelian.setEnabled(valid);
        mnuPenjualan.setEnabled(valid);
        //mnuPenyesuaian.setEnabled(valid);
        mnuReturnPenjualan.setEnabled(valid);
    }
    
    private void getGudang( boolean valid){
       //mnuAdmin.setEnabled(valid);
        //mnuBarang.setEnabled(valid);
        mnuGudang.setEnabled(valid);
        mnuInputBarang.setEnabled(valid);
        mnuKategori.setEnabled(valid);
        mnuLapManagement.setEnabled(valid);
        mnuLapTransaksi.setEnabled(valid);
        mnuLapinventori.setEnabled(valid);
        mnuLihatGudang.setEnabled(valid);
        mnuPelanggan.setEnabled(valid);
        mnuPemasok.setEnabled(valid);
        mnuPembelian.setEnabled(valid);
        //mnuPenjualan.setEnabled(valid);
        mnuPenyesuaian.setEnabled(valid);
        //mnuReturnPenjualan.setEnabled(valid);
    }
    
    private void getStaff( boolean valid){
       // mnuAdmin.setEnabled(valid);
        //mnuBarang.setEnabled(valid);
       // mnuGudang.setEnabled(valid);
        //mnuInputBarang.setEnabled(valid);
        //mnuKategori.setEnabled(valid);
        //mnuLapManagement.setEnabled(valid);
        mnuLapTransaksi.setEnabled(valid);
        //mnuLapinventori.setEnabled(valid);
        mnuLihatGudang.setEnabled(valid);
        mnuPelanggan.setEnabled(valid);
        //mnuPemasok.setEnabled(valid);
        //mnuPembelian.setEnabled(valid);
        //mnuPenjualan.setEnabled(valid);
       //mnuPenyesuaian.setEnabled(valid);
       // mnuReturnPenjualan.setEnabled(valid);
    }
    
    private void setSkrin(JInternalFrame frame){
        jDesktopPane1.add(frame);
        Dimension scren=this.getSize();
        Dimension dim=frame.getSize();
        frame.setLocation((scren.width-dim.width)/2, (scren.height-dim.height)/2);
        frame.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuKeluarPengguna = new javax.swing.JMenuItem();
        mnuKeluarAplikasi = new javax.swing.JMenuItem();
        mnuUbahPassword = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnuAdmin = new javax.swing.JMenuItem();
        mnuGudang = new javax.swing.JMenuItem();
        mnuKategori = new javax.swing.JMenuItem();
        mnuBarang = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuPemasok = new javax.swing.JMenuItem();
        mnuPelanggan = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuPenjualan = new javax.swing.JMenuItem();
        mnuPembelian = new javax.swing.JMenuItem();
        mnuReturnPenjualan = new javax.swing.JMenuItem();
        mnuInputBarang = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mnuPenyesuaian = new javax.swing.JMenuItem();
        mnuLihatGudang = new javax.swing.JMenuItem();
        mnuManajInventory = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnuLapinventori = new javax.swing.JMenu();
        lapAdmin = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mnuLapBarangKat = new javax.swing.JMenuItem();
        lapPemasok = new javax.swing.JMenuItem();
        lapPelanggan = new javax.swing.JMenuItem();
        mnuLapTransaksi = new javax.swing.JMenu();
        lapPenjualan = new javax.swing.JMenuItem();
        lapPembelian = new javax.swing.JMenuItem();
        lapReturn = new javax.swing.JMenuItem();
        mnuLapManagement = new javax.swing.JMenu();
        lapPenyesuaian = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        JMAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("System");

        mnuKeluarPengguna.setText("Keluar Pengguna");
        mnuKeluarPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKeluarPenggunaActionPerformed(evt);
            }
        });
        jMenu1.add(mnuKeluarPengguna);

        mnuKeluarAplikasi.setText("Keluar Aplikasi");
        mnuKeluarAplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKeluarAplikasiActionPerformed(evt);
            }
        });
        jMenu1.add(mnuKeluarAplikasi);

        mnuUbahPassword.setText("Ubah Password");
        mnuUbahPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUbahPasswordActionPerformed(evt);
            }
        });
        jMenu1.add(mnuUbahPassword);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Inventory");

        mnuAdmin.setText("Pengguna");
        mnuAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAdminActionPerformed(evt);
            }
        });
        jMenu2.add(mnuAdmin);

        mnuGudang.setText("Gudang");
        mnuGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGudangActionPerformed(evt);
            }
        });
        jMenu2.add(mnuGudang);

        mnuKategori.setText("Kategori");
        mnuKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuKategoriActionPerformed(evt);
            }
        });
        jMenu2.add(mnuKategori);

        mnuBarang.setText("Barang");
        mnuBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBarangActionPerformed(evt);
            }
        });
        jMenu2.add(mnuBarang);
        jMenu2.add(jSeparator1);

        mnuPemasok.setText("Pemasok");
        mnuPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPemasokActionPerformed(evt);
            }
        });
        jMenu2.add(mnuPemasok);

        mnuPelanggan.setText("Pelanggan");
        mnuPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPelangganActionPerformed(evt);
            }
        });
        jMenu2.add(mnuPelanggan);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Transaksi");

        mnuPenjualan.setText("Penjualan");
        mnuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPenjualanActionPerformed(evt);
            }
        });
        jMenu3.add(mnuPenjualan);

        mnuPembelian.setText("Pembelian");
        mnuPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPembelianActionPerformed(evt);
            }
        });
        jMenu3.add(mnuPembelian);

        mnuReturnPenjualan.setText("Retur Penjualan");
        mnuReturnPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuReturnPenjualanActionPerformed(evt);
            }
        });
        jMenu3.add(mnuReturnPenjualan);

        mnuInputBarang.setText("Input Barang");
        mnuInputBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInputBarangActionPerformed(evt);
            }
        });
        jMenu3.add(mnuInputBarang);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Managemen");

        mnuPenyesuaian.setText("Penyesuaian Stok");
        mnuPenyesuaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPenyesuaianActionPerformed(evt);
            }
        });
        jMenu4.add(mnuPenyesuaian);

        mnuLihatGudang.setText("Lihat Isi Gudang");
        mnuLihatGudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLihatGudangActionPerformed(evt);
            }
        });
        jMenu4.add(mnuLihatGudang);

        mnuManajInventory.setText("Manajemen Inventory");
        mnuManajInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuManajInventoryActionPerformed(evt);
            }
        });
        jMenu4.add(mnuManajInventory);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Report");

        mnuLapinventori.setText("Inventory");

        lapAdmin.setText("Admin");
        lapAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapAdminActionPerformed(evt);
            }
        });
        mnuLapinventori.add(lapAdmin);

        jMenuItem3.setText("Barang By Gudang");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuLapinventori.add(jMenuItem3);

        mnuLapBarangKat.setText("Barang By Kategori");
        mnuLapBarangKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLapBarangKatActionPerformed(evt);
            }
        });
        mnuLapinventori.add(mnuLapBarangKat);

        lapPemasok.setText("Pemasok");
        lapPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapPemasokActionPerformed(evt);
            }
        });
        mnuLapinventori.add(lapPemasok);

        lapPelanggan.setText("Pelanggan");
        lapPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapPelangganActionPerformed(evt);
            }
        });
        mnuLapinventori.add(lapPelanggan);

        jMenu5.add(mnuLapinventori);

        mnuLapTransaksi.setText("Transaksi");

        lapPenjualan.setText("Penjualan");
        lapPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapPenjualanActionPerformed(evt);
            }
        });
        mnuLapTransaksi.add(lapPenjualan);

        lapPembelian.setText("Pembelian");
        lapPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapPembelianActionPerformed(evt);
            }
        });
        mnuLapTransaksi.add(lapPembelian);

        lapReturn.setText("Return");
        lapReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapReturnActionPerformed(evt);
            }
        });
        mnuLapTransaksi.add(lapReturn);

        jMenu5.add(mnuLapTransaksi);

        mnuLapManagement.setText("Management");

        lapPenyesuaian.setText("Penyesuaian Stock");
        lapPenyesuaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lapPenyesuaianActionPerformed(evt);
            }
        });
        mnuLapManagement.add(lapPenyesuaian);

        jMenu5.add(mnuLapManagement);

        jMenuBar1.add(jMenu5);

        jMenu9.setText("Help");

        JMAbout.setText("About");
        JMAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMAboutActionPerformed(evt);
            }
        });
        jMenu9.add(JMAbout);

        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAdminActionPerformed

            // TODO add your handling code here:
            AdminView1 view=new AdminView1(adminDao,autoNumber);
            view.loadAwal();
            setSkrin(view);
    }//GEN-LAST:event_mnuAdminActionPerformed

    private void mnuPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPemasokActionPerformed
        // TODO add your handling code here:
        PemasokView view1=new PemasokView(pemasokDao,autoNumber);
        view1.loadAwal();
        setSkrin(view1);
    }//GEN-LAST:event_mnuPemasokActionPerformed

    private void mnuPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPelangganActionPerformed
        // TODO add your handling code here:
        PelangganView view2=new PelangganView(pelangganDao, autoNumber);
        view2.loadAwal();
        setSkrin(view2);
    }//GEN-LAST:event_mnuPelangganActionPerformed

    private void mnuGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGudangActionPerformed
        // TODO add your handling code here:
        GudangView view = new GudangView(gudangDao, autoNumber);
        view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuGudangActionPerformed

    private void mnuKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKategoriActionPerformed
        // TODO add your handling code here:
        KategoriView view = new KategoriView(kategoriDao, autoNumber);
        view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuKategoriActionPerformed

    private void mnuBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBarangActionPerformed
        // TODO add your handling code here:
        BarangView view=new BarangView(barangDao, autoNumber, kategoriDao, gudangDao);
        view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuBarangActionPerformed

    private void mnuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPenjualanActionPerformed
        // TODO add your handling code here:
        PenjualanView view=new PenjualanView(autoNumber, pelangganDao, gudangDao, barangDao, penjualanDao);
        view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuPenjualanActionPerformed

    private void mnuPenyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPenyesuaianActionPerformed
        // TODO add your handling code here:
        PenyesuaianView view=new PenyesuaianView(penyesuaianDao, autoNumber, barangDao);
        setSkrin(view);
    }//GEN-LAST:event_mnuPenyesuaianActionPerformed

    private void mnuReturnPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuReturnPenjualanActionPerformed
        // TODO add your handling code here:
        ReturPenjualanView view = new ReturPenjualanView(barangDao, autoNumber, returPenjualanDao, admin, penjualanDao);
        //view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuReturnPenjualanActionPerformed

    private void mnuPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPembelianActionPerformed
        // TODO add your handling code here:
        PembelianView view=new PembelianView(autoNumber, pemasokDao, barangDao, gudangDao, pembelianDao, manajementDao);
        view.loadAwal();
        setSkrin(view);
    }//GEN-LAST:event_mnuPembelianActionPerformed

    private void mnuInputBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInputBarangActionPerformed
        // TODO add your handling code here:
        InsertPembelian ip=new InsertPembelian(pembelianDao, barangDao);
        setSkrin(ip);
    }//GEN-LAST:event_mnuInputBarangActionPerformed

    private void mnuLihatGudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLihatGudangActionPerformed
        // TODO add your handling code here:
        try{
        List<Barang> brg = barangDao.getBarang();
            if(!brg.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(brg, Barang.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Lihat Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                pencarian.getbAdd().setVisible(false);
                pencarian.setSize(1000, 600);
                pencarian.ambilData();     
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuLihatGudangActionPerformed

    private void lapAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapAdminActionPerformed
        try {
            // TODO add your handling code here:
                // TODO add your handling code here:
                List<Admin> admin1 = adminDao.getAdmin();
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapAdmin.jasper"),null, new JRBeanCollectionDataSource(admin1));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Seluruh Pengguna", jv);
        } catch (RemoteException | JRException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_lapAdminActionPerformed

    private void lapPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapPemasokActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            List<Pemasok> pemasok = pemasokDao.getPemasok();
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapPemasok.jasper"),null, new JRBeanCollectionDataSource(pemasok));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Seluruh Pemasok", jv);
        } catch (RemoteException | JRException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lapPemasokActionPerformed

    private void lapPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapPelangganActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            List<Pelanggan> pelanggan = pelangganDao.getPelanggan();
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapPelanggan.jasper"),null, new JRBeanCollectionDataSource(pelanggan));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Seluruh Pelanggan", jv);
        } catch (RemoteException | JRException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lapPelangganActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            List<Barang> barang = barangDao.getBarang();
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapBarangGudang.jasper"),null, new JRBeanCollectionDataSource(barang));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Seluruh Barang Berdasarkan Gudang", jv);
        } catch (RemoteException | JRException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuLapBarangKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLapBarangKatActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            // TODO add your handling code here:
            List<Barang> barang = barangDao.getBarang();
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapBarangKategori.jasper"),null, new JRBeanCollectionDataSource(barang));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Seluruh Barang Berdasarkan Kategori", jv);
        } catch (RemoteException | JRException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnuLapBarangKatActionPerformed

    public InputStream getPath(String file){
            return getClass().getClassLoader().
                        getResourceAsStream("inventori/client/laporan/"+file+".jasper");
    }
    private void lapPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapPenjualanActionPerformed
        // TODO add your handling code here:
        PencarianTanggal pt=new PencarianTanggal();
        pt.load();
        if(pt.getTglAwal()!=null&&pt.getTglAkhir()!=null){
            try {
                List<Penjualan> penj = penjualanDao.getPenjualan(pt.getTglAwal(), pt.getTglAkhir());
                HashMap map=new HashMap();
                map.put("penjualanDetil", getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPenjualan_detil.jasper"));
                JasperPrint jRprint=JasperFillManager.fillReport(new Direktori().getPath("LapPenjualan"),map, new JRBeanCollectionDataSource(penj));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Penjualan Berdasarkan Tanggal", jv);
            } catch (    RemoteException | JRException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lapPenjualanActionPerformed

    private void lapPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapPembelianActionPerformed
        // TODO add your handling code here:
        PencarianTanggal pt=new PencarianTanggal();
        pt.load();
        if(pt.getTglAwal()!=null&&pt.getTglAkhir()!=null){
            try {
                List<Pembelian> pembelian = pembelianDao.getPembelian(pt.getTglAwal(), pt.getTglAkhir());
                HashMap map=new HashMap();
                map.put("pembelianDetil", getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPembelian_detil.jasper"));
                JasperPrint jRprint=JasperFillManager.fillReport(getPath("LapPembelian"),map, new JRBeanCollectionDataSource(pembelian));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Pembelian", jv);
            } catch (    JRException | RemoteException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lapPembelianActionPerformed

    private void lapReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapReturnActionPerformed
        // TODO add your handling code here:
        PencarianTanggal pt=new PencarianTanggal();
        pt.load();
        if(pt.getTglAwal()!=null&&pt.getTglAkhir()!=null){
            try {
                List<ReturPenjualan> returPenjualan = returPenjualanDao.getReturPenjualan(pt.getTglAwal(), pt.getTglAkhir());
                HashMap map=new HashMap();
                map.put("returDetil", new Direktori().getPath("lapReturPenjualan_detil"));
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapReturPenjualan.jasper"),map, new JRBeanCollectionDataSource(returPenjualan));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Pembelian", jv);
            } catch (    RemoteException | JRException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lapReturnActionPerformed

    private void JMAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAboutActionPerformed
        // TODO add your handling code here:
        About a=new About();
        a.setTitle("Tentang Pembuat");
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }//GEN-LAST:event_JMAboutActionPerformed

    private void lapPenyesuaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lapPenyesuaianActionPerformed
        // TODO add your handling code here:
        PencarianTanggal pt=new PencarianTanggal();
        pt.load();
        if(pt.getTglAwal()!=null&&pt.getTglAkhir()!=null){
            try {
                List<Penyesuaian> penyesuaian = penyesuaianDao.getPenyesuaian(pt.getTglAwal(), pt.getTglAkhir());
                JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                        ("inventori/client/laporan/LapPenyesuaian.jasper"),null, new JRBeanCollectionDataSource(penyesuaian));
                JRViewer jv=new JRViewer(jRprint);
                TampilReport report=new TampilReport("Laporan Penyesuaian ", jv);
            } catch (    RemoteException | JRException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lapPenyesuaianActionPerformed

    private void mnuKeluarPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKeluarPenggunaActionPerformed
        // TODO add your handling code here:
        tampilAwal();
    }//GEN-LAST:event_mnuKeluarPenggunaActionPerformed

    private void mnuKeluarAplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuKeluarAplikasiActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(rootPane, "Apakah anda mau keluar ?", "Konfirmasi", 
                JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_mnuKeluarAplikasiActionPerformed

    private void mnuUbahPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUbahPasswordActionPerformed
        // TODO add your handling code here
        UbahPassword up=new UbahPassword(adminDao);
        up.setLocationRelativeTo(null);
        up.setVisible(true);
        
    }//GEN-LAST:event_mnuUbahPasswordActionPerformed

    private void mnuManajInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuManajInventoryActionPerformed
        // TODO add your handling code here:
        ManajemenView view=new ManajemenView(autoNumber, manajementDao, barangDao, penjualanDao);
        setSkrin(view);
    }//GEN-LAST:event_mnuManajInventoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMAbout;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem lapAdmin;
    private javax.swing.JMenuItem lapPelanggan;
    private javax.swing.JMenuItem lapPemasok;
    private javax.swing.JMenuItem lapPembelian;
    private javax.swing.JMenuItem lapPenjualan;
    private javax.swing.JMenuItem lapPenyesuaian;
    private javax.swing.JMenuItem lapReturn;
    private javax.swing.JMenuItem mnuAdmin;
    private javax.swing.JMenuItem mnuBarang;
    private javax.swing.JMenuItem mnuGudang;
    private javax.swing.JMenuItem mnuInputBarang;
    private javax.swing.JMenuItem mnuKategori;
    private javax.swing.JMenuItem mnuKeluarAplikasi;
    private javax.swing.JMenuItem mnuKeluarPengguna;
    private javax.swing.JMenuItem mnuLapBarangKat;
    private javax.swing.JMenu mnuLapManagement;
    private javax.swing.JMenu mnuLapTransaksi;
    private javax.swing.JMenu mnuLapinventori;
    private javax.swing.JMenuItem mnuLihatGudang;
    private javax.swing.JMenuItem mnuManajInventory;
    private javax.swing.JMenuItem mnuPelanggan;
    private javax.swing.JMenuItem mnuPemasok;
    private javax.swing.JMenuItem mnuPembelian;
    private javax.swing.JMenuItem mnuPenjualan;
    private javax.swing.JMenuItem mnuPenyesuaian;
    private javax.swing.JMenuItem mnuReturnPenjualan;
    private javax.swing.JMenuItem mnuUbahPassword;
    // End of variables declaration//GEN-END:variables
}
