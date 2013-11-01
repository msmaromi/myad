/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.misc.IOUtils;

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
    boolean phone1Pass = false;
    boolean phone2Pass = false;
    boolean addressPass = false;
    for (int i = 0; i < nl.getLength(); i++) {                  
      an = nl.item(i);      
      if(an.getNodeType()==Node.ELEMENT_NODE) {                
        NodeList nl2 = an.getChildNodes();
        if (!rolePass) {
          con.role = an.getFirstChild().getNodeValue();
          rolePass = true;          
        } else if (!namePass) {
          con.name = an.getFirstChild().getNodeValue();
          namePass = true;
        } else if (!phone1Pass) {
          HashMap<String, String> map = new HashMap<String, String>();
          for(int i2=0; i2<nl2.getLength(); i2++) {
            an2 = nl2.item(i2);
            if (an2.getNodeType()==Node.ELEMENT_NODE) {
              map.put(filterAdsml(an2.getNodeName()).toLowerCase(), an2.getFirstChild().getNodeValue());              
            }                                          
          }
          con.comChannelPhone.put("phone1", map);
          phone1Pass = true;          
          
        } else if (!phone2Pass) {
          HashMap<String, String> map = new HashMap<>();
          for(int i2=0; i2<nl2.getLength(); i2++) {
            an2 = nl2.item(i2);
            if (an2.getNodeType()==Node.ELEMENT_NODE) {              
              map.put(filterAdsml(an2.getNodeName()).toLowerCase(), an2.getFirstChild().getNodeValue());                  
            }            
          }
          con.comChannelPhone.put("phone2", map);
          phone2Pass = true;
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
