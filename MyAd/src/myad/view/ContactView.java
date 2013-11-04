/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.swing.UnsupportedLookAndFeelException;
import myad.XMLReader;
import myad.model.CommunicationChannel.Phone;
import myad.model.Contact;

/**
 *
 * @author FUJITSU
 */
public class ContactView extends javax.swing.JFrame {

    private Contact contact;
    JLabel nameL;
    JLabel roleL;
    javax.swing.JComboBox<String> phoneType;
    JTextArea phoneDetails;
    XMLReader rd;
    
    /**
     * Creates new form ContactView
     */
    public ContactView(Contact con) throws Exception {
//        get Contact abstraction
        contact = con;
        
//        set nimbus look and feel
        try {
            for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
//                System.out.println("nama laf: "+info.getName());
                if("Nimbus".equals(info.getName()))
                {
//                    System.out.println("nimbux");
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        
        initComponents();
        
        this.setTitle("M.Y.A.D");

//        layout configuration
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        
        //baca file xml
        rd = new XMLReader("my.xml");
//        System.out.println(rd.Phone(1, "type"));
//        System.out.println(rd.getName());
        
//        components definition
        nameL = new JLabel(rd.getName(),SwingConstants.CENTER);
        roleL = new JLabel(rd.getRole());
        
//        custom font
        Font newFont = new Font(nameL.getName(), Font.ITALIC, nameL.getFont().getSize());
        
//        change name + role font
        nameL.setFont(newFont);
        roleL.setFont(newFont);                        
        
//        susun string address
        StringBuilder addrBuilder = new StringBuilder();
//        addrBuilder.append("Street: ").append(contact.getAddress().getStreet()).append("\n");
        addrBuilder.append("Street: ").append(rd.Address("street")).append("\n");
        addrBuilder.append("Zip Postal Code: ").append(rd.Address("zippostalcode")).append("\n");
        addrBuilder.append("City: ").append(rd.Address("city")).append("\n");
        addrBuilder.append("State Province: ").append(rd.Address("stateprovince")).append("\n");
        addrBuilder.append("Country Name: ").append(rd.Address("countryname")).append("\n");
        JTextArea addressL =  new JTextArea(addrBuilder.toString());
        addressL.setEditable(false);
        addressL.setBackground(new Color(253, 253, 193));
        
//        isi pilihan phone type
        int numberPhoneTypes = rd.getPhoneTypes();
        String[] phoneTypeArr = new String[numberPhoneTypes];
        for(int i = 0; i<numberPhoneTypes; i++)
        {
            phoneTypeArr[i] = rd.Phone(i+1, "type");
        }
        phoneType = new JComboBox<>(phoneTypeArr);
        
//        cari selected item - priority 1
        int firstPhoneIdx =  0;
        phoneType.setSelectedIndex(firstPhoneIdx);
        
//        mengisi section phone details
        phoneDetails = new JTextArea();
        phoneDetails.setBackground(new Color(253, 253, 193));
        this.setPhoneDetails(firstPhoneIdx);
        
//        set ActionListener to ComboBox
        phoneType.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox) e.getSource();
                int idx = combo.getSelectedIndex();
                
                ContactView.this.setPhoneDetails(idx);
            }
        });
        
//        insert components to layout
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameL)
                .addComponent(roleL)
                .addComponent(addressL));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(phoneType)
                .addComponent(phoneDetails));
        layout.setHorizontalGroup(hGroup);
        
//        menyamakan ukuran label
        layout.linkSize(SwingConstants.HORIZONTAL, nameL, roleL);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(roleL)
                .addComponent(phoneType));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameL));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(addressL)
                .addComponent(phoneDetails));
        layout.setVerticalGroup(vGroup);
        
//        put these altogether to the frame
        this.pack();
    }
    
    private void setPhoneDetails(int idx)
    {
        Phone selPhone = contact.getPhone()[idx];
        
        StringBuilder phoneStrBuilder = new StringBuilder();
        phoneStrBuilder.append("Type: ").append(rd.Phone(idx+1, "type")).append("\n");
        phoneStrBuilder.append("Phone Number: ").append(rd.Phone(idx+1, "phonenumber")).append("\n");
        phoneStrBuilder.append("Area Code: ").append(rd.Phone(idx+1, "areacode")).append("\n");
        phoneStrBuilder.append("Subscriber No: ").append(rd.Phone(idx+1, "subscriberno")).append("\n");
        
        this.phoneDetails.setText(phoneStrBuilder.toString());
        this.phoneDetails.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The pane of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(600, 300));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 599, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 512, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ContactView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ContactView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ContactView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ContactView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ContactView().setVisible(true);
//            }
//        });
//    }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}