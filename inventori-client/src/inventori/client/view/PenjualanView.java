/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.BarangDao;
import dao.GudangDao;
import dao.PelangganDao;
import dao.PenjualanDao;
import entity.Gudang;
import entity.Pelanggan;
import entity.Penjualan;
import entity.PenjualanDetail;
import inventori.client.dialog.Pencarian;
import inventori.client.dialog.PencarianBarang;
import inventori.client.dialog.PencarianTanggal;
import inventori.client.dialog.TampilReport;
import inventori.client.laporan.path.Direktori;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PenjualanView extends javax.swing.JInternalFrame {

    private AutoNumber autoNumber;
    private PelangganDao pelangganDao;
    private GudangDao gudangDao;
    private BarangDao barangDao;
    private Pelanggan pelanggan;
    private DynamicTableModel tableModel;
    private PenjualanDao penjualanDao;
    
    public PenjualanView(AutoNumber autoNumber,
        PelangganDao pelangganDao,
        GudangDao gudangDao,
        BarangDao barangDao,
        PenjualanDao penjualanDao) {
        this.autoNumber=autoNumber;
        this.pelangganDao=pelangganDao;
        this.gudangDao=gudangDao;
        this.barangDao=barangDao;
        this.penjualanDao=penjualanDao;
        initComponents();
    }
    
    public Penjualan getPenjualan(){
        Penjualan p=new Penjualan();
        p.setNo_jual(txtKode.getText());
        p.setTgl(txtTanggal.getDate());
        p.setPelanggan(pelanggan);
        Gudang g=(Gudang) cboGudang.getSelectedItem();
        p.setGudang(g);
        p.setTotal(Double.valueOf(txtTotal.getText()));
        List list=new ArrayList();
        for(int i=0;i<tabel.getRowCount();i++){
            PenjualanDetail pd=(PenjualanDetail) tableModel.get(tabel.convertRowIndexToModel(i));
            list.add(pd);
        }
        p.setDetailPenjualan(list);
        return p;
    }
    
    public boolean validasiInput(){
        boolean valid=false;
        if(txtKode.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Kode Masih Kosong !");
        }else if(txtTanggal.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Masih Kosong !");
        }else if(txtPelanggan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Pelanggan Masih Kosong !");
        }else if(cboGudang.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Gudang Masih Kosong !");
        }else if(tabel.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Barang Detil Masih Kosong !");
        }else{
            valid=true;
        }
        return valid;
    }

    public void loadAwal(){
        try {
            List<Gudang> gudang = gudangDao.getGudang();
            for(Gudang g: gudang){
                cboGudang.addItem(g);
            }
            tableModel=new DynamicTableModel(PenjualanDetail.class);
            tabel.setModel(tableModel);
        } catch (RemoteException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Double hitungTotal(){
        Double total=0.0;
        if(tabel.getRowCount()!=0){
            for(int i=0;i<tabel.getRowCount();i++){
                PenjualanDetail pd=(PenjualanDetail) tableModel.get(tabel.convertRowIndexToModel(i));
                total=total+pd.getHarga_jual_total();
            }
        }
        return total;
    }
    
    private void reset(){
        txtKode.setText("");
        txtPelanggan.setText("");
        txtTanggal.setDate(null);
        txtTotal.setText("");
        cboGudang.setSelectedIndex(0);
        pelanggan=null;
        txtPelanggan.setEnabled(false);
        txtTanggal.setEnabled(false);
        cboGudang.setEnabled(false);
        tableModel.clear();
        bTambah.setEnabled(true);
        bAdd.setEnabled(false);
        bHBarang.setEnabled(false);
        bPrint.setEnabled(false);
        bSimpan.setEnabled(false);
        bTBarang.setEnabled(false);
        bUBarang.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboGudang = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        txtPelanggan = new javax.swing.JTextField();
        bAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bTambah = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();
        scroltabel = new javax.swing.JScrollPane();
        tabel = new com.stripbandunk.jwidget.JDynamicTable();
        bTBarang = new javax.swing.JButton();
        bUBarang = new javax.swing.JButton();
        bHBarang = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Penjualan\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tanggal :");

        jLabel3.setText("Pelanggan :");

        jLabel4.setText("Gudang :");

        cboGudang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Salah Satu -" }));
        cboGudang.setEnabled(false);

        jLabel5.setText("No. Jual :");

        txtKode.setEnabled(false);

        txtTanggal.setDateFormatString("dd MMMM yyyy");
        txtTanggal.setEnabled(false);

        txtPelanggan.setEnabled(false);

        bAdd.setText("Add");
        bAdd.setEnabled(false);
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        jLabel1.setText("Total :");

        txtTotal.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboGudang, 0, 162, Short.MAX_VALUE)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPelanggan))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAdd))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboGudang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34))
        );

        bTambah.setText("New");
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });
        jPanel2.add(bTambah);

        bSimpan.setText("Save");
        bSimpan.setEnabled(false);
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpan);

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel2.add(bReset);

        bPrint.setText("Print");
        bPrint.setEnabled(false);
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });
        jPanel2.add(bPrint);

        scroltabel.setViewportView(tabel);

        bTBarang.setText("Tambah Barang");
        bTBarang.setEnabled(false);
        bTBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTBarangActionPerformed(evt);
            }
        });

        bUBarang.setText("Ubah Barang");
        bUBarang.setEnabled(false);
        bUBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUBarangActionPerformed(evt);
            }
        });

        bHBarang.setText("Hapus Barang");
        bHBarang.setEnabled(false);
        bHBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(scroltabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(bTBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bUBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bHBarang))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bTBarang)
                    .addComponent(bUBarang)
                    .addComponent(bHBarang))
                .addGap(10, 10, 10)
                .addComponent(scroltabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("PNJ");
            field.setNamaField("no_jual");
            field.setNamaTabel("penjualan");
            field.setPanjangField(7);
            txtKode.setText(autoNumber.getAutoNumberInt(field));
            txtTanggal.setDate(new Date());
            txtTanggal.setEnabled(true);
            bAdd.setEnabled(true);
            bSimpan.setEnabled(true);
            bTBarang.setEnabled(true);
            bUBarang.setEnabled(true);
            bHBarang.setEnabled(true);
            cboGudang.setEnabled(true);
            txtPelanggan.setText(" ");
            txtTotal.setText(" ");
            cboGudang.setSelectedIndex(0);
            bTambah.setEnabled(false);
            //loadAwal();
        } catch (RemoteException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bTambahActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List<Pelanggan> pelanggan1 = pelangganDao.getPelanggan();
            if(!pelanggan1.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(pelanggan1, Pelanggan.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Pelanggan");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                    pelanggan = pelangganDao.getPelanggan(ambilData);
                   
                    txtPelanggan.setText(pelanggan.getNm_plg());
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Pelanggan Masih Kosong !");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bTBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTBarangActionPerformed
        // TODO add your handling code here:
        if (cboGudang.getSelectedIndex()!=0) {
            boolean sama=false;
            Gudang g = (Gudang) cboGudang.getSelectedItem();
            PencarianBarang pb = new PencarianBarang(barangDao);
            pb.load(g);
            if (pb.getBarang() != null && pb.getJumlah() != 0) {
                    int jumlah=pb.getBarang().getJumlah();
                    if (jumlah<pb.getJumlah()) {
                        JOptionPane.showMessageDialog(rootPane, "Jumlah tidak mencukupi !");
                    }else{
                        for(int i=0;i<tabel.getRowCount();i++){
                            PenjualanDetail pd=(PenjualanDetail) tableModel.get(tabel.convertRowIndexToModel(i));
                            if(pd.getBarang().getKd_brg().equals(pb.getBarang().getKd_brg())){
                                sama=true;
                            }
                        }
                        if (sama==false) {
                            PenjualanDetail detail = new PenjualanDetail();
                            Penjualan p=new Penjualan();
                            p.setNo_jual(txtKode.getText());
                            detail.setPenjualan(p);
                            detail.setBarang(pb.getBarang());
                            detail.setHarga_jual(Double.valueOf(pb.getBarang().getHarga_jual()));
                            detail.setHarga_jual_total(pb.getBarang().getHarga_jual() * pb.getJumlah());
                            detail.setJumlah(Double.valueOf(pb.getJumlah()));
                            tableModel.add(detail);
                            txtTotal.setText(String.valueOf(hitungTotal()));
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Barang yang anda masukan sudah ada !");
                        }
                    }
                
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Pilih dulu gudang !");
        }
    }//GEN-LAST:event_bTBarangActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(validasiInput()){
            Penjualan penjualan = getPenjualan();
            if(penjualan!=null){
                try {
                    if(penjualanDao.insert(penjualan)){
                        JOptionPane.showMessageDialog(null, "penjualan berhasil di simpan !");
                        bPrint.setEnabled(true);
                        bSimpan.setEnabled(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "penjualan gagal di simpan !");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        try {
            // TODO add your handling code here:
            List list=new ArrayList();
            list.add(getPenjualan());
            HashMap map=new HashMap();
            map.put("penjualanDetil", getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPenjualan_detil.jasper"));
            JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPenjualan.jasper"),map, new JRBeanCollectionDataSource(list));
            JRViewer jv=new JRViewer(jRprint);
            TampilReport report=new TampilReport("Laporan Penjualan", jv);
            reset();
        } catch (JRException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bPrintActionPerformed

    private void bUBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUBarangActionPerformed
        // TODO add your handling code here:
        int index=tabel.getSelectedRow();
        if(index!=-1){
            if (cboGudang.getSelectedIndex()!=0) {
                PenjualanDetail pd = (PenjualanDetail) tableModel.get(tabel.convertRowIndexToModel(index));
                PencarianBarang pb = new PencarianBarang(barangDao);
                pb.update(pd, getPenjualan().getGudang());
                if (pb.getBarang() != null && pb.getJumlah() != 0) {
                    int jumlah = pb.getBarang().getJumlah();
                    if (jumlah < pb.getJumlah()) {
                        JOptionPane.showMessageDialog(rootPane, "Jumlah tidak mencukupi !");
                    } else {
                        
                        PenjualanDetail detail = new PenjualanDetail();
                        Penjualan p=new Penjualan();
                        p.setNo_jual(txtKode.getText());
                        detail.setPenjualan(p);
                        detail.setBarang(pb.getBarang());
                        detail.setHarga_jual(Double.valueOf(pb.getBarang().getHarga_jual()));
                        detail.setHarga_jual_total(pb.getBarang().getHarga_jual() * pb.getJumlah());
                        detail.setJumlah(Double.valueOf(pb.getJumlah()));
                        tableModel.set(index,detail);
                        txtTotal.setText(String.valueOf(hitungTotal()));
                        
                    }
                    
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Gudang belum dipilih !");
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Pilih salah satu baris !");
        }
    }//GEN-LAST:event_bUBarangActionPerformed

    private void bHBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHBarangActionPerformed
        // TODO add your handling code here:
        int index=tabel.getSelectedRow();
        if(index!=-1){
                PenjualanDetail pd = (PenjualanDetail) tableModel.get(tabel.convertRowIndexToModel(index));
                tableModel.remove(index);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Pilih salah satu baris !");
        }
    }//GEN-LAST:event_bHBarangActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bHBarang;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bTBarang;
    private javax.swing.JButton bTambah;
    private javax.swing.JButton bUBarang;
    private javax.swing.JComboBox cboGudang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scroltabel;
    private com.stripbandunk.jwidget.JDynamicTable tabel;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtPelanggan;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
