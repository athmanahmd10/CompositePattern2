import java.io.File;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

class Prog1{

    File path;
    public String vXML=null;
    boolean isDirectory = false;
    int files = 0;
    public Prog1(){
        
    }

    public static String pathToXml( String vpath) {
        File path = new File(vpath);
        File [] list = path.listFiles();
        String vXML="<Directory name='"+path.getName()+"'>\n";
        if (path.exists()){
            for(File file: list) {   
                if(file.isDirectory()){
                    vXML=vXML+pathToXml(file.getPath());
                }
                else if(file.isFile()){
                    vXML=vXML+"<File name='"+file.getName()+"'/>\n";
                }
            }          
        }
        else{
            System.out.println("ce chemin n'existe pas");
        }
        vXML = vXML+"</Directory>\n";
        return vXML;
    }


    public Component XmlToObject(String vPath) throws ParserConfigurationException, SAXException, IOException{
        String myString = pathToXml(vPath);
        System.out.println(myString); 
        myString = "<?xml version='1.0'?>"+myString;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(myString);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));

        Document doc = builder.parse(input);
        Element element = doc.getDocumentElement();
        return (Component)insertElement(element);  
    }

    public Folder insertElement(Element element){
       Folder folder1 = new Folder(element.getAttribute("name"));
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) childNodes.item(i);
                // System.out.println(eElemnt.getAttribute("name")); 
                if(eElement.getNodeName().equals("File")){
                    // System.out.println(eElement.getAttribute("name")); 
                    Component fic = new Fic(eElement.getAttribute("name"));
                    folder1.addElement(fic);
                }else if(eElement.getNodeName().equals("Directory")){
                    folder1.addElement(insertElement(eElement));
                }
            }
        }

    return folder1;
    }
}