package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.Bot;

import java.io.File;
import java.io.IOException;

public class MainController {
    private final FileChooser fileChooser = new FileChooser();
    private File file;

    @FXML
    private Text txt_feedback;

    @FXML
    private AnchorPane pnl_Beranda, pnl_Instalasi, pnl_Gangguan, pnl_Kecurangan, pnl_LiveChat, pnl_KelolaChannel, pnl_Pengaturan;

    @FXML
    private Label txt_BreadCrumb;

    @FXML
    private TextArea ta_messageCaption;

    @FXML
    private TextField tf_currentFile;

    @FXML
    void btn_Beranda(ActionEvent event) {
        txt_BreadCrumb.setText("/ Beranda");
        pnl_Beranda.toFront();
    }

    @FXML
    void btn_Instalasi(ActionEvent event) {
        txt_BreadCrumb.setText("/ Laporan Instalasi Listrik");
        pnl_Instalasi.toFront();
    }

    @FXML
    void btn_Gangguan(ActionEvent event) {
        txt_BreadCrumb.setText("/ Laporan Gangguan Listrik");
        pnl_Gangguan.toFront();
    }


    @FXML
    void btn_Kecurangan(ActionEvent event) {
        txt_BreadCrumb.setText("/ Laporan Kecurangan Listrik");
        pnl_Kecurangan.toFront();
    }

    @FXML
    void btn_LiveChat(ActionEvent event) {
        txt_BreadCrumb.setText("/ Permintaan Live Chat");
        pnl_LiveChat.toFront();
    }

    @FXML
    void btn_KelolaChannel(ActionEvent event) {
        txt_BreadCrumb.setText("/ Kelola Channel");
        pnl_KelolaChannel.toFront();
    }

    @FXML
    void btn_Pengaturan(ActionEvent event) {
        txt_BreadCrumb.setText("/ Pengaturan telegram.Bot");
        pnl_Pengaturan.toFront();
    }

    @FXML
    void btn_searhFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            tf_currentFile.setText(toString().valueOf(file));
            tf_currentFile.disabledProperty();
        }
    }


    @FXML
    void btn_clearFile(ActionEvent event) {
        file = null;
        tf_currentFile.clear();
    }


    @FXML
    void btn_sendBroadcast(ActionEvent event) {
        Bot bot = new Bot();
        String messages = ta_messageCaption.getText();
        try {
            bot.sendPhotoToChannel(file, messages);
        } catch (TelegramApiException e) {
            txt_feedback.setText(e.getMessage());
        }
    }

    private void changePage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/home/fxml/TableView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
