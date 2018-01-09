//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fromparse.uitl;

import fromparse.FromBean.SpecialBean;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SpecialHandler {
    private File file;
    private static SpecialHandler specialHandlers = null;

    private SpecialHandler(File file) {
        this.file = file;
    }

    public static SpecialHandler getInstance(File file) {
        if(specialHandlers == null) {
            specialHandlers = new SpecialHandler(file);
        }

        return specialHandlers;
    }

    public List<SpecialBean> Filter() {
        ArrayList list = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder e = factory.newDocumentBuilder();
            Document document = e.parse(this.file);
            Element root = document.getDocumentElement();
            NodeList nodelist = root.getElementsByTagName("node");

            for(int i = 0; i < nodelist.getLength(); ++i) {
                Element element = (Element)nodelist.item(i);
                String formname = element.getAttribute("formname");
                String location = element.getAttribute("location");
                String fieldName = element.getTextContent();
                SpecialBean specialBean = new SpecialBean();
                specialBean.setFormname(formname);
                specialBean.setFieldName(fieldName);
                specialBean.setLocation(location);
                list.add(specialBean);
            }
        } catch (ParserConfigurationException var13) {
            var13.printStackTrace();
        } catch (SAXException var14) {
            var14.printStackTrace();
        } catch (IOException var15) {
            var15.printStackTrace();
        }

        return list;
    }
}
