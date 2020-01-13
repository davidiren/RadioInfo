package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class: SverigesRadio
 *
 * @author - David Irén
 *
 * Holds the programloader and information about all channels
 */
public class SverigesRadio {

    private ProgramLoader programLoader = new ProgramLoader();
    private ArrayList<Channel> channelList = new ArrayList<>();
    private LinkedHashMap<String, Channel> channels = new LinkedHashMap<>();

    /**
     * Constructor
     */
    public SverigesRadio(){

    }

    /**
     * makes the programloader parse xml
     */
    public void setUp(){
        programLoader.parsePrograms();
        channelList = programLoader.getChannelList();
        fixEpisodeTweleveBeforeAndAfter();
        createHashMap();

    }

    private void fixEpisodeTweleveBeforeAndAfter() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twelveAgo = now.minusHours(12);
        LocalDateTime twelveTo = now.plusHours(12);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH" +
                ":mm:ss'Z'");
        for (Channel c:channelList) {
            c.getEpisodeList().removeIf(episode -> {
                LocalDateTime checkThis =
                        LocalDateTime.parse(episode.getEndTime().substring(0,
                         episode.getEndTime().length()-1));
                return twelveAgo.isAfter(checkThis) || twelveTo.isBefore(checkThis);
            });

        }
    }

    /**
     * create the HashMap with information from SverigesRadio
     */
    private void createHashMap() {
        for (Channel c:channelList) {
            channels.put(c.getName(),c);
        }
    }

    /**
     * getter for the HashMap
     * @return - LinkedHashMap
     */
    public LinkedHashMap<String ,Channel> getChannelsList() {
        return channels;
    }
}
