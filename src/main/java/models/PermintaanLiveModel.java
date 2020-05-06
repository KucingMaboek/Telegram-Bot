package models;

public class PermintaanLiveModel {
    private int id;
    private String date;
    private String time;
    private String chatId;
    private String username;
    private String status;

    public PermintaanLiveModel(int id, String date, String time, String chatId, String username, String status) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.chatId = chatId;
        this.username = username;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
