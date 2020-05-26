package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utils.Helper;

public class LoginController {
    @FXML
    private Text txt_hint;

    @FXML
    private TextField tf_username, tf_password;

    @FXML
    void btn_Close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btn_Login(ActionEvent event) {
        String username = String.valueOf(tf_username.getText());
        String password = String.valueOf(tf_password.getText());
        if (username.equals("admin") && password.equals("admin")) {
            Helper.changeStage(event, "Main");
        } else if (username.isEmpty() || password.isEmpty()) {
            txt_hint.setText("*username/password harus diisi");
        } else{
            txt_hint.setText("*username/password salah");
        }
    }
}
