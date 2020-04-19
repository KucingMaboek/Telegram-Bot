package models;

public class PermintaanLiveModel {
    private String chatID;
    private String status;
    private String date;

    public PermintaanLiveModel(String chatID, String status, String date) {
        this.chatID = chatID;
        this.status = status;
        this.date = date;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
