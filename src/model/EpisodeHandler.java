package model;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Stack;

/**
 * Class: EpisodeHandler
 *
 * @author David Irén
 *
 * Handler used for parsing the XML files with episodes
 */
public class EpisodeHandler extends DefaultHandler {

    private final Stack<String> elementStack = new Stack<>();
    private ArrayList<Episode> episodeList = new ArrayList<>();
    private int indexOfCurrentEpisode = 0;

    /**
     * Start element in xml
     * @param namespace - String
     * @param localName - String
     * @param qName - String
     * @param attr - Attributes
     */
    @Override
    public void startElement(String namespace, String localName, String qName,
                             Attributes attr) {
        this.elementStack.push(qName);
        if ("scheduledepisode".equals(qName)) {
            episodeList.add(new Episode());
        }
        if ("program".equals(qName)) {
            episodeList.get(indexOfCurrentEpisode)
                    .setProgramId(Integer.parseInt(
                            attr.getValue("id")));
            episodeList.get(indexOfCurrentEpisode)
                    .setProgramName(attr.getValue("name"));
        }
        if ("schedule".equals(qName)){
            episodeList.clear();
            indexOfCurrentEpisode = 0;
        }
    }


    /**
     * Called when the end of an element is reached in the xml
     * @param uri - String
     * @param localName - String
     * @param qName - name of element
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        this.elementStack.pop();
        // A level has been made, move to levelList
        if ("scheduledepisode".equals(qName)) {
            //hasEpisodeAired();
            indexOfCurrentEpisode++;
        }
    }

    /*private void hasEpisodeAired() {
        try {
            Date today = new Date();
            String strDateFormat = "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            String formatedToday = sdf.format(today);

            Date now = sdf.parse(formatedToday);

            String endTime =
                    episodeList.get(indexOfCurrentEpisode).getEndTime();
            String epDate = endTime.substring(0, endTime.indexOf("Z"));
            Date ep;
            SimpleDateFormat another = new SimpleDateFormat(strDateFormat);
                    ep = another.parse(epDate);

            if (now.compareTo(ep) > 0) {
                episodeList.get(indexOfCurrentEpisode).setAlreadyShown(true);
            } else if (now.compareTo(ep) < 0) {
                episodeList.get(indexOfCurrentEpisode).setAlreadyShown(false);
            } else {
                episodeList.get(indexOfCurrentEpisode).setAlreadyShown(true);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

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
            if ("episodeid".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode)
                        .setId(Integer.parseInt(value));
            }
            if ("title".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode).setTitle(value);
            }
            if ("description".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode).setDescription(value);
            }
            if ("starttimeutc".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode).setStartTime(value);
            }
            if ("endtimeutc".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode).setEndTime(value);
            }
            if ("imageurl".equals(currentElement())) {
                episodeList.get(indexOfCurrentEpisode)
                        .setImageURL(new URL(value));
            }
        }catch (MalformedURLException e) {
            System.out.println("malformedURL: "+e.getLocalizedMessage());
        }
    }

    /**
     * checks top of element stack
     * @return - String
     */
    public String currentElement(){
        return this.elementStack.peek();
    }

    /**
     * getter for episode list
     * @return - ArrayList
     */
    public ArrayList<Episode> getEpisodeList() {
        return episodeList;
    }
}
