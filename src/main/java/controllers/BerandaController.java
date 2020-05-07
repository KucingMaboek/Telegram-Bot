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
    private AnchorPane ap_status_net;


    @FXML
    public void initialize() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                txt_botConnection.setText(Helper.getBotConnectionStats());
                txt_twitterConnection.setText(Helper.getTwConnectionStats());
                txt_databaseConnection.setText(Helper.getDbConnectionStats());
                txt_internetConnection.setText(Helper.getNetConnectionStats());
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 1000;
        timer.scheduleAtFixedRate(timerTask, delay,intervalPeriod);

    }

}
