/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.view;

import asep.ws.entity.NumberField;
import asep.ws.service.AutoNumber;
import com.stripbandunk.jwidget.JDynamicTable;
import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.PelangganDao;
import entity.Pelanggan;
import entity.Pelanggan;
import inventori.client.dialog.TampilReport;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ayu
 */
public class PelangganView extends javax.swing.JInternalFrame implements DocumentListener{

    DynamicTableModel tableModel;
    private JDynamicTable tabelPelanggan;
    private PelangganDao pelangganDao;
    private AutoNumber autoNumber;
    private TableRowSorter<DynamicTableModel> sorter;
    
    public PelangganView(PelangganDao pelangganDao, AutoNumber autoNumber){
        this.pelangganDao=pelangganDao;
        this.autoNumber=autoNumber;
        initComponents();
    }
    
public void loadAwal(){
        try {
            List<Pelanggan> pelanggan = pelangganDao.getPelanggan();
            if(pelanggan!=null){
                tableModel=new DynamicTableModel<>(pelanggan,Pelanggan.class);
                tabelPelanggan=new JDynamicTable(tableModel);
                ScrolTabel.setViewportView(tabelPelanggan);
                sorter=new TableRowSorter<>(tableModel);
                tabelPelanggan.setRowSorter(sorter);
                txtCariPelanggan.getDocument().addDocumentListener(this);
                tabelPelanggan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int index=tabelPelanggan.getSelectedRow();
                        if(index!=-1){
                            Pelanggan get = (Pelanggan) tableModel.get(tabelPelanggan.convertRowIndexToModel(index));
                            txtKd_Plg.setText(get.getKd_plg());
                            txtNama.setText(get.getNm_plg());
                            txtAlamat.setText(get.getAlamat());
                            txtTelpon.setText(get.getTelp());
                            txtNama.setEnabled(true);
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
            Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean validInput(){
        boolean valid=false;
        if(txtKd_Plg.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Kode Masih Kosong");
        }else if(txtNama.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Nama Masih Kosong");
        }else if(txtTelpon.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Telepon Masih Kosong");
        }else if(txtAlamat.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Alamat Masih Kosong");
        }else{
            valid=true;
        }
        return valid;
    }
    
    public Pelanggan getPelanggan(){
        Pelanggan p=new Pelanggan();
        p.setKd_plg(txtKd_Plg.getText());
        p.setNm_plg(txtNama.getText());
        p.setTelp(txtTelpon.getText());
        p.setAlamat(txtAlamat.getText());
        return p;
    }
    
    public void reset(){
        txtKd_Plg.setText("");
        txtNama.setText("");
        txtTelpon.setText("");
        txtAlamat.setText("");
        txtNama.setEnabled(false);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKd_Plg = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtNama = new asep.lib.pkg01.dokument.JTextEx();
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
        txtCariPelanggan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Master Pelanggan\n");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Kode Pelanggan :");

        jLabel2.setText("Nama Pelanggan:");

        jLabel3.setText("Telepon :");

        jLabel4.setText("Alamat :");

        txtKd_Plg.setEnabled(false);

        txtAlamat.setEnabled(false);

        txtNama.setEnabled(false);
        txtNama.setMaxlength(25);

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKd_Plg, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txtTelpon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtAlamat))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKd_Plg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelpon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
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

        bReset.setBackground(new java.awt.Color(153, 153, 255));
        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jPanel2.add(bReset);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Masukan Kode Pelanggan :");

        jLabel6.setText("Pencarian Data Pelanggan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCariPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrolTabel)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrolTabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        // TODO add your handling code here:
         if(validInput()){
            Pelanggan pelanggan = getPelanggan();
            if(pelanggan!=null){
                try {
                    pelangganDao.insert(pelanggan);
                    loadAwal();
                    reset();
                } catch (RemoteException ex) {
                    Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        if(validInput()){
            Pelanggan pelanggan = getPelanggan();
            if(pelanggan!=null){
                try {
                    pelangganDao.update(pelanggan);
                    loadAwal();
                    reset();
                } catch (RemoteException ex) {
                    Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        if (validInput()) {
                if (JOptionPane.showConfirmDialog(rootPane, "Apakah Anda Mau menghapus ?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
                Pelanggan pelanggan = getPelanggan();
                if (pelanggan != null) {
                    try {
                        pelangganDao.delete(pelanggan);
                        loadAwal();
                        reset();
                    } catch (RemoteException ex) {
                        Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            NumberField field=new NumberField();
            field.setAwalan("PLG");
            field.setNamaField("kd_plg");
            field.setNamaTabel("pelanggan");
            field.setPanjangField(8);
            txtKd_Plg.setText(autoNumber.getAutoNumberInt(field));
            txtNama.setEnabled(true);
            txtAlamat.setEnabled(true);
            txtTelpon.setEnabled(true);
            bInsert.setEnabled(true);
            bNew.setEnabled(false);
        } catch (RemoteException ex) {
            Logger.getLogger(PelangganView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNewActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCariPelanggan;
    private javax.swing.JTextField txtKd_Plg;
    private asep.lib.pkg01.dokument.JTextEx txtNama;
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
        String text=txtCariPelanggan.getText();
        if(text.length()==0){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*"+text+".*", 
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()
                   ));
        }
    }
}
