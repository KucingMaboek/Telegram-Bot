package models;

public class GangguanModel {

    private String chatID;
    private String title;
    private String date;

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GangguanModel(String chatID, String title, String date) {
        this.chatID = chatID;
        this.title = title;
        this.date = date;
    }
}