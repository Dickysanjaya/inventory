/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.BarangDao;
import dao.ManajementDao;
import dao.PenjualanDao;
import entity.Barang;
import entity.Manajemen;
import inventori.client.dialog.Pencarian;
import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author asep
 */
public class ManajemenView extends javax.swing.JInternalFrame {

    private AutoNumber autoNumber;
    private ManajementDao manajementDao;
    private BarangDao barangDao;
    private Barang barang;
    private PenjualanDao penjualanDao;
    
    public ManajemenView(AutoNumber autoNumber, 
            ManajementDao manajementDao,
            BarangDao barangDao,
            PenjualanDao penjualanDao) {
        this.autoNumber=autoNumber;
        this.barangDao=barangDao;
        this.manajementDao=manajementDao;
        this.penjualanDao=penjualanDao;
        initComponents();
        barang=new Barang();
        txtTahun.setVisible(false);
        bLoad.setVisible(false);
    }
    
    private boolean isValaidMaxPerHari(){
        double mQtyHariMax;
        double mQtyHari;
        boolean result=false;
        try{
            mQtyHari=Double.valueOf(txtQtyHari.getText());
            mQtyHariMax=Double.valueOf(txtQtyHariMax.getText());
            if(mQtyHariMax<=mQtyHari){
                JOptionPane.showMessageDialog(this, "kebutuhan maksimal per hari harus > "
                        + " dari kebutuhan rata-ratpera per hari ");
                result=false;
            }else{
                result=true;
            }
            }catch(NumberFormatException | HeadlessException e){
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
        if (txtBarang.getText().trim().equals("")) {
        } else {
            try {
                mTotalSeTahun = Double.valueOf(txtQtyTahun.getText());
                mBiyPesan = Double.valueOf(txtBiyPesan.getText());
                mBiySimpan = Double.valueOf(txtBiySimpan.getText());
                mBiySatuan = Double.valueOf(barang.getHarga_beli());
                txtQtyHari.setText(formatter.format(mTotalSeTahun / 365));
                txtQtyEOQ.setText(formatter.format(Math.sqrt(2 * mTotalSeTahun * mBiyPesan
                        / ((mBiySimpan * mBiySatuan) / 100))));
                result = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        txtQtyHariMax.requestFocus();
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
           mLeadTime=Double.valueOf(txtLeadTime.getText());
           mQtyPerhari=Double.valueOf(txtQtyHari.getText());
           mQtyPerhariMax=Double.valueOf(txtQtyHariMax.getText());
           mSelisih=mQtyPerhariMax-mQtyPerhari;
           mSafetyStok=mSelisih*mLeadTime;
           mROP=(mLeadTime*mQtyPerhari)+mSafetyStok;
           txtRopSafty.setText(String.valueOf(mROP));
           result=true;
       }catch(Exception e){
           System.out.println(e);
       }
       return result;
    }
    
    private boolean validInput(){
        boolean valid=false;
        if(txtKode.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong ");
        }else if(txtBarang.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Barang Masih Kosong ");
        }else if(txtQtyTahun.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Qty Tahun Masih Kosong ");
        }else if(txtBiyPesan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Biy Pesan Masih Kosong ");
        }else if(txtBiySimpan.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Biy Simpan Masih Kosong ");
        }else if(txtQtyHari.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Qty Hari Masih Kosong ");
        }else if(txtQtyHariMax.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Qty Hari Max Masih Kosong ");
        }else if(txtQtyEOQ.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Qty EOQ Masih Kosong ");
        }else if(txtLeadTime.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Lead Time Masih Kosong ");
        }else if(txtRopSafty.getText().trim().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Rof Safty Masih Kosong ");
        }else{
            valid=true;
        }
        return valid;
    }
    
    public Manajemen getManajemen(){
        Manajemen m=new Manajemen();
        m.setKdManag(txtKode.getText());
        m.setBarang(barang);
        m.setBiy_pesan(Double.valueOf(txtBiyPesan.getText()));
        m.setBiy_simpan(Double.valueOf(txtBiySimpan.getText()));
        m.setLead_time(Double.valueOf(txtLeadTime.getText()));
        m.setQty_eoq(Double.valueOf(txtQtyEOQ.getText()));
        m.setQty_hari(Double.valueOf(txtQtyHari.getText()));
        m.setQty_hari_max(Double.valueOf(txtQtyHariMax.getText()));
        m.setQty_tahun(Double.valueOf(txtQtyTahun.getText()));
        m.setRop_safety(Double.valueOf(txtRopSafty.getText()));
        return m;
    }
    
    public void reset(){
        //buat data jadi kosong
        txtBarang.setText("");
        txtBiyPesan.setText("");
        txtBiySimpan.setText("");
        txtKode.setText("");
        txtLeadTime.setText("");
        txtQtyEOQ.setText("");
        txtQtyHari.setText("");
        txtQtyHariMax.setText("");
        txtQtyTahun.setText("");
        txtRopSafty.setText("");
        //buat enable
        
    }
    
    public void disable(boolean input){
        txtBiyPesan.setEnabled(input);
        txtBiySimpan.setEnabled(input);
        txtLeadTime.setEnabled(input);
        //txtQtyEOQ.setEnabled(input);
        //txtQtyHari.setEnabled(input);
        txtQtyHariMax.setEnabled(input);
        txtQtyTahun.setEnabled(input);
        //txtRopSafty.setEnabled(input);
        bCariBarang.setEnabled(input);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtQtyTahun = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBiyPesan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBiySimpan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQtyHari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQtyHariMax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtQtyEOQ = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLeadTime = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRopSafty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bSearch = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        bCariBarang = new javax.swing.JButton();
        txtTahun = new com.toedter.calendar.JYearChooser();
        bLoad = new javax.swing.JButton();

        setClosable(true);
        setTitle("Manajemen Inventory");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Kode :");

        txtKode.setEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Qty Tahun :");

        txtQtyTahun.setEnabled(false);
        txtQtyTahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyTahunKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Biy Pesan :");

        txtBiyPesan.setEnabled(false);
        txtBiyPesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBiyPesanKeyTyped(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Biy Simpan :");

        txtBiySimpan.setEnabled(false);
        txtBiySimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBiySimpanActionPerformed(evt);
            }
        });
        txtBiySimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBiySimpanKeyTyped(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Qty Hari :");

        txtQtyHari.setEnabled(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Qty Hari Max :");

        txtQtyHariMax.setEnabled(false);
        txtQtyHariMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyHariMaxKeyTyped(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Qty EOQ :");

        txtQtyEOQ.setEnabled(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Lead Time :");

        txtLeadTime.setEnabled(false);
        txtLeadTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLeadTimeKeyTyped(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Rop Safety :");

        txtRopSafty.setEnabled(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Barang :");

        txtBarang.setEnabled(false);

        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jPanel1.add(bNew);

        bInsert.setText("Insert");
        bInsert.setEnabled(false);
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });
        jPanel1.add(bInsert);

        bUpdate.setText("Update");
        bUpdate.setEnabled(false);
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(bUpdate);

        bDelete.setText("Delete");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(bDelete);

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });
        jPanel1.add(bSearch);

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel1.add(bReset);

        bCariBarang.setText("Cari");
        bCariBarang.setEnabled(false);
        bCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariBarangActionPerformed(evt);
            }
        });

        txtTahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTahunKeyTyped(evt);
            }
        });

        bLoad.setText("Load");
        bLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCariBarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBiyPesan, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(txtKode))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtBiySimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(56, 56, 56)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtRopSafty, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtQtyTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addComponent(txtLeadTime, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtQtyEOQ, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtQtyHariMax, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(txtQtyHari, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(185, 185, 185)
                                .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 86, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCariBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBiyPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBiySimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQtyTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQtyHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtQtyHariMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtQtyEOQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLeadTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRopSafty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("MN");
            field.setNamaField("manag_id");
            field.setNamaTabel("mangement");
            field.setPanjangField(10);
            txtKode.setText(autoNumber.getAutoNumberInt(field));
            disable(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
            bUpdate.setEnabled(false);
            bDelete.setEnabled(false);
            bSearch.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void bCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariBarangActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List<Barang> br = barangDao.getBarangNotIn();
            if(!br.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(br, Barang.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){                   
                    barang = barangDao.getById(ambilData);
                    txtBarang.setText(barang.getNm_brg());
                    txtQtyTahun.setText(String.valueOf(penjualanDao.penjualanTahunan(barang.getKd_brg(), new Date())));
                   
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Semua barang sudah dimanaj !, silahkan input barang baru");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCariBarangActionPerformed

    private void txtBiyPesanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBiyPesanKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()==evt.VK_ENTER){
            txtBiySimpan.requestFocus();
        }
    }//GEN-LAST:event_txtBiyPesanKeyTyped

    private void txtBiySimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBiySimpanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtBiySimpanActionPerformed

    private void txtBiySimpanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBiySimpanKeyTyped
        // TODO add your handling code here:
         if(evt.getKeyChar()==evt.VK_ENTER){
            txtQtyTahun.requestFocus();
        }
    }//GEN-LAST:event_txtBiySimpanKeyTyped

    private void txtQtyTahunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyTahunKeyTyped
        // TODO add your handling code here:
         if(evt.getKeyChar()==evt.VK_ENTER){
            if(isValidEoq())
                txtQtyHariMax.requestFocus();
        }
    }//GEN-LAST:event_txtQtyTahunKeyTyped

    private void txtQtyHariMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyHariMaxKeyTyped
        // TODO add your handling code here:
         if(evt.getKeyChar()==evt.VK_ENTER){
            if(isValaidMaxPerHari())
                txtLeadTime.requestFocus();
        }
    }//GEN-LAST:event_txtQtyHariMaxKeyTyped

    private void txtLeadTimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLeadTimeKeyTyped
        // TODO add your handling code here:
         if(evt.getKeyChar()==evt.VK_ENTER){
            if(isValidROP());
        }
    }//GEN-LAST:event_txtLeadTimeKeyTyped

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            try {
                Manajemen manajemen = getManajemen();
                if(manajementDao.insert(manajemen)){
                    JOptionPane.showMessageDialog(rootPane, "Data Berhasil disimpan");
                    reset();
                    disable(false);
                    txtTahun.setVisible(false);
                    bLoad.setVisible(false);
                    bInsert.setEnabled(false);
                    bNew.setEnabled(true);
                    bUpdate.setEnabled(false);
                    bDelete.setEnabled(false);
                    bSearch.setEnabled(true);
                    bReset.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data Gagal disimpan");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ManajemenView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List<Manajemen> manajemen = manajementDao.getManajemen();
            if(!manajemen.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(manajemen, Manajemen.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Manajemen barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){                   
                    Manajemen byId = manajementDao.getById(ambilData);
                    barang=byId.getBarang();
                    txtBarang.setText(barang.getNm_brg());
                    txtBiyPesan.setText(String.valueOf(byId.getBiy_pesan()));
                    txtBiySimpan.setText(String.valueOf(byId.getBiy_simpan()));
                    txtKode.setText(byId.getKdManag());
                    txtLeadTime.setText(String.valueOf(byId.getLead_time()));
                    txtQtyEOQ.setText(String.valueOf(byId.getQty_eoq()));
                    txtQtyHari.setText(String.valueOf(byId.getQty_hari()));
                    txtQtyHariMax.setText(String.valueOf(byId.getQty_hari_max()));
                    txtQtyTahun.setText(String.valueOf(byId.getQty_tahun()));
                    txtRopSafty.setText(String.valueOf(byId.getRop_safety()));
                    disable(true);
                    txtTahun.setVisible(true);
                    bLoad.setVisible(true);
                    bInsert.setEnabled(false);
                    bNew.setEnabled(false);
                    bUpdate.setEnabled(true);
                    bDelete.setEnabled(true);
                    bSearch.setEnabled(false);
                    bReset.setEnabled(true);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Manajemen Masih Kosong !");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            try {
                Manajemen manajemen = getManajemen();
                if(manajementDao.update(manajemen)){
                    JOptionPane.showMessageDialog(rootPane, "Data Barhasil Di Update");
                    reset();
                    disable(false);
                    txtTahun.setVisible(false);
                    bLoad.setVisible(false);
                    bInsert.setEnabled(false);
                    bNew.setEnabled(true);
                    bUpdate.setEnabled(false);
                    bDelete.setEnabled(false);
                    bSearch.setEnabled(true);
                    bReset.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data Gagal Di Update");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ManajemenView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
        disable(false);
        barang=null;
        txtTahun.setVisible(false);
        bLoad.setVisible(false);
        bInsert.setEnabled(false);
        bNew.setEnabled(true);
        bUpdate.setEnabled(false);
        bDelete.setEnabled(false);
        bSearch.setEnabled(true);
        bReset.setEnabled(true);
    }//GEN-LAST:event_bResetActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            try {
                Manajemen manajemen = getManajemen();
                if(manajementDao.delete(manajemen)){
                    JOptionPane.showMessageDialog(rootPane, "Data Barhasil Di Delete");
                    reset();
                    disable(false);
                    txtTahun.setVisible(false);
                    bLoad.setVisible(false);
                    bInsert.setEnabled(false);
                    bNew.setEnabled(true);
                    bUpdate.setEnabled(false);
                    bDelete.setEnabled(false);
                    bSearch.setEnabled(true);
                    bReset.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data Gagal Di Delete");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ManajemenView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void txtTahunKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTahunKeyTyped
        
    }//GEN-LAST:event_txtTahunKeyTyped

    private void bLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadActionPerformed
        // TODO add your handling code here:
        try {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.YEAR, txtTahun.getValue());
                Date tahun=calendar.getTime();
                txtQtyTahun.setText(String.valueOf(penjualanDao.penjualanTahunan(barang.getKd_brg(), tahun)));
            } catch (RemoteException ex) {
                Logger.getLogger(ManajemenView.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_bLoadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCariBarang;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bLoad;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSearch;
    private javax.swing.JButton bUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JTextField txtBiyPesan;
    private javax.swing.JTextField txtBiySimpan;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtLeadTime;
    private javax.swing.JTextField txtQtyEOQ;
    private javax.swing.JTextField txtQtyHari;
    private javax.swing.JTextField txtQtyHariMax;
    private javax.swing.JTextField txtQtyTahun;
    private javax.swing.JTextField txtRopSafty;
    private com.toedter.calendar.JYearChooser txtTahun;
    // End of variables declaration//GEN-END:variables
}
