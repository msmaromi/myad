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
  private HashMap<String, String> content;
  
  public XMLReader(String fileName) throws Exception {
    content = new HashMap<>();
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
    for (int i = 0; i < nl.getLength(); i++) {
      an = nl.item(i);
      if(an.getNodeType()==Node.ELEMENT_NODE) {
        NodeList nl2 = an.getChildNodes();
        for(int i2=0; i2<nl2.getLength(); i2++) {
          an2 = nl2.item(i2);          
          content.put(an2.getParentNode().getNodeName(), an2.getNodeValue());          
        }
      }
    }
  }
  
  public String getValue(String tag) {
    return content.get(tag);
  }   
  
  public String getKey(int index) {    
    ArrayList<String> keys = new ArrayList<>(content.keySet());
    return keys.get(index);
  }
  
  public int contentSize() {
    return content.size();
  }
}