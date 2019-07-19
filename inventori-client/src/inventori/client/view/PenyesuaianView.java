/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.BarangDao;
import dao.PenyesuaianDao;
import entity.Barang;
import entity.Penyesuaian;
import inventori.client.dialog.Pencarian;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author asep
 */
public class PenyesuaianView extends javax.swing.JInternalFrame {

    private PenyesuaianDao penyesuaianDao;
    private AutoNumber autoNumber;
    private BarangDao barangDao;
    private Barang barang;
    
    public PenyesuaianView(PenyesuaianDao penyesuaianDao,
            AutoNumber autoNumber,
            BarangDao barangDao) {
        this.penyesuaianDao=penyesuaianDao;
        this.autoNumber=autoNumber;
        this.barangDao=barangDao;
        initComponents();
    }
    
    private void reset(){
        txtTanggal.setEnabled(false);
        cboStatus.setEnabled(false);
        txtTanggal.setDate(null);
        txtJumlah.setText("");
        txtBarang.setText("");
        txtNoPen.setText("");
        txtJumlah.setEnabled(false);
        cboStatus.setSelectedIndex(0);
        bBaru.setEnabled(true);
        bCari.setEnabled(true);
        bHapus.setEnabled(false);
        bSimpan.setEnabled(false);
        bUbah.setEnabled(false);
    }
    
    public boolean validasiInput(){
        boolean valid=false;
        if(txtNoPen.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "NO Penyesuaian masih kosong !");
        }else if(txtTanggal.getDate()==null){
            JOptionPane.showMessageDialog(rootPane, "Tanggal masih kosong !");
        }else if(txtBarang.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Barang masih kosong !");
        }else if(cboStatus.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(rootPane, "Status  masih kosong !");
        }else{
            valid=true;
        }
        return valid;
    }

    public Penyesuaian getPenyesuaian(){
        Penyesuaian p=new Penyesuaian();
        p.setNoPenyesiaian(txtNoPen.getText());
        p.setTanggal(txtTanggal.getDate());
        p.setBarang(barang);
        p.setJumlah(Integer.valueOf(txtJumlah.getText()));
        p.setKeterangan(cboStatus.getSelectedItem().toString());
        return p;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoPen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        badd = new javax.swing.JButton();
        cboStatus = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        bBaru = new javax.swing.JButton();
        bSimpan = new javax.swing.JButton();
        bUbah = new javax.swing.JButton();
        bHapus = new javax.swing.JButton();
        bCari = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Penyesuaian Barang");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("No Penyesuaian :");

        txtNoPen.setEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tanggal :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Barang :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Status :");

        txtBarang.setEnabled(false);

        txtTanggal.setDateFormatString("dd MMMM yyyy");
        txtTanggal.setEnabled(false);

        badd.setText("Cari");
        badd.setEnabled(false);
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Salah Satu -", "Rusak", "Hilang", "Lain-Lain" }));
        cboStatus.setEnabled(false);

        bBaru.setText("Baru");
        bBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBaruActionPerformed(evt);
            }
        });
        jPanel2.add(bBaru);

        bSimpan.setText("Simpan");
        bSimpan.setEnabled(false);
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(bSimpan);

        bUbah.setText("Ubah");
        bUbah.setEnabled(false);
        bUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUbahActionPerformed(evt);
            }
        });
        jPanel2.add(bUbah);

        bHapus.setText("Hapus");
        bHapus.setEnabled(false);
        bHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHapusActionPerformed(evt);
            }
        });
        jPanel2.add(bHapus);

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });
        jPanel2.add(bCari);

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel2.add(bReset);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Jumlah :");

        txtJumlah.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoPen)
                            .addComponent(txtBarang)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(txtJumlah)
                            .addComponent(cboStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(badd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNoPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(badd))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBaruActionPerformed
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("PN");
            field.setNamaField("no_penyesuaian");
            field.setNamaTabel("penyesuaian");
            field.setPanjangField(8);
            txtNoPen.setText(autoNumber.getAutoNumberInt(field));
            txtTanggal.setDate(new Date());
            txtTanggal.setEnabled(true);
            txtJumlah.setEnabled(true);
            cboStatus.setEnabled(true);
            bBaru.setEnabled(false);
            bSimpan.setEnabled(true);
            badd.setEnabled(true);
            bCari.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(PenyesuaianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bBaruActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bResetActionPerformed

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed
        // TODO add your handling code here:
        try{
        List<Barang> brg = barangDao.getBarang();
            if(!brg.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(brg, Barang.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){          
                    barang = barangDao.getById(ambilData);
                    txtBarang.setText(barang.getNm_brg());
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_baddActionPerformed

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
        // TODO add your handling code here:
        if(validasiInput()){
            try {
                Penyesuaian penyesuaian = getPenyesuaian();
                if(penyesuaianDao.insert(penyesuaian)){
                    JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan !");
                    reset();
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan !");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PenyesuaianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bSimpanActionPerformed

    private void bUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUbahActionPerformed
        // TODO add your handling code here:
        if(validasiInput()){
            try {
                Penyesuaian penyesuaian = getPenyesuaian();
                if(penyesuaianDao.update(penyesuaian)){
                    JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah !");
                    reset();
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data gagal diubah !");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PenyesuaianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bUbahActionPerformed

    private void bHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHapusActionPerformed
        // TODO add your handling code here:
        if(validasiInput()){
            try {
                Penyesuaian penyesuaian = getPenyesuaian();
                if (JOptionPane.showConfirmDialog(rootPane, "Apakah anda mau menghapus ?","Konfirmasi",
                        JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                    if (penyesuaianDao.delete(penyesuaian)) {
                        JOptionPane.showMessageDialog(rootPane, "Data berhasil dihapus !");
                        reset();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Data gagal dihapus !");
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(PenyesuaianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bHapusActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        try {
            // TODO add your handling code here:
            List<Penyesuaian> peny = penyesuaianDao.getPenyesuaian();
            if(!peny.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(peny, Penyesuaian.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){          
                    Penyesuaian penyesuaian1 = penyesuaianDao.getPenyesuaian(ambilData);
                    txtNoPen.setText(penyesuaian1.getNoPenyesiaian());
                    txtTanggal.setDate(penyesuaian1.getTanggal());
                    txtBarang.setText(penyesuaian1.getBarang().getNm_brg());
                    barang=penyesuaian1.getBarang();
                    cboStatus.setSelectedItem(penyesuaian1.getKeterangan());
                    txtJumlah.setText(String.valueOf(penyesuaian1.getJumlah()));
                    txtTanggal.setEnabled(true);
                    txtJumlah.setEnabled(true);
                    cboStatus.setEnabled(true);
                    bUbah.setEnabled(true);
                    bHapus.setEnabled(true);
                    bBaru.setEnabled(false);
                    bCari.setEnabled(false);
                    badd.setEnabled(true);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(PenyesuaianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCariActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBaru;
    private javax.swing.JButton bCari;
    private javax.swing.JButton bHapus;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSimpan;
    private javax.swing.JButton bUbah;
    private javax.swing.JButton badd;
    private javax.swing.JComboBox cboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNoPen;
    private com.toedter.calendar.JDateChooser txtTanggal;
    // End of variables declaration//GEN-END:variables
}
