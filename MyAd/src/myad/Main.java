/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author msmaromi
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
          View v;
        try {
          v = new View();
                  v.setVisible(true);
        } catch (Exception ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }
    });
//    XMLReader rd = new XMLReader("ad.xml");
//    for (int i = 0; i < rd.contentSize(); i++) {
//      System.out.println(rd.getValue(rd.getKey(i)));
//    }
  }
}
