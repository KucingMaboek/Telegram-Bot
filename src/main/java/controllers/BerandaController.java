package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import utils.Helper;

import java.util.Timer;
import java.util.TimerTask;

public class BerandaController {

    @FXML
    private Text txt_internetConnection, txt_botConnection, txt_twitterConnection, txt_databaseConnection;

    @FXML
    void btn_botReconnect(ActionEvent event) {
    }

    @FXML
    void btn_databaseReconnect(ActionEvent event) {
        Helper.connectDatabase();
        txt_databaseConnection.setText(Helper.getDbConnectionStats());
    }

    @FXML
    void btn_internetReconnect(ActionEvent event) {
    }

    @FXML
    void btn_twitterReconnect(ActionEvent event) {
    }

    @FXML
    private AnchorPane ap_status_net, ap_status_bot, ap_status_twit, ap_status_db;


    @FXML
    public void initialize() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try{
                    if (Helper.getNetConnectionStats().equals("Terhubung")) {
                        ap_status_net.setStyle("-fx-background-color: rgba(168, 223, 101,0.6);");
                    } else{
                        ap_status_net.setStyle("-fx-background-color: rgba(238, 133, 114,0.6);");
                    }
                    txt_internetConnection.setText(Helper.getNetConnectionStats());

                    if (Helper.getBotConnectionStats().equals("Terhubung")) {
                        ap_status_bot.setStyle("-fx-background-color: rgba(168, 223, 101,0.6);");
                    } else{
                        ap_status_bot.setStyle("-fx-background-color: rgba(238, 133, 114,0.6);");
                    }
                    txt_botConnection.setText(Helper.getBotConnectionStats());

                    if (Helper.getTwConnectionStats().equals("Terhubung")) {
                        ap_status_twit.setStyle("-fx-background-color: rgba(168, 223, 101,0.6);");
                    } else{
                        ap_status_twit.setStyle("-fx-background-color: rgba(238, 133, 114,0.6);");
                    }
                    txt_twitterConnection.setText(Helper.getTwConnectionStats());

                    if (Helper.getDbConnectionStats().equals("Terhubung")) {
                        ap_status_db.setStyle("-fx-background-color: rgba(168, 223, 101,0.6);");
                    } else{
                        ap_status_db.setStyle("-fx-background-color: rgba(238, 133, 114,0.6);");
                    }
                    txt_databaseConnection.setText(Helper.getDbConnectionStats());
                } catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1000;
        timer.scheduleAtFixedRate(timerTask, delay, intervalPeriod);

    }

}
