package model;

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
        createHashMap();
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
