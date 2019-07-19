/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.JDynamicTable;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.AdminDao;
import dao.BarangDao;
import dao.ReturPembelianDao;
import entity.Admin;
import entity.Barang;
import entity.ReturPembelian;
import inventori.client.dialog.Pencarian;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;


public class ReturPembelianView extends javax.swing.JInternalFrame implements DocumentListener{
DynamicTableModel tableModel;
private JDynamicTable tableReturPembelian;;
private TableRowSorter <DynamicTableModel> sorter;
private BarangDao barangDao;
private  AutoNumber autoNumber;
private ReturPembelianDao returPembelianDao;
private Admin adm;
private Barang barang;

    
    public ReturPembelianView(BarangDao barangDao, 
        AdminDao adminDao, AutoNumber autoNumber, 
        ReturPembelianDao returPembelianDao, 
        Admin admin) {
        this.adm=admin;
        this.barangDao = barangDao;
        this.autoNumber = autoNumber;
        this.returPembelianDao = returPembelianDao;
        initComponents();
    }
 public void loadAwal(){
        try {
            List<ReturPembelian> returPembelian = returPembelianDao.getReturPembelian();
            if(returPembelian!=null){
                tableModel=new DynamicTableModel<>(returPembelian,ReturPembelian.class);
                tableReturPembelian=new JDynamicTable(tableModel);
                tabelScrol.setViewportView(tableReturPembelian);
                sorter=new TableRowSorter<>(tableModel);
                tableReturPembelian.setRowSorter(sorter);
                txtCari.getDocument().addDocumentListener(this);
                tableReturPembelian.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int index=tableReturPembelian.getSelectedRow();
                        if(index!=-1){
                            ReturPembelian get = (ReturPembelian) tableModel.get(tableReturPembelian.convertRowIndexToModel(index));
                            txtKodeRetur.setText(get.getKd_retur_beli());
                            txtDate.setDate(get.getTgl());
                            txtJumlah.setText(String.valueOf(get.getJumlah())); 
                            
                           
                          //  bUpdate.setEnabled(true);
                          //  bDelete.setEnabled(true);
                            bNew.setEnabled(false);
                        }
                    }
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ReturPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
  public ReturPembelian getReturPembelian(){
        ReturPembelian rp=new ReturPembelian();
        rp.setKd_retur_beli(txtKodeRetur.getText());
        rp.setTgl(txtDate.getDate());
        rp.setKeterangan(txtKeterangan.getText());
        rp.setJumlah(Double.valueOf(txtJumlah.getText()));
        rp.setAdmin(adm);
        rp.setBarang(barang);
        String text = txtKd_brg.getText();
        return rp;
    }
 public boolean validInput(){
        boolean valid=false;
        if(txtKodeRetur.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtJumlah.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Jumlah Masih Kosong");
        }else if(txtKeterangan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Keterangan Masih Kosong");
        }else if(txtKd_brg.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Barang Masih Kosong");
       }else{
            valid=true;
        }
        return valid;
    }
public void reset(){
    txtJumlah.setEnabled(false);
    txtKd_brg.setEnabled(false);
    txtKeterangan.setEnabled(false);
    txtKodeRetur.setEnabled(false);
    txtJumlah.setText("");
    txtKd_brg.setText("");
    txtKd_brg.setText("");
    txtKeterangan.setText("");
    txtKodeRetur.setText("");
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKodeRetur = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtKd_brg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bAdd = new javax.swing.JButton();
        txtJumlah = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        tabelScrol = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Retur Pembelian\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Kode Retur :");

        txtKodeRetur.setEnabled(false);

        jLabel2.setText("Kode Barang :");

        txtKd_brg.setEnabled(false);

        jLabel4.setText("Jumlah :");

        jLabel5.setText("Tanggal :");

        jLabel6.setText("Keterangan :");

        bAdd.setText("Add");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        txtJumlah.setEnabled(false);

        txtKeterangan.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addGap(57, 57, 57))
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKeterangan)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKodeRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(txtKd_brg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAdd)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jPanel2.add(bReset);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Masukan Kode Retur: ");

        jLabel3.setText("Pencarian Data Retur Pembelian");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabelScrol))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabelScrol)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
         if(validInput()){
            ReturPembelian rb = getReturPembelian();
            if(rb!=null){
                try {
                    if(returPembelianDao.insertReturn(rb)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(ReturPembelianView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_bInsertActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        try {
            // TODO add your handling code here:
            List<Barang> barang1 = barangDao.getBarang();
            if(!barang1.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(barang1, Barang.class);
                Pencarian pencarian=new Pencarian();
                pencarian.setTitle("Pencarian Barang");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                barang = barangDao.getById(ambilData);
                txtKd_brg.setText(barang.getNm_brg());
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Barang masih kosong");
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
            field.setAwalan("RPL");
            field.setNamaField("kd_retur_beli");
            field.setNamaTabel("retur_pembelian");
            field.setPanjangField(6);
            txtKodeRetur.setText(autoNumber.getAutoNumberInt(field));
            txtCari.setEnabled(true);
            txtDate.setEnabled(true);
            txtJumlah.setEnabled(true);
            txtKeterangan.setEnabled(true);
            txtKd_brg.setEnabled(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane tabelScrol;
    private javax.swing.JTextField txtCari;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKd_brg;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtKodeRetur;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent e) {
        saring();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        saring();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
       saring();
    }
    
    public void saring(){
        String text=txtCari.getText();
        if(text.length()==0){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*"+text+".*", 
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()
                   ));
        }
    }
}
