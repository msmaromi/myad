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
