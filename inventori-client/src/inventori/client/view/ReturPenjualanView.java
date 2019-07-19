/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.JDynamicTable;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.BarangDao;
import dao.PenjualanDao;
import dao.ReturPenjualanDao;
import entity.Admin;
import entity.Barang;
import entity.Penjualan;
import entity.PenjualanDetail;
import entity.ReportPenjualan;
import entity.ReturPenjualan;
import entity.ReturnDetil;
import inventori.client.dialog.Pencarian;
import inventori.client.dialog.PencarianReturn;
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


public class ReturPenjualanView extends javax.swing.JInternalFrame{
DynamicTableModel tableModel;
private JDynamicTable tableReturPenjualan;
private BarangDao barangDao;
private AutoNumber autoNumber;
private ReturPenjualanDao returPenjualanDao;
private Admin adm;
private PenjualanDao penjualanDao;
private Penjualan penj;
private Barang barang;

    
    public ReturPenjualanView(BarangDao barangDao,
        AutoNumber autoNumber, 
        ReturPenjualanDao returPenjualanDao1,
        Admin adm,
        PenjualanDao penjualanDao) {
        this.barangDao = barangDao;
        this.autoNumber = autoNumber;
        this.returPenjualanDao = returPenjualanDao1;
        this.adm=adm;
        this.penjualanDao=penjualanDao;
        initComponents();
        tableModel=new DynamicTableModel<>(ReturnDetil.class);
        tabel.setDynamicModel(tableModel);
    }
 
 
  public ReturPenjualan getReturPenjualan(){
        ReturPenjualan rp=new ReturPenjualan();
        rp.setKd_retur_jual(txtKodeReturJual.getText());
        rp.setTgl(txtDate.getDate());
        rp.setKeterangan(txtKeterangan.getText());
        rp.setAdmin(adm);
        List list=new ArrayList();
        for(int i=0;i<tabel.getRowCount();i++){
            ReturnDetil rd=(ReturnDetil) tableModel.get(tabel.convertRowIndexToModel(i));
            list.add(rd);
        }
        rp.setReturnDetils(list);
        return rp;
    }
 public boolean validInput(){
        boolean valid=false;
        if(txtKodeReturJual.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtKeterangan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Keterangan Masih Kosong");
        }else if(txtPenjualan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Penjualan Masih Kosong");
       }else if(txtDate.getDate()==null){
            JOptionPane.showMessageDialog(rootPane, "Tanggal Masih Kosong");
       }else{
            valid=true;
        }
        return valid;
    }
public void reset(){
    txtDate.setDate(null);
    txtKeterangan.setText("");
    txtKodeReturJual.setText("");
    txtPenjualan.setText("");
    bTambahReturn.setEnabled(false);
    bDelete.setEnabled(false);
    txtDate.setEnabled(false);
    txtKeterangan.setEnabled(false);
    txtPenjualan.setEnabled(false);
    bInsert.setEnabled(false);
    bNew.setEnabled(true);
    bAdd.setEnabled(false);
    tableModel.clear();
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKodeReturJual = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPenjualan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bAdd = new javax.swing.JButton();
        txtKeterangan = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        bPrint = new javax.swing.JButton();
        tabelScrol = new javax.swing.JScrollPane();
        tabel = new com.stripbandunk.jwidget.JDynamicTable();
        bDelete = new javax.swing.JButton();
        bTambahReturn = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Kode Retur :");

        txtKodeReturJual.setEnabled(false);

        jLabel2.setText("Penjualan :");

        txtPenjualan.setEnabled(false);

        jLabel5.setText("Tanggal :");

        jLabel6.setText("Keterangan :");

        bAdd.setText("Add");
        bAdd.setEnabled(false);
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        txtKeterangan.setEnabled(false);

        txtDate.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKodeReturJual, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(txtPenjualan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAdd)
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 138, Short.MAX_VALUE))
                    .addComponent(txtKeterangan))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtKodeReturJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bAdd)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bNew.setText("New");
        bNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewActionPerformed(evt);
            }
        });
        jPanel2.add(bNew);

        bInsert.setText("Insert");
        bInsert.setEnabled(false);
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });
        jPanel2.add(bInsert);

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

        tabelScrol.setViewportView(tabel);

        bDelete.setText("Delete Barang");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bTambahReturn.setText("Insert Barang");
        bTambahReturn.setEnabled(false);
        bTambahReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelScrol)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bTambahReturn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDelete))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDelete)
                    .addComponent(bTambahReturn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelScrol, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
         if(validInput()){
            try {
                boolean insertReturn = returPenjualanDao.insertReturn(getReturPenjualan(), penj.getNo_jual());
                if(insertReturn){
                    JOptionPane.showMessageDialog(rootPane, "Data Berhasil Disimpan !");
                    bPrint.setEnabled(true);
                    bInsert.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Data gagal Disimpan !");
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReturPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_bInsertActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        try {
            // TODO add your handling code here:
            List<Penjualan> penjualan = penjualanDao.getPenjualan();
            if(!penjualan.isEmpty()){
                DynamicTableModel tableMod=new DynamicTableModel(penjualan, Penjualan.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Penjualan");
                pencarian.setTableModel(tableMod);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                    penj=penjualanDao.getPenjualan(ambilData);
                    txtPenjualan.setText(penj.getNo_jual());
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Penjualan masih kosong !");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("RPJ");
            field.setNamaField("kd_retur_jual");
            field.setNamaTabel("return_penjualan");
            field.setPanjangField(6);
            txtKodeReturJual.setText(autoNumber.getAutoNumberInt(field));
            txtDate.setEnabled(true);
            txtDate.setDate(new Date());
            txtKeterangan.setEnabled(true);
            txtPenjualan.setEnabled(true);
            bInsert.setEnabled(true);
            bTambahReturn.setEnabled(true);
            bDelete.setEnabled(true);
            bNew.setEnabled(false);
            bAdd.setEnabled(true);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void bTambahReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahReturnActionPerformed
        if (!txtPenjualan.getText().trim().isEmpty()) {
            try {
                PencarianReturn pr = new PencarianReturn(penjualanDao);
                List<PenjualanDetail> penjualanDetil = penjualanDao.getpenjualanDetil(penj);
                pr.load(penjualanDetil, penj);
                if (pr.getJumlah() != 0 && pr.getBarang() != null) {
                    barang = pr.getBarang();
                    ReturnDetil rdet = new ReturnDetil();
                    rdet.setBarang(pr.getBarang());
                    rdet.setJumlah(pr.getJumlah());
                    rdet.setReturPenjualan(getReturPenjualan());
                    if(pr.getJumlah()!=pr.getPenjualanDetil().getJumlah()){
                        rdet.setStatus(1);
                    }
                    tableModel.add(rdet);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ReturPenjualanView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "Penjualan Belum dipilih !");
        }
    }//GEN-LAST:event_bTambahReturnActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        int index=tabel.getSelectedRow();
        if(index!=-1){
            //ReturnDetil rd=(ReturnDetil) tableModel.get(tabel.convertRowIndexToModel(index));
            if(JOptionPane.showConfirmDialog(rootPane, "Apakah Anda Mau Menghapusnya ?",
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                tableModel.remove(index);
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bResetActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            List list=new ArrayList();
            list.add(getReturPenjualan());
            HashMap map=new HashMap();
            map.put("returDetil", new Direktori().getPath("lapReturPenjualan_detil"));
            JasperPrint jRprint=JasperFillManager.fillReport(getClass().getClassLoader().getResourceAsStream
                    ("inventori/client/laporan/LapReturPenjualan.jasper"),map, new JRBeanCollectionDataSource(list));
            JRViewer jv=new JRViewer(jRprint);
            TampilReport report=new TampilReport("Laporan Pembelian", jv);
            reset();
        } catch (JRException ex) {
            Logger.getLogger(PenjualanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bPrintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bTambahReturn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.stripbandunk.jwidget.JDynamicTable tabel;
    private javax.swing.JScrollPane tabelScrol;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtKodeReturJual;
    private javax.swing.JTextField txtPenjualan;
    // End of variables declaration//GEN-END:variables

    
}
