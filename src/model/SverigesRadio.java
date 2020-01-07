package model;

import java.util.ArrayList;

public class SverigesRadio {

    private ProgramLoader programLoader = new ProgramLoader();
    private ArrayList<Channel> channelList = new ArrayList<>();

    public SverigesRadio(){

    }

    public void setUp(){
        programLoader.parsePrograms();
        channelList = programLoader.getChannelList();
    }
}
