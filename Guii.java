import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Guii {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String vPath = "../";
        Prog1 prog = new Prog1();
        Component folder1 = new Folder("testxml");
        folder1 = prog.XmlToObject(vPath);
        folder1.showElement();
    }
}
