/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ayu
 */
public class Splash1 extends JPanel{
  private Image image;
  
  public Splash1(){
      image = new ImageIcon(getClass().getResource("/inventori/client/img/eshopSplash.png")).getImage();
      
  }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g.create();
        gd.drawImage(image, 0, 0, getWidth(),getHeight(),this);
        gd.dispose();
    }
  
  
  
}
