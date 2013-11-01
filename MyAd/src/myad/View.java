/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author msmaromi
 */
public class View extends JFrame {
  private JPanel panel;
  
  public View() throws Exception {
    setTitle("My Advertising");
    setSize(300, 200);
    
    panel = new JPanel();
    panel.setLayout(null);
    add(panel);
    
//    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
  }
  
  private void initComponents() throws Exception {
    XMLReader rd = new XMLReader("ad.xml");
    
    
  }  
}