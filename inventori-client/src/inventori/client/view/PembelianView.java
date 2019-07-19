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
import dao.ManajementDao;
import dao.PemasokDao;
import dao.PembelianDao;
import entity.Gudang;
import entity.Pemasok;
import entity.PembelianDetail;
import entity.Pembelian;
import inventori.client.dialog.Pencarian;
import inventori.client.dialog.PencarianBarang1;
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
public class PembelianView extends javax.swing.JInternalFrame {
    private AutoNumber autoNumber;
    private PemasokDao pemasokDao;
    private GudangDao gudangDao;
    private BarangDao barangDao;
    private Pemasok pemasok;
    private DynamicTableModel tableModel;
    private PembelianDao pembelianDao;
    private ManajementDao manajementDao;
    
    public PembelianView(AutoNumber autoNumber,
        PemasokDao pemasokDao, BarangDao barangDao, GudangDao gudangDao,
        PembelianDao pembelianDao, ManajementDao manajementDao) {
        this.autoNumber =autoNumber;
        this.barangDao = barangDao;
        this.gudangDao = gudangDao;
        this.pemasokDao = pemasokDao;
        this.pembelianDao = pembelianDao;
        this.manajementDao=manajementDao;
        initComponents();
    }
    
    public Pembelian getPembelian(){
        Pembelian p=new Pembelian();
        p.setNo_beli(txtNoBeli.getText());
        p.setTgl(txtTgl.getDate());
        p.setPemasok(pemasok);
        p.setTotal(Double.valueOf(txtTotal.getText()));
        Gudang g=(Gudang) cboGudang.getSelectedItem();
        p.setGudang(g);
        List list=new ArrayList();
        for(int i=0;i<tabelPembelian.getRowCount();i++){
            PembelianDetail pd=(PembelianDetail) tableModel.get(tabelPembelian.convertRowIndexToModel(i));
            list.add(pd);
        }
        p.setDetailPembelian(list);
        return p;
    }
public void loadAwal(){
        try {
            List<Gudang> gudang = gudangDao.getGudang();
            for(Gudang g: gudang){
                cboGudang.addItem(g);
            }
            tableModel=new DynamicTableModel(PembelianDetail.class);
            tabelPembelian.setModel(tableModel);
        } catch (RemoteException ex) {
            Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public boolean validasiInput(){
        boolean valid=false;
        if(txtNoBeli.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Kode Masih Kosong !");
        }else if(txtTgl.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal Masih Kosong !");
        }else if(txtPemasok.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Pemasok Masih Kosong !");
        }else if(cboGudang.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Gudang Masih Kosong !");
        }else if(tabelPembelian.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Barang Detil Masih Kosong !");
        }else{
            valid=true;
        }
        return valid;
    }
 private Double hitungTotal(){
        Double total=0.0;
        if(tabelPembelian.getRowCount()!=0){
            for(int i=0;i<tabelPembelian.getRowCount();i++){
                PembelianDetail pd=(PembelianDetail) tableModel.get(tabelPembelian.convertRowIndexToModel(i));
                total=total+pd.getHarga_beli_total();
            }
        }
        return total;
    }
 
 private void reset(){
     txtNoBeli.setText("");
     txtPemasok.setText("");
     txtTgl.setDate(null);
     txtTotal.setText("");
     cboGudang.setSelectedIndex(0);
     txtPemasok.setEnabled(false);
     txtTgl.setEnabled(false);
     cboGudang.setEnabled(false);
     bNew.setEnabled(true);
     bAdd.setEnabled(false);
     bAddBarang.setEnabled(false);
     bDeleteBarang.setEnabled(false);
     bPrint.setEnabled(false);
     bReset.setEnabled(true);
     bSimpan.setEnabled(false);
     bUpdateBarang.setEnabled(false);
     tableModel.clear();
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboGudang = new javax.swing.JComboBox();
        txtTgl = new com.toedter.calendar.JDateChooser();
        txtPemasok = new javax.swing.JTextField();
        bAdd = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNoBeli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bAddBarang = new javax.swing.JButton();
        bUpdateBarang = new javax.swing.JButton();
        bDeleteBarang = new javax.swing.JButton();
        tabScrol = new javax.swing.JScrollPane();
        tabelPembelian = new com.stripbandunk.jwidget.JDynamicTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pembelian\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tanggal :");

        jLabel3.setText("Pemasok :");

        jLabel4.setText("Gudang :");

        cboGudang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Salah Satu -" }));
        cboGudang.setEnabled(false);

        txtTgl.setEnabled(false);

        txtPemasok.setEnabled(false);

        bAdd.setText("Add");
        bAdd.setEnabled(false);
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        txtTotal.setEnabled(false);

        jLabel5.setText("Total :");

        txtNoBeli.setEnabled(false);

        jLabel6.setText("No Beli :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNoBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bAdd))
                    .addComponent(cboGudang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 254, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboGudang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
        );

        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jPanel2.add(bNew);

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

        bAddBarang.setText("Add Barang");
        bAddBarang.setEnabled(false);
        bAddBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddBarangActionPerformed(evt);
            }
        });
        jPanel3.add(bAddBarang);

        bUpdateBarang.setText("Update Barang");
        bUpdateBarang.setEnabled(false);
        bUpdateBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateBarangActionPerformed(evt);
            }
        });
        jPanel3.add(bUpdateBarang);

        bDeleteBarang.setText("Delete Barang");
        bDeleteBarang.setEnabled(false);
        bDeleteBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteBarangActionPerformed(evt);
            }
        });
        jPanel3.add(bDeleteBarang);

        tabScrol.setViewportView(tabelPembelian);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tabScrol, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabScrol, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("PBL");
            field.setNamaField("no_beli");
            field.setNamaTabel("pembelian");
            field.setPanjangField(7);
            txtNoBeli.setText(autoNumber.getAutoNumberInt(field));
            txtTgl.setDate(new Date());
            txtTgl.setEnabled(true);
            bAdd.setEnabled(true);
            bSimpan.setEnabled(true);
            bNew.setEnabled(true);
            bReset.setEnabled(true);
            bAddBarang.setEnabled(true);
            bUpdateBarang.setEnabled(true);
            bDeleteBarang.setEnabled(true);
            cboGudang.setEnabled(true);
            txtNoBeli.setEnabled(false);
            bNew.setEnabled(false);
            //loadAwal();
        } catch (RemoteException ex) {
            Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void bAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddBarangActionPerformed
        // TODO add your handling code here:
        boolean sama=false;
        if (cboGudang.getSelectedIndex()!=0) {
            Gudang g = (Gudang) cboGudang.getSelectedItem();
            PencarianBarang1 pb = new PencarianBarang1(barangDao, manajementDao);
            pb.load(g);
            if (pb.getBarang() != null && pb.getJumlah() != 0) {
                    if (pb.getJumlah()<pb.getManajemen().getRop_safety()) {
                        JOptionPane.showMessageDialog(rootPane, "Jumlah Pemesanan ideal adalah "+pb.getJumlah());
                    }else{
                    PembelianDetail detail = new PembelianDetail();
                    detail.setNo_beli(txtNoBeli.getText());
                    detail.setBarang(pb.getBarang());
                    detail.setHarga_beli(Double.valueOf(pb.getBarang().getHarga_beli()));
                    detail.setHarga_beli_total(pb.getBarang().getHarga_beli() * pb.getJumlah());
                    detail.setJumlah(Double.valueOf(pb.getJumlah()));
                    if(tabelPembelian.getRowCount()!=0){
                        for(int i=0;i<tabelPembelian.getRowCount();i++){
                            PembelianDetail pd=(PembelianDetail) tableModel.get(tabelPembelian.convertRowIndexToModel(i));
                            if(pd.getBarang().getKd_brg().equals(pb.getBarang().getKd_brg())){
                                sama=true;
                            }
                        }
                    }
                        if (sama) {
                            JOptionPane.showMessageDialog(rootPane, "Barang yang anda masukan sudah ada !");
                        }else{
                            tableModel.add(detail);
                            txtTotal.setText(String.valueOf(hitungTotal()));
                        }
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Pilih dulu gudang !");
        }
        
    }//GEN-LAST:event_bAddBarangActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(validasiInput()){
            Pembelian pembelian = getPembelian();
            if(pembelian!=null){
                try {
                    if(pembelianDao.insert(pembelian)){
                        JOptionPane.showMessageDialog(null, "pembelian berhasil di simpan !");
                        bPrint.setEnabled(true);
                        bSimpan.setEnabled(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "pembelian gagal di simpan !");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(PembelianView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
        
    }//GEN-LAST:event_bResetActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List<Pemasok> pemasok1 = pemasokDao.getPemasok();
            if(!pemasok1.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(pemasok1, Pemasok.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Pemasok");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                    pemasok = pemasokDao.getPemasok(ambilData);
                    txtPemasok.setText(pemasok.getNama());
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Pemasok Masih Kosong !");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bUpdateBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateBarangActionPerformed
        // TODO add your handling code here:
        int index=tabelPembelian.getSelectedRow();
        if(index!=-1){
            if (cboGudang.getSelectedIndex()!=0) {
                PembelianDetail pd = (PembelianDetail) tableModel.get(tabelPembelian.convertRowIndexToModel(index));
                PencarianBarang1 pb = new PencarianBarang1(barangDao, manajementDao);
                Gudang selectedItem = (Gudang) cboGudang.getSelectedItem();
                pb.update(pd, selectedItem);
                if (pb.getBarang() != null && pb.getJumlah() != 0) {
                     int jumlah = (int) pb.getManajemen().getRop_safety();
                     if (jumlah > pb.getJumlah()) {
                        JOptionPane.showMessageDialog(rootPane, "Pemesanan ideal adalah "+jumlah);
                    } else {
                        
                        PembelianDetail detail = new PembelianDetail();
                        detail.setNo_beli(txtNoBeli.getText());
                        detail.setBarang(pb.getBarang());
                        detail.setHarga_beli(Double.valueOf(pb.getBarang().getHarga_beli()));
                        detail.setHarga_beli_total(pb.getBarang().getHarga_beli() * pb.getJumlah());
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
    }//GEN-LAST:event_bUpdateBarangActionPerformed

    private void bDeleteBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteBarangActionPerformed
        // TODO add your handling code here:
        int index=tabelPembelian.getSelectedRow();
        if(index!=-1){
                PembelianDetail pd = (PembelianDetail) tableModel.get(tabelPembelian.convertRowIndexToModel(index));
                tableModel.remove(index);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Pilih salah satu baris !");
        }
    }//GEN-LAST:event_bDeleteBarangActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List list=new ArrayList();
            list.add(getPembelian());
            HashMap map=new HashMap();
            map.put("pembelianDetil", getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPembelian_detil.jasper"));
            JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapPembelian.jasper"),map, new JRBeanCollectionDataSource(list));
            JRViewer jv=new JRViewer(jRprint);
            TampilReport report=new TampilReport("Laporan Pembelian", jv);
            reset();
        } catch (JRException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bAddBarang;
    private javax.swing.JButton bDeleteBarang;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUpdateBarang;
    private javax.swing.JComboBox cboGudang;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane tabScrol;
    private com.stripbandunk.jwidget.JDynamicTable tabelPembelian;
    private javax.swing.JTextField txtNoBeli;
    private javax.swing.JTextField txtPemasok;
    private com.toedter.calendar.JDateChooser txtTgl;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
