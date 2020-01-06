package model;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class ProgramLoader {

    public ProgramLoader(){

    }

    public void parsePrograms(String fileName) {
        MyEventHandler handler = new MyEventHandler();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL("http://api.sr.se/api/v2/channels").openStream()), handler);


        } catch (ParserConfigurationException pce) {
            System.err.println("Cannot create parser");
            pce.printStackTrace();
        } catch (SAXException se) {
            System.err.println("SAX error");
            se.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("IO error");
            ioe.printStackTrace();
        }
    }

}
