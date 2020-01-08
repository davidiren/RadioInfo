package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SverigesRadio {

    private ProgramLoader programLoader = new ProgramLoader();
    private ArrayList<Channel> channelList = new ArrayList<>();
    private LinkedHashMap<String, Channel> channels = new LinkedHashMap<>();

    public SverigesRadio(){

    }

    public void setUp(){
        programLoader.parsePrograms();
        channelList = programLoader.getChannelList();
        createHashMap();
    }

    private void createHashMap() {
        for (Channel c:channelList) {
            channels.put(c.getName(),c);
        }
    }

    public LinkedHashMap<String ,Channel> getChannelsList() {
        return channels;
    }
}
