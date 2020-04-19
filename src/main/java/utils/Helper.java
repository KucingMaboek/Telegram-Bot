package utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    public static void changePage(Event event, String namaView) {
        Node node = (Node) event.getSource();
        Scene oldScene = node.getScene();
        Stage stage = (Stage) oldScene.getWindow();
        try {
            Parent newPage = FXMLLoader.load(Helper.class.getResource("../views/" + namaView + ".fxml"));
            Scene newScene = new Scene(newPage);
            newPage.getStylesheets().add(Helper.class.getResource("../styling/style.css").toExternalForm());
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error tidak ditemukan");
            e.printStackTrace();
        }
    }

    public static void changeStage(Event event, String namaView) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Helper.class.getResource("../views/" + namaView + ".fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Hello World");
            stage.setScene(scene);
            stage.setResizable(false);
            root.getStylesheets().add(Helper.class.getResource("../styling/style.css").toExternalForm());
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void moveToCenter(URL ui, BorderPane borderpane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ui);
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpane.setCenter(root);
    }

    private static final String HOSTNAME = "localhost";
    private static final String USERNAME = "miqdad";
    private static final String PASSWORD = "a";
    private static final String DATABASE = "praktikum";
    private static final int PORT = 3306;
    private static String URI = String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            HOSTNAME, PORT, DATABASE);

    public static Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Package mysql connector tidak ditemukan");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal");
            e.printStackTrace();
            return null;
        }
    }
}
