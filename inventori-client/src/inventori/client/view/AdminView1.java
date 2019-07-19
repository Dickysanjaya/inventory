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
import entity.Admin;
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
public class AdminView1 extends javax.swing.JInternalFrame implements DocumentListener{

    DynamicTableModel tableModel;
    private JDynamicTable tabelAdmin;
    private AdminDao adminDao;
    private AutoNumber autoNumber;
    private TableRowSorter<DynamicTableModel> sorter;
    public AdminView1(AdminDao adminDao, AutoNumber autoNumber){
        this.adminDao=adminDao;
        this.autoNumber=autoNumber;
        initComponents();
    }
    
    public void loadAwal(){
        try {
            List<Admin> admin = adminDao.getAdmin();
            if(admin!=null){
                tableModel=new DynamicTableModel<>(admin,Admin.class);
                tabelAdmin=new JDynamicTable(tableModel);
                scrolTabel.setViewportView(tabelAdmin);
                sorter=new TableRowSorter<>(tableModel);
                tabelAdmin.setRowSorter(sorter);
                txtCari.getDocument().addDocumentListener(this);
                tabelAdmin.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int index=tabelAdmin.getSelectedRow();
                        if(index!=-1){
                            Admin get = (Admin) tableModel.get(tabelAdmin.convertRowIndexToModel(index));
                            txtKode.setText(get.getKd_user());
                            txtNama.setText(get.getNm_user());
                            txtUsername.setText(get.getUser_name());
                            txtPassword.setText(get.getUser_pass());
                            cboAdmin.setSelectedItem(get.getBagian());
                            txtAlamat.setText(get.getAlamat());
                            txtTelpon.setText(get.getTelepon());
                            txtNama.setEnabled(true);
                            txtUsername.setEnabled(true);
                            txtPassword.setEnabled(true);
                            cboAdmin.setEnabled(true);
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
            Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean validInput(){
        boolean valid=false;
        if(txtKode.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtNama.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Nama Masih Kosong");
        }else if(txtUsername.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Username Masih Kosong");
        }else if(txtPassword.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Password Masih Kosong");
        }else if(cboAdmin.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(rootPane, "Bagian Belum dipilih");
        }else if(txtTelpon.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "telepon Masih Kosong");
        }else if(txtAlamat.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Alamat Masih Kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    public Admin getAdmin(){
        Admin a=new Admin();
        a.setKd_user(txtKode.getText());
        a.setNm_user(txtNama.getText());
        a.setUser_name(txtUsername.getText());
        a.setUser_pass(new String(txtPassword.getPassword()));
        a.setBagian(cboAdmin.getSelectedItem().toString());
        a.setTelepon(txtTelpon.getText());
        a.setAlamat(txtAlamat.getText());
        return a;
    }
    
    public void reset(){
        txtKode.setText("");
        txtNama.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        cboAdmin.setSelectedIndex(0);
        txtTelpon.setText("");
        txtAlamat.setText("");
        txtNama.setEnabled(false);
        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        cboAdmin.setEnabled(false);
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

        scrolTabel = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cboAdmin = new javax.swing.JComboBox();
        txtAlamat = new javax.swing.JTextField();
        txtKode = new asep.lib.pkg01.dokument.JTextEx();
        txtNama = new asep.lib.pkg01.dokument.JTextEx();
        txtTelpon = new asep.lib.pkg01.dokument.JTextEx();
        jPanel3 = new javax.swing.JPanel();
        bNew = new javax.swing.JButton();
        bInsert = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Master Pengguna\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Pengguna"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Kode :");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nama User :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Username :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Password :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Bagian :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Alamat :");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Telepon :");

        txtUsername.setEnabled(false);

        txtPassword.setEnabled(false);

        cboAdmin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Pilih salah satu -", "Admin", "Kasir", "Gudang", "staff" }));
        cboAdmin.setEnabled(false);

        txtAlamat.setEnabled(false);

        txtKode.setEnabled(false);
        txtKode.setMaxlength(10);

        txtNama.setEnabled(false);
        txtNama.setMaxlength(25);
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtTelpon.setEnabled(false);
        txtTelpon.setInputType(asep.lib.pkg01.dokument.JTextEx.TypeText.Number);
        txtTelpon.setMaxlength(15);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtUsername)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                        .addComponent(txtKode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cboAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelpon, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelpon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        bNew.setText("New ");
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

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel3.add(bReset);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Pencarian Dara Pengguna");

        jLabel9.setText("Masukan Kode Pengguna:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrolTabel)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrolTabel))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Admin admin = getAdmin();
            if(admin!=null){
                try {
                    if(adminDao.insert(admin)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Admin admin = getAdmin();
            if(admin!=null){
                try {
                    if(adminDao.update(admin)){
                       JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan"); 
                        loadAwal();
                        reset();
                    }else{
                      JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan");    
                    }
                    
                } catch (RemoteException ex) {
                    Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
            if (validInput()) {
                if (JOptionPane.showConfirmDialog(rootPane, "Apakah Anda Mau menghapus ?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                Admin admin = getAdmin();
                if (admin != null) {
                    try {
                        adminDao.delete(admin);
                        loadAwal();
                        reset();
                    } catch (RemoteException ex) {
                        Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
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

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("ADM");
            field.setNamaField("kd_user");
            field.setNamaTabel("admin");
            field.setPanjangField(7);
            txtKode.setText(autoNumber.getAutoNumberInt(field));
            txtNama.setEnabled(true);
            txtUsername.setEnabled(true);
            txtPassword.setEnabled(true);
            cboAdmin.setEnabled(true);
            txtAlamat.setEnabled(true);
            txtTelpon.setEnabled(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(AdminView1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bNew;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bUpdate;
    private javax.swing.JComboBox cboAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane scrolTabel;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private asep.lib.pkg01.dokument.JTextEx txtKode;
    private asep.lib.pkg01.dokument.JTextEx txtNama;
    private javax.swing.JPasswordField txtPassword;
    private asep.lib.pkg01.dokument.JTextEx txtTelpon;
    private javax.swing.JTextField txtUsername;
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
