/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import myad.model.CommunicationChannel.Phone;
import myad.model.CommunicationChannel.PhysicalAddress;
import myad.model.Contact;
import myad.view.ContactView;

/**
 *
 * @author msmaromi
 */
public class Main {
    
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception
  {
      String role = "MediaBuyer";
      String name = "Jane Jones";

      PhysicalAddress phy = new PhysicalAddress();
      phy.setStreet("100 Broadway");
      phy.setZipPostalCode("10001");
      phy.setCity("New York");
      phy.setStateProvince("New York");
      phy.setCountryName("United States");

      Phone phone = new Phone();
      phone.setPriority(1);
      phone.setType("Voice");
      phone.setPhoneNumber("212-555-1516");
      phone.setAreaCode("212");
      phone.setSubscriberNo("5551516");
      
      Phone phonee = new Phone();
      phonee.setPriority(2);
      phonee.setType("Fax");
      phonee.setPhoneNumber("212-555-1515");
      phonee.setAreaCode("212");
      phonee.setSubscriberNo("5551515");
      
      Phone[] phoneArr = {phone, phonee};
      
      Contact contact = new Contact(role, name, phoneArr, phy);
      ContactView contactView = new ContactView(contact);
      
      contactView.setVisible(true);
  }
}