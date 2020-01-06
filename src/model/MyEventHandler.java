package model;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * Handler used for parsing the XML file
 * @author David Irén
 */
public class MyEventHandler extends DefaultHandler {

    private final Stack<String> elementStack = new Stack<>();

    @Override
    public void startElement(String namespace, String localName, String qName,
                             Attributes attr) {
        this.elementStack.push(qName);
        if ("level".equals(qName)) {

            }
        }


    /**
     * Called when the end of an element is reached in the xml
     * @param uri -
     * @param localName -
     * @param qName - name of element
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        this.elementStack.pop();
        // A level has been made, move to levelList
        if ("level".equals(qName)) {

        }
    }

    /**
     * This will be called every time parser encounters a value node
     * */
    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch,start,length).trim();
        if (value.length() == 0) {
            return; // ignore whitespace
        }
        //Create a zone to put into level
        if ("zone".equals(currentElement())) {

        }
    }

    public String currentElement(){
        return this.elementStack.peek();
    }

}
