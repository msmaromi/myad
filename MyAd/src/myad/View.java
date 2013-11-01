/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
    
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    initComponents();
  }
  
  private void initComponents() throws Exception {
    XMLReader rd = new XMLReader("ad.xml");
    int size = rd.contentSize();
    System.out.println(size);
    ArrayList<JLabel> labelsKey = new ArrayList<JLabel>(size);
    ArrayList<JLabel> labelsVal = new ArrayList<JLabel>(size);
    for (int i = 0; i < size; i++) {
      System.out.println(i);
      String key = rd.getKey(i);
      
      labelsKey.add(new JLabel(key));
      labelsVal.add(new JLabel(rd.getValue(key)));                                        
      
      int gap = i*1;
      Insets insets = panel.getInsets();      
      
      labelsKey.get(i).setLocation(10, 10+0);
      labelsVal.get(i).setLocation(30, 10+0);
      
      panel.add(labelsKey.get(i));
      panel.add(labelsVal.get(i)); 
    }        
    
  }
  
}
