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
            URL episodesURL;
            String episodeString;
            String paginationFalse = "&pagination=false";
            for (Channel channel : channelList) {
                if (channel.getScheduleURL() == (null)){
                    continue;
                }
                episodeString = channel.getScheduleURL().toString();
                episodeString = episodeString + paginationFalse;
                episodesURL = new URL(episodeString);
                System.out.println(channel.getName());
                parser.parse(new InputSource(episodesURL.openStream()),
                        episodeHandler);

                channel.setEpisodeList(new ArrayList<>(episodeHandler.getEpisodeList()));
            }

        } catch (ParserConfigurationException pce) {
            System.err.println("Cannot create parser");
            pce.printStackTrace();
        } catch (SAXException se) {
            System.err.println("SAX error");
            se.printStackTrace();
        } catch (FileNotFoundException fne) {
            System.out.println("WebbPage does not exist");
        } catch (NullPointerException npe){
            System.out.println("this channel has no table");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }
}
