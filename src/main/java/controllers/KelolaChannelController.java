package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.Bot;

import java.io.File;

public class KelolaChannelController {
    private final FileChooser fileChooser = new FileChooser();
    private File file;

    @FXML
    private TextArea ta_messageCaption;

    @FXML
    private TextField tf_currentFile;

    @FXML
    private Text txt_feedback;

    @FXML
    void btn_clearFile(ActionEvent event) {
        file = null;
        tf_currentFile.clear();
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
    void btn_sendBroadcast(ActionEvent event) {
        Bot bot = new Bot();
        String messages = ta_messageCaption.getText();
        try {
            bot.broadcast(file, messages);
            txt_feedback.setText("Broadcast berhasil terkirim");
        } catch (TelegramApiException e) {
            txt_feedback.setText(e.getMessage());
        }
    }

}
