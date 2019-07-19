/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.JDynamicTable;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.PemasokDao;
import entity.Pemasok;
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

/**
 *
 * @author ayu
 */
public class PemasokView extends javax.swing.JInternalFrame implements DocumentListener{

    DynamicTableModel tableModel;
    private JDynamicTable tabelPemasok;
    private PemasokDao pemasokDao;
    private AutoNumber autoNumber;
    private TableRowSorter<DynamicTableModel> sorter;
    
    public PemasokView(PemasokDao pemasokDao, AutoNumber autoNumber) {
        this.pemasokDao=pemasokDao;
        this.autoNumber=autoNumber;
        initComponents();
    }
    
    public void loadAwal(){
        try {
            List<Pemasok> pemasok = pemasokDao.getPemasok();
            if(pemasok!=null){
                tableModel=new DynamicTableModel<>(pemasok,Pemasok.class);
                tabelPemasok=new JDynamicTable(tableModel);
                ScrolTabel.setViewportView(tabelPemasok);
                sorter=new TableRowSorter<>(tableModel);
                tabelPemasok.setRowSorter(sorter);
                txtCariPemasok.getDocument().addDocumentListener(this);
                tabelPemasok.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                 @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int index=tabelPemasok.getSelectedRow();
                        if(index!=-1){
                            Pemasok get = (Pemasok) tableModel.get(tabelPemasok.convertRowIndexToModel(index));
                            txtKd_Pemasok.setText(get.getKd_pemasok());
                            txtNm_Pemasok.setText(get.getNama());
                            txtAlamat.setText(get.getAlamat());
                            txtTelpon.setText(get.getTelp());
                            txtNm_Pemasok.setEnabled(true);
                            txtAlamat.setEnabled(true);
                            txtTelpon.setEnabled(true);
                            bUpdate.setEnabled(true);
                            bDelete.setEnabled(true);
                            bNew.setEnabled(false);
                    }
                    }
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(PemasokView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public boolean validInput(){
        boolean valid=false;
        if(txtKd_Pemasok.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtNm_Pemasok.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Nama Masih Kosong");
        }else if(txtTelpon.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "telepon Masih Kosong");
        }else if(txtAlamat.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Alamat Masih Kosong");
        }else{
            valid=true;
        }
        return valid;
    }
 
  public Pemasok getPemasok(){
        Pemasok p=new Pemasok();
        p.setKd_pemasok(txtKd_Pemasok.getText());
        p.setNama(txtNm_Pemasok.getText());
        p.setTelp(txtTelpon.getText());
        p.setAlamat(txtAlamat.getText());
        return p;
    }
  
    public void reset(){
        txtKd_Pemasok.setText("");
        txtNm_Pemasok.setText("");
        txtTelpon.setText("");
        txtAlamat.setText("");
        txtNm_Pemasok.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtTelpon.setEnabled(false);
        bInsert.setEnabled(false);
        bNew.setEnabled(true);
        bUpdate.setEnabled(false);
        bDelete.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKd_Pemasok = new javax.swing.JTextField();
        txtNm_Pemasok = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelpon = new asep.lib.pkg01.dokument.JTextEx();
        jPanel2 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        ScrolTabel = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCariPemasok = new javax.swing.JTextField();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setClosable(true);
        setIconifiable(true);
        setTitle("Master Pemasok\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Kode Pemasok : ");

        jLabel2.setText("Nama Pemasok :");

        jLabel3.setText("Telepon :");

        jLabel4.setText("Alamat :");

        txtKd_Pemasok.setEnabled(false);

        txtNm_Pemasok.setEnabled(false);

        txtAlamat.setEnabled(false);

        txtTelpon.setEnabled(false);
        txtTelpon.setInputType(asep.lib.pkg01.dokument.JTextEx.TypeText.Number);
        txtTelpon.setMaxlength(15);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAlamat)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKd_Pemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNm_Pemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(txtTelpon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 118, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKd_Pemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNm_Pemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelpon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
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

        bUpdate.setText("Update");
        bUpdate.setEnabled(false);
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(bUpdate);

        bDelete.setText("Delete");
        bDelete.setEnabled(false);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(bDelete);

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel2.add(bReset);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Pencarian Data Pemasok");

        jLabel6.setText("Masukan Kode Pemasok :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtCariPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCariPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrolTabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ScrolTabel)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("PMS");
            field.setNamaField("kd_pemasok");
            field.setNamaTabel("pemasok");
            field.setPanjangField(6);
            txtKd_Pemasok.setText(autoNumber.getAutoNumberInt(field));
            txtNm_Pemasok.setEnabled(true);
            txtAlamat.setEnabled(true);
            txtTelpon.setEnabled(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(PemasokView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Pemasok pemasok = getPemasok();
            if(pemasok!=null){
                try {
                    if(pemasokDao.insert(pemasok)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(PemasokView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
         if(validInput()){
            Pemasok pemasok = getPemasok();
            if(pemasok!=null){
                try {
                    if(pemasokDao.update(pemasok)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(PemasokView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        if (validInput()) {
                if (JOptionPane.showConfirmDialog(rootPane, "Apakah Anda Mau menghapus ?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                Pemasok pemasok = getPemasok();
                if (pemasok != null) {
                    try {
                        pemasokDao.delete(pemasok);
                        loadAwal();
                        reset();
                    } catch (RemoteException ex) {
                        Logger.getLogger(PemasokView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        reset();
        loadAwal();
    }//GEN-LAST:event_bResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrolTabel;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCariPemasok;
    private javax.swing.JTextField txtKd_Pemasok;
    private javax.swing.JTextField txtNm_Pemasok;
    private asep.lib.pkg01.dokument.JTextEx txtTelpon;
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
        String text=txtCariPemasok.getText();
        if(text.length()==0){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*"+text+".*", 
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()
                   ));
        }
    }
}
