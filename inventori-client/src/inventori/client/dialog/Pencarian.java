/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.dialog;

import com.stripbandunk.jwidget.JDynamicTable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ayu
 */
public class Pencarian extends javax.swing.JDialog implements DocumentListener{

    private TableRowSorter<TableModel> sorter;
    private TableModel tableModel;
    private List data=new ArrayList();
    private String id;
    
    public Pencarian() {
        setModal(true);
        initComponents();
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JDynamicTable getTabelPencarian() {
        return tabelPencarian;
    }

    public JButton getbAdd() {
        return bAdd;
    }

    
    public void loadPencarian(){
        tabelPencarian.setModel(tableModel);
        sorter=new TableRowSorter<>(tabelPencarian.getModel());
        tabelPencarian.setRowSorter(sorter);
        txtCari.getDocument().addDocumentListener(this);
    }
    
    public String ambilData(){
        setLocationRelativeTo(null);
        setVisible(true);
        return id;
    }
    
    public void saring(){
        String text = txtCari.getText();
        if(text.length() == 0){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter(Pattern.compile("(?i).*"+text+".*", 
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL).toString()));
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrolPencarian = new javax.swing.JScrollPane();
        tabelPencarian = new com.stripbandunk.jwidget.JDynamicTable();
        jLabel1 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        bAdd = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        scrolPencarian.setViewportView(tabelPencarian);

        jLabel1.setText("Masukan Kode :");

        bAdd.setText("Add");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        jPanel1.add(bAdd);

        bCancel.setText("Cancel");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });
        jPanel1.add(bCancel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrolPencarian, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrolPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        // TODO add your handling code here:
        id=null;
        dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        // TODO add your handling code here:
        if(tabelPencarian.getSelectedRow()!=-1){
            int index=tabelPencarian.getSelectedRow();
            id=(String) tabelPencarian.getValueAt(index, 0);
            dispose();
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        id=null;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrolPencarian;
    private com.stripbandunk.jwidget.JDynamicTable tabelPencarian;
    private javax.swing.JTextField txtCari;
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
}
