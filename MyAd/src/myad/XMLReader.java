/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author msmaromi
 */
public class XMLReader {
  private Contact con;
  
  public class Contact {
    private String role;
    private String name;
    private HashMap< String, HashMap<String, String> > comChannelPhone;
    private HashMap<String, String> comChannelAddress;            
    
    public Contact() {
      comChannelPhone = new HashMap< String, HashMap<String, String> >();
      comChannelAddress = new HashMap<String, String>();
    }
    
          
  }
  
  public XMLReader(String fileName) throws Exception {
    con = new Contact();
    extractXML(fileName);
  }
  
  public void extractXML(String fileName) throws Exception {  
    String xmlString = new Scanner(new File(fileName)).useDelimiter("\\Z").next();   
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes());
    Document doc = db.parse(bis);       
    Node n = doc.getFirstChild();
    NodeList nl = n.getChildNodes();
    Node an, an2;
    boolean rolePass = false;
    boolean namePass = false;
    boolean phonePass = false;
    boolean addressPass = false;
    int prioPhone = 1;
    for (int i = 0; i < nl.getLength(); i++) {                  
      an = nl.item(i);      
      if(an.getNodeType()==Node.ELEMENT_NODE) {           
        NodeList nl2 = an.getChildNodes();
        if (filterAdsml(an.getNodeName()).toLowerCase().equals("communicationchannel.physicaladdress")) {
          phonePass = true;
        }
        if (!rolePass) {
          con.role = an.getFirstChild().getNodeValue();
          rolePass = true;          
        } else if (!namePass) {
          con.name = an.getFirstChild().getNodeValue();
          namePass = true;
        } else if (!phonePass) {
          HashMap<String, String> map = new HashMap<String, String>();
          for(int i2=0; i2<nl2.getLength(); i2++) {
            an2 = nl2.item(i2);
            if (an2.getNodeType()==Node.ELEMENT_NODE) {
              
              map.put(filterAdsml(an2.getNodeName()).toLowerCase(), an2.getFirstChild().getNodeValue());              
              
            }                                          
          }
          String prio = "phone" + prioPhone;
          con.comChannelPhone.put(prio, map);
          System.out.println(prioPhone);          
          prioPhone++;
        } else if (!addressPass) {
          HashMap<String, String> map = new HashMap<>();
          for(int i2=0; i2<nl2.getLength(); i2++) {
            an2 = nl2.item(i2);
            if (an2.getNodeType()==Node.ELEMENT_NODE) {
              map.put(filterAdsml(an2.getNodeName()).toLowerCase(), an2.getFirstChild().getNodeValue());
            }                              
          }
          con.comChannelAddress = map;
          addressPass = true;
        }                    
      }
    }
  }
  
  public String filterAdsml(String s) { 
    return s.substring(6);
  }
  
  public String getRole() {
    return con.role;
  }       

  public String getName() {
    return con.name;
  }
  
  public String Phone(int prio, String attr) {
    String priority = "Phone"+prio;
    return con.comChannelPhone.get(priority.toLowerCase()).get(attr.toLowerCase());             
  }

  public String Address(String attr) {
    return con.comChannelAddress.get(attr.toLowerCase());
  }

  public int getPhoneTypes() {
    return con.comChannelPhone.size();
  }
//  public String getValue(String tag) {
//    return content.get(tag);
//  }   
//  
//  public String getKey(int index) {    
//    ArrayList<String> keys = new ArrayList<>(content.keySet());
//    return keys.get(index);
//  }
//  
//  public int contentSize() {
//    return content.size();
//  }
}
