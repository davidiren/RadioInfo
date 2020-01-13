package model;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class: ProgramLoader
 *
 * @author - David Irén
 *
 * Uses a SAXParser to parse the XML's gotten from the API
 * calls from SverigesRadio
 */
public class ProgramLoader {

    private ArrayList<Channel> channelList = new ArrayList<>();

    /**
     * Constructor
     */
    public ProgramLoader(){

    }

    /**
     * Does the API calls and parses the XML's
     */
    public void parsePrograms() {
        ChannelHandler handler = new ChannelHandler();
        EpisodeHandler episodeHandler = new EpisodeHandler();
        try {
            URL site = new URL("http://api.sr.se/api/v2/channels");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);

            SAXParser parser = factory.newSAXParser();
            //gets all channels
            do {
                parser.parse(new InputSource(site.openStream()), handler);
                site = handler.getNextPage();
            }while (!handler.isFinished());
            channelList = handler.getChannelList();

            //Adds all episodes to all channels
            URL episodesURL;
            String episodeString;
            //Do this to get 12h before and after
            LocalDateTime ldt = LocalDateTime.now();
            LocalDateTime minus = ldt.minusHours(12);
            LocalDateTime plus = ldt.plusHours(12);
            //format for correct api-call
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String twelveBefore = minus.format(dtf);
            String twelveAfter = plus.format(dtf);
            // to get all episodes in one xml-file, 12h before
            // and 12h after current time
            String everything = "&fromdate="+twelveBefore+"&todate="+twelveAfter +
                    "&pagination=false";
            for (Channel channel : channelList) {
                //don't try to search episodes that doesn't exist
                if (channel.getScheduleURL() == (null)){
                    continue;
                }
                episodeString = channel.getScheduleURL().toString();
                episodeString = episodeString + everything;
                episodesURL = new URL(episodeString);
                System.out.println(channel.getName());
                parser.parse(new InputSource(episodesURL.openStream()),
                        episodeHandler);

                channel.setEpisodeList(new ArrayList<>(
                        episodeHandler.getEpisodeList()));
            }

        } catch (ParserConfigurationException pce) {
            System.err.println("Cannot create parser");
            pce.printStackTrace();
        } catch (SAXException se) {
            System.err.println("SAX error");
            se.printStackTrace();
        } catch (FileNotFoundException fne) {
            //System.out.println("Website does not exist");
            //do nothing, website does not exist
            //will not be shown to user
        } catch (NullPointerException npe){
            System.out.println("this channel has no table");
        } catch (MalformedURLException e) {
            //System.out.println("MalformedURL");
            //This will only occur when the api gives a
            //faulty URL, this will be handled by not showing
            //that specific channel to the user
        } catch (IOException e) {
            System.out.println("Cant fetch programs from SR, Check internet " +
                    "connection");
        }
    }

    /**
     * getter for the channel list
     * @return - ArrayList
     */
    public ArrayList<Channel> getChannelList() {
        return channelList;
    }
}
