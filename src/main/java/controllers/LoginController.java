package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Helper;

import java.io.IOException;

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
        } else {
            txt_hint.setText("*username/password salah");
        }
    }

    private void changePage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/" + "Main" + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("src/main/resources/styling/style.css").toExternalForm());
        stage.show();
    }
}
