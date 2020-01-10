package model;


import java.net.URL;

public class Episode {

    private int id;
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private int programId;
    private String programName;
    private URL imageURL;
    private boolean alreadyShown;

    public Episode(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getAlreadyShown() {
        return alreadyShown;
    }

    public void setAlreadyShown(boolean alreadyShown) {
        this.alreadyShown = alreadyShown;
    }
}
