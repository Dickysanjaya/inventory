/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.dialog;

import com.stripbandunk.jwidget.model.DynamicTableModel;
import dao.BarangDao;
import dao.PenjualanDao;
import entity.Barang;
import entity.Gudang;
import entity.Penjualan;
import entity.PenjualanDetail;
import inventori.client.view.BarangView;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ayu
 */
public class PencarianReturn extends javax.swing.JDialog {
    
    private BarangDao barangDao;
    private Barang barang;
    private int jumlah;
    private List penjualan=new ArrayList<>();
    private Penjualan pnj;
    private PenjualanDao penjualanDao;
    PenjualanDetail penjualanDetil1;
    public PencarianReturn(PenjualanDao penjualanDao) {
        this.penjualanDao=penjualanDao;
        setModal(true);
        initComponents();
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public PenjualanDetail getPenjualanDetil() {
        return penjualanDetil1;
    }
    
    public void load(List penjualan, Penjualan pnj){
        this.penjualan=penjualan;
        this.pnj=pnj;
        setTitle("Pencarian Penjualan");
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    
    public void loadUpdate(Gudang g){
        setTitle("Pencarian Barang");
        setLocationRelativeTo(getParent());
        bCari.setEnabled(false);
        setVisible(true);
    }
    
    public void update(PenjualanDetail pd, Gudang g){
        setTitle("Ubah Barang");
        bCari.setEnabled(false);
        barang=pd.getBarang();
        jumlah=pd.getJumlah().intValue();
        txtBarang.setText(String.valueOf(pd.getBarang()));
        txtJumlah.setText(String.valueOf(pd.getJumlah()));
        setLocationRelativeTo(getParent());
        setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        bCari = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        bAmbil = new javax.swing.JButton();
        bBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("Barang :");

        jLabel2.setText("Jumlah :");

        bCari.setText("Cari");
        bCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtJumlah)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCari)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bCari))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bAmbil.setText("Ambil");
        bAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAmbilActionPerformed(evt);
            }
        });
        jPanel2.add(bAmbil);

        bBatal.setText("Batal");
        bBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBatalActionPerformed(evt);
            }
        });
        jPanel2.add(bBatal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCariActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            if(!penjualan.isEmpty()){
                DynamicTableModel tableModel=new DynamicTableModel(penjualan, PenjualanDetail.class);
                Pencarian1 pencarian=new Pencarian1();
                pencarian.setTitle("Pencarian Penjualan");
                pencarian.setTableModel(tableModel);
                pencarian.loadPencarian();
                String ambilData = pencarian.ambilData();
                if(ambilData!=null){
                    penjualanDetil1 = penjualanDao.getPenjualanDetil(pnj, ambilData);
                    barang=penjualanDetil1.getBarang();
                    txtBarang.setText(barang.getNm_brg());
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Penjualan masih kosong !");
            }
        } catch (RemoteException ex) {
            Logger.getLogger(PencarianReturn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCariActionPerformed

    private void bBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBatalActionPerformed
        // TODO add your handling code here:
        barang=null;
        jumlah=0;
        dispose();
    }//GEN-LAST:event_bBatalActionPerformed

    private void bAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAmbilActionPerformed
        // TODO add your handling code here:
        if(txtBarang.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Barang Masih Kosong !");
        }else if(txtJumlah.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(rootPane, "Jumlah Masih Kosong !");
        }else{
            jumlah=Integer.valueOf(txtJumlah.getText());
            if(penjualanDetil1.getJumlah()<jumlah){
                JOptionPane.showMessageDialog(rootPane, "Jumlah Retrurn terlalu banayak ! jumlah = "+penjualanDetil1.getJumlah());
            }else{
                dispose();
            }
        }
    }//GEN-LAST:event_bAmbilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAmbil;
    private javax.swing.JButton bBatal;
    private javax.swing.JButton bCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtBarang;
    private javax.swing.JTextField txtJumlah;
    // End of variables declaration//GEN-END:variables
}
