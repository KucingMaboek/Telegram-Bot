package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import utils.Helper;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {

    @FXML
    private Label txt_BreadCrumb;

    @FXML
    public BorderPane bp_MainPane;

    @FXML
    void btn_Beranda(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "Beranda" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Beranda");
    }

    @FXML
    void btn_Gangguan(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "LapGangguan" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Gangguan");
    }

    @FXML
    void btn_Instalasi(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "LapInstalasi" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Laporan Instalasi Listrik");
    }

    @FXML
    void btn_Kecurangan(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "LapKecurangan" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Laporan Kecurangan Listrik");
    }

    @FXML
    void btn_KelolaChannel(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "KelolaChannel" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Kelola Channel");
    }

    @FXML
    void btn_LiveChat(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "PermintaanLive" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Permintaan Live Chat");
    }

    @FXML
    void btn_Pengaturan(ActionEvent event) {
        Helper.moveToCenter(getClass().getResource("../views/" + "PengaturanBot" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Pengaturan Bot");
    }

    @FXML
    public void initialize() {
        Helper.moveToCenter(getClass().getResource("../views/" + "Beranda" + ".fxml"), bp_MainPane);
        txt_BreadCrumb.setText("/ Beranda");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Helper.checkInternet();
                Helper.connectBot();
                Helper.connectTwitter();
                Helper.connectDatabase();
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intervalPeriod = 300 * 1000;
        timer.scheduleAtFixedRate(timerTask, delay, intervalPeriod);


    }
}
