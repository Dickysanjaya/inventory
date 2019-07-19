/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.dialog;

import javax.swing.JDialog;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author ayu
 */
public class TampilReport extends JDialog{
    
    private String title;
    private JRViewer jv;

    public TampilReport(String title, JRViewer jv) {
        this.title = title;
        this.jv = jv;
        atur();
    }
    
    private void atur(){
        setSize(1200, 750);
        setModal(true);
        setTitle(title);
        getContentPane().add(jv);
        setVisible(true);
    }
    
}
