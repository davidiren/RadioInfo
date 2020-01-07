package model;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ProgramLoader {

    ArrayList<Channel> channelList = new ArrayList<>();

    public ProgramLoader(){

    }

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
            /*URL episodesURL;
            for (Channel channel : channelList) {
                episodesURL = channel.getScheduleURL();
                do {
                    parser.parse(new InputSource(episodesURL.openStream()),
                            episodeHandler);
                    episodesURL = episodeHandler.getNextPage();
                } while (!episodeHandler.isFinished());
                channel.setEpisodeList(episodeHandler.getEpisodeList());
            }*/

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

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }
}
