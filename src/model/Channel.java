package model;

import java.net.URL;

public class Channel {
    private int id;
    private String name;
    private URL image;
    private String color;
    private String tagline;
    private URL siteURL;
    private URL scheduleURL;
    private String channelType;

    public Channel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public URL getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(URL siteURL) {
        this.siteURL = siteURL;
    }

    public URL getScheduleURL() {
        return scheduleURL;
    }

    public void setScheduleURL(URL scheduleURL) {
        this.scheduleURL = scheduleURL;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
