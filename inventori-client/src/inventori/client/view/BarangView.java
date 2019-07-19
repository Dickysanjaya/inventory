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
import dao.KategoriDao;
import entity.Barang;
import entity.Gudang;
import entity.Kategori;
import inventori.client.dialog.Pencarian;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class BarangView extends javax.swing.JInternalFrame {

    private BarangDao barangDao;
    private AutoNumber autoNumber;
    private KategoriDao kategoriDao;
    private GudangDao gudangDao;
    
    public BarangView(BarangDao barangDao, AutoNumber autoNumber,
            KategoriDao kategoriDao, GudangDao gudangDao) {
        this.autoNumber=autoNumber;
        this.barangDao=barangDao;
        this.kategoriDao=kategoriDao;
        this.gudangDao=gudangDao;
        initComponents();
    }

    public void loadAwal(){
        try {
            List<Kategori> kategori = kategoriDao.getKategori();
            for(Kategori k: kategori){
                cboKategori.addItem(k);
            }
            List<Gudang> gudang = gudangDao.getGudang();
            for(Gudang g: gudang){
                cboGudang.addItem(g);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    /*
    private boolean isValaidMaxPerHari(){
        double mQtyHariMax;
        double mQtyHari;
        boolean result=false;
        try{
            mQtyHari=Double.valueOf(txt_qty_hari.getText());
            mQtyHariMax=Double.valueOf(txt_qty_hari_max.getText());
            if(mQtyHariMax<=mQtyHari){
                JOptionPane.showMessageDialog(this, "kebutuhan maksimal per hari harus > "
                        + " dari kebutuhan rata-ratpera per hari ");
                result=false;
            }else{
                result=true;
            }
            }catch(Exception e){
            e.getMessage();
        }
        return result;
    }
    
    private boolean isValidEoq(){
        double mTotalSeTahun=0;
        double mBiySimpan;
        double mBiySatuan;
        double mBiyPesan;
        
        boolean result = false;
        
        NumberFormat formatter =
                new DecimalFormat("##0.00");
        try{
            mTotalSeTahun= Double.valueOf(txt_qty_tahun.getText());
            mBiyPesan=Double.valueOf(txt_biy_pesan.getText());
            mBiySimpan=Double.valueOf(txt_biy_simpan.getText());
            mBiySatuan=Double.valueOf(txtHarga_Beli.getText());
            txt_qty_hari.setText(formatter.format(mTotalSeTahun/365));
            txt_qty_eoq.setText(formatter.format(Math.sqrt(2*mTotalSeTahun*mBiyPesan / 
                    ((mBiySimpan*mBiySatuan)/100))));
            result=true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        txt_qty_hari_max.requestFocus();
        return result;
    }
    
    private boolean isValidROP(){
        double mLeadTime;
        double mQtyPerhari;
        double mQtyPerhariMax;
        double mSelisih;
        double mSafetyStok;
        double mROP;
        boolean result=false;
       
       try{
           mLeadTime=Double.valueOf(txt_lead_time.getText());
           mQtyPerhari=Double.valueOf(txt_qty_hari.getText());
           mQtyPerhariMax=Double.valueOf(txt_qty_hari_max.getText());
           mSelisih=mQtyPerhariMax-mQtyPerhari;
           mSafetyStok=mSelisih*mLeadTime;
           mROP=(mLeadTime*mQtyPerhari)+mSafetyStok;
           txt_rop_safety.setText(String.valueOf(mROP));
           result=true;
       }catch(Exception e){
           System.out.println(e);
       }
       return result;
    }
  */  
     public boolean validInput(){
        boolean valid=false;
        if(txtKd_brg.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtNm_brg.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Nama Masih Kosong");
        }else if(cboKategori.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(rootPane, "Kategori Masih Kosong");
        }else if(txtHarga_Beli.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Harga Beli Masih Kosong");
        }else if(txtHarga_Jual.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Bagian Belum dipilih");
        /*
        }else if(txt_biy_pesan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Biaya Tiap Pesan Masih Kosong");
            
        }else if(txt_biy_simpan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Rate Biaya Simpan Masih Kosong");
        }else if(txt_qty_tahun.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kebutuhan pertahun Masih Kosong");
        }else if(txt_qty_hari.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kebutuhan perhari Masih Kosong");
        }else if(txt_qty_hari_max.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kebutuhan Max per Hari Masih Kosong");
        }else if(txt_lead_time.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Lead Time Masih Kosong");
            */
        }else if(txtJumlah.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Jumlah Masih Kosong");
        }else if(cboGudang.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(rootPane, "Gudang Belum dipilih");
        }else{
            valid=true;
        }
        return valid;
     }
     
     
    public Barang getBarang(){
        Barang b=new Barang();
        b.setKd_brg(txtKd_brg.getText());
        b.setNm_brg(txtNm_brg.getText());
        Kategori k = (Kategori) cboKategori.getSelectedItem();
        b.setKategori(k);
        b.setHarga_beli(Double.valueOf(txtHarga_Beli.getText()));
        b.setHarga_jual(Double.valueOf(txtHarga_Jual.getText()));
        /*
        b.setBiy_pesan(Double.valueOf(txt_biy_pesan.getText()));
        b.setBiy_simpan(Double.valueOf(txt_biy_simpan.getText()));
        b.setQty_tahun(Double.valueOf(txt_qty_tahun.getText()));
        b.setQty_hari(Double.valueOf(txt_qty_hari.getText()));
        b.setQty_hari_max(Double.valueOf(txt_qty_hari_max.getText()));
        b.setLead_time(Double.valueOf(txt_lead_time.getText()));
        b.setQty_eoq(Double.valueOf(txt_qty_eoq.getText()));
        b.setRop_safety(Double.valueOf(txt_rop_safety.getText()));
        */
        b.setJumlah(Integer.valueOf(txtJumlah.getText()));
        Gudang gudang=(Gudang) cboGudang.getSelectedItem();
        b.setGudang(gudang);
        return b;
    }
    
     public void reset(){
        txtKd_brg.setText("");
        txtNm_brg.setText("");
        cboKategori.setSelectedIndex(0);
        txtHarga_Beli.setText("");
        txtHarga_Jual.setText("");
        //txt_biy_pesan.setText("");
        /*
        txt_biy_simpan.setText("");
        txt_qty_tahun.setText("");
        txt_qty_hari.setText("");
        txt_qty_hari_max.setText("");
        txt_lead_time.setText("");
        txt_qty_eoq.setText("");
        txt_rop_safety.setText("");
        */
        txtJumlah.setText("");
        cboGudang.setSelectedIndex(0);
        txtNm_brg.setEnabled(false);
        cboKategori.setEnabled(false);
        txtHarga_Beli.setEnabled(false);
        txtHarga_Jual.setEnabled(false);
        //txt_biy_simpan.setEnabled(false);
        //txt_biy_pesan.setEnabled(false);
        /*
        txt_qty_tahun.setEnabled(false);
        txt_qty_hari.setEnabled(false);
        txt_qty_hari_max.setEnabled(false);
        txt_lead_time.setEnabled(false);
        txt_qty_eoq.setEnabled(false);
        txt_rop_safety.setEnabled(false);
        */
        txtJumlah.setEnabled(false);
        cboGudang.setEnabled(false);
        bInsert.setEnabled(false);
        bNew.setEnabled(true);
        bUpdate.setEnabled(false);
        bDelete.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKd_brg = new javax.swing.JTextField();
        txtNm_brg = new javax.swing.JTextField();
        cboKategori = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cboGudang = new javax.swing.JComboBox();
        txtHarga_Beli = new asep.lib.pkg01.dokument.JTextEx();
        txtHarga_Jual = new asep.lib.pkg01.dokument.JTextEx();
        txtJumlah = new asep.lib.pkg01.dokument.JTextEx();
        jPanel3 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bCari = new javax.swing.JButton();
        bRefresh = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Barang\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Kode Barang :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama Barang :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Kategori :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Harga Beli :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Harga Jual :");

        txtKd_brg.setEnabled(false);

        txtNm_brg.setEnabled(false);
        txtNm_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNm_brgKeyTyped(evt);
            }
        });

        cboKategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Salah Satu -" }));
        cboKategori.setEnabled(false);
        cboKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cboKategoriKeyTyped(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Jumlah :");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Gudang :");

        cboGudang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih Salah Satu -" }));
        cboGudang.setEnabled(false);
        cboGudang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboGudangKeyPressed(evt);
            }
        });

        txtHarga_Beli.setEnabled(false);
        txtHarga_Beli.setInputType(asep.lib.pkg01.dokument.JTextEx.TypeText.Number);
        txtHarga_Beli.setMaxlength(15);
        txtHarga_Beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHarga_BeliKeyPressed(evt);
            }
        });

        txtHarga_Jual.setEnabled(false);
        txtHarga_Jual.setInputType(asep.lib.pkg01.dokument.JTextEx.TypeText.Number);
        txtHarga_Jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHarga_JualKeyTyped(evt);
            }
        });

        txtJumlah.setEnabled(false);
        txtJumlah.setInputType(asep.lib.pkg01.dokument.JTextEx.TypeText.Number);
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHarga_Jual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboKategori, javax.swing.GroupLayout.Alignment.LEADING, 0, 161, Short.MAX_VALUE)
                                .addComponent(txtHarga_Beli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboGudang, 0, 161, Short.MAX_VALUE))
                        .addGap(43, 43, 43)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHarga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHarga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboGudang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jPanel3.add(bNew);

        bInsert.setText("Insert");
        bInsert.setEnabled(false);
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });
        jPanel3.add(bInsert);

        bUpdate.setText("Update");
        bUpdate.setEnabled(false);
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(bUpdate);

        bDelete.setText("Delete");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(bDelete);

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });
        jPanel3.add(bCari);

        bRefresh.setText("Refresh");
        bRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRefreshActionPerformed(evt);
            }
        });
        jPanel3.add(bRefresh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("BRG");
            field.setNamaField("kd_brg");
            field.setNamaTabel("barang");
            field.setPanjangField(8);
            txtKd_brg.setText(autoNumber.getAutoNumberInt(field));
            txtNm_brg.setText("");
            cboKategori.setSelectedIndex(0);
            txtHarga_Beli.setText("");
            txtHarga_Jual.setText("");
            //txt_biy_pesan.setText("");
            /*
            txt_biy_simpan.setText("");
            txt_qty_tahun.setText("");
            txt_qty_hari.setText("");
            txt_qty_hari_max.setText("");
            txt_lead_time.setText("");
            txt_qty_eoq.setText("");
            txt_rop_safety.setText("");
            */
            txtJumlah.setText("");
            cboGudang.setSelectedIndex(0);
            txtNm_brg.setEnabled(true);
            cboKategori.setEnabled(true);
            txtHarga_Beli.setEnabled(true);
            txtHarga_Jual.setEnabled(true);
            //txt_biy_pesan.setEnabled(true);
            /*
            txt_biy_simpan.setEnabled(true);
            txt_qty_tahun.setEnabled(true);
            //txt_qty_hari.setEnabled(true);
            txt_qty_hari_max.setEnabled(true);
            txt_lead_time.setEnabled(true);
            */
            txtJumlah.setEnabled(true);
            cboGudang.setEnabled(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
            bUpdate.setEnabled(false);
            bDelete.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void txtNm_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNm_brgKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cboKategori.requestFocus();
        }
    }//GEN-LAST:event_txtNm_brgKeyTyped

    private void cboKategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboKategoriKeyTyped
        // TODO add your handling code here:
         if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtHarga_Beli.requestFocus();
         }
    }//GEN-LAST:event_cboKategoriKeyTyped

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Barang barang = getBarang();
            if(barang!=null){
                try {
                    if(barangDao.insert(barang)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_bInsertActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Barang barang = getBarang();
            if(barang!=null){
                try {
                    if(barangDao.update(barang)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");  
                    loadAwal();
                    reset();
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Data gagal diubah"); 
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        if (validInput()) {
                if (JOptionPane.showConfirmDialog(rootPane, "Apakah Anda Mau menghapus ?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                Barang barang = getBarang();
                if (barang != null) {
                    try {
                        barangDao.delete(barang);
                        loadAwal();
                        reset();
                    } catch (RemoteException ex) {
                        Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        try {
            // TODO add your handling code here:
            List<Barang> barang = barangDao.getBarang();
            if(!barang.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(barang, Barang.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                    
                    Barang b = barangDao.getById(ambilData);
                    txtKd_brg.setText(b.getKd_brg());
                    txtNm_brg.setText(b.getNm_brg());
                    loadAwal();
                    cboKategori.setSelectedItem(b.getKategori());
                    txtHarga_Beli.setText(String.valueOf(b.getHarga_beli()));
                    txtHarga_Jual.setText(String.valueOf(b.getHarga_jual()));
                    
                    /*
                    txt_biy_pesan.setText(String.valueOf(b.getBiy_pesan()));
                    txt_biy_simpan.setText(String.valueOf(b.getBiy_simpan()));
                    txt_qty_tahun.setText(String.valueOf(b.getQty_tahun()));
                    txt_qty_hari.setText(String.valueOf(b.getQty_hari()));
                    txt_qty_hari_max.setText(String.valueOf(b.getQty_hari_max()));
                    txt_lead_time.setText(String.valueOf(b.getLead_time()));
                    txt_qty_eoq.setText(String.valueOf(b.getQty_eoq()));
                    txt_rop_safety.setText(String.valueOf(b.getRop_safety()));
                    */
                    txtJumlah.setText(String.valueOf(b.getJumlah()));
                    cboGudang.setSelectedItem(b.getGudang());
                    txtNm_brg.setEnabled(true);
                    cboKategori.setEnabled(true);
                    txtHarga_Beli.setEnabled(true);
                    txtHarga_Jual.setEnabled(true);
                    //txt_biy_pesan.setEnabled(true);
                    /*
                    txt_biy_simpan.setEnabled(true);
                    txt_qty_tahun.setEnabled(true);
                    txt_qty_hari_max.setEnabled(true);
                    txt_lead_time.setEnabled(true);
                    */
                    txtJumlah.setEnabled(true);
                    cboGudang.setEnabled(true);
                    bUpdate.setEnabled(true);
                    bDelete.setEnabled(true);
                    bInsert.setEnabled(false);
                    bNew.setEnabled(true);
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCariActionPerformed

    private void bRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRefreshActionPerformed
        // TODO add your handling code here:
            reset();
    }//GEN-LAST:event_bRefreshActionPerformed

    private void txtHarga_BeliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHarga_BeliKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtHarga_Jual.requestFocus();
        }
    }//GEN-LAST:event_txtHarga_BeliKeyPressed

    private void cboGudangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboGudangKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
           //txt_biy_simpan.requestFocus();
        }
    }//GEN-LAST:event_cboGudangKeyPressed

    private void txtHarga_JualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHarga_JualKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            //txt_biy_pesan.requestFocus();
        }
    }//GEN-LAST:event_txtHarga_JualKeyTyped

    private void txtJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cboGudang.requestFocus();
        }
    }//GEN-LAST:event_txtJumlahKeyTyped

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCari;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bRefresh;
    private javax.swing.JButton bUpdate;
    private javax.swing.JComboBox cboGudang;
    private javax.swing.JComboBox cboKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private asep.lib.pkg01.dokument.JTextEx txtHarga_Beli;
    private asep.lib.pkg01.dokument.JTextEx txtHarga_Jual;
    private asep.lib.pkg01.dokument.JTextEx txtJumlah;
    private javax.swing.JTextField txtKd_brg;
    private javax.swing.JTextField txtNm_brg;
    // End of variables declaration//GEN-END:variables
}
