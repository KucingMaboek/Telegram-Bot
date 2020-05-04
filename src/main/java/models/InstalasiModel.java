package models;

public class InstalasiModel {

    private String chatID;
    private String title;
    private String date;
//    private String layanan;
//    private String peruntukan;
//    private int daya;
//    private int tokenPerdana;
//    private String alamat;
//    private String nama;
//    private String NoKtp;
//    private String NoHp;


    public InstalasiModel(String chatID, String title, String date) {
        this.chatID = chatID;
        this.title = title;
        this.date = date;
    }

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
}
