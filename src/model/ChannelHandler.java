package model;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class: ChannelHandler
 *
 * @author David Irén
 *
 * Handler used for parsing the XML file with channels
 */
public class ChannelHandler extends DefaultHandler {

    private final Stack<String> elementStack = new Stack<>();
    private ArrayList<Channel> channelList = new ArrayList<>();
    private int indexOfCurrentChannel = 0;
    private boolean finished = false;
    private int pageNr;
    private int nrOfPages;
    private URL nextPage;

    @Override
    public void startElement(String namespace, String localName, String qName,
                             Attributes attr) {
        this.elementStack.push(qName);
        if ("channel".equals(qName)) {
            Channel channel = new Channel();
            channel.setId(Integer.parseInt(attr.getValue("id")));
            channel.setName(attr.getValue("name"));
            channelList.add(channel);
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
        if ("channel".equals(qName)) {
            indexOfCurrentChannel++;
        }
        if ("sr".equals(qName)) {
            pageNr++;
            if (pageNr > nrOfPages){
                finished = true;
            }
        }
    }

    /**
     * This will be called every time parser encounters a value node
     * @param ch - char[]
     * @param start - int
     * @param length - int
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch,start,length).trim();
        if (value.length() == 0) {
            return; // ignore whitespace
        }
        try {
            //Create a zone to put into level
            if ("page".equals(currentElement())) {
                pageNr = Integer.parseInt(value);
            }
            if ("totalpages".equals(currentElement())) {
                nrOfPages = Integer.parseInt(value);
            }
            if ("nextpage".equals(currentElement())) {
                nextPage = new URL(value);
            }
            if ("image".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setImage(new URL(value));
            }
            if ("color".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setColor(value);
            }
            if ("tagline".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setTagline(value);
            }
            if ("siteurl".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setSiteURL(new URL(
                        value));
            }
            if ("scheduleurl".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setScheduleURL(new URL(
                        value));
            }
            if ("channeltype".equals(currentElement())) {
                channelList.get(indexOfCurrentChannel).setChannelType(value);
            }
        }catch (MalformedURLException e) {
            //URL is malformed, will be handled later in the process
            //by showing a "noimage" image or not showing channel
            //depending on which URL is faulty
        }
    }

    /**
     * Checks top of stack
     * @return - String
     */
    public String currentElement(){
        return this.elementStack.peek();
    }

    /**
     * Getter for channel list
     * @return - ArrayList
     */
    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    /**
     * @return - boolean, if parser is done or not
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * gets the next page of xml
     * @return - URL
     */
    public URL getNextPage() {
        return nextPage;
    }
}
