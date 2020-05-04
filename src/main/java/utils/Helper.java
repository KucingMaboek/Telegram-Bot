package utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.TwitterException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    private static String netConnectionStats;
    private static String dbConnectionStats;
    private static String botConnectionStats;
    private static String twConnectionStats;

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
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "telegrambot";
    private static final int PORT = 3306;
    private static String URI = String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
            HOSTNAME, PORT, DATABASE);

    public static Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            setDbConnectionStats("Terhubung");
            return DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            setDbConnectionStats("Package mysql connector tidak ditemukan");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            setDbConnectionStats("Koneksi database gagal");
            return null;
        }
    }

    public static void connectBot() {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(bot);
            setBotConnectionStats("Terhubung");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            setBotConnectionStats("Koneksi bot gagal");
        }
    }

    public static void connectTwitter() {
        TwitterHandler twitterHandler = new TwitterHandler();
        try {
            twitterHandler.configTwitter();
            setTwConnectionStats("Terhubung");
        } catch (TwitterException e) {
            e.printStackTrace();
            setTwConnectionStats("Koneksi twitter gagal");
        }
    }

    public static void checkInternet() {
        try {
            URL url = new URL("https://www.geeksforgeeks.org/");
            URLConnection connection = url.openConnection();
            connection.connect();
            setNetConnectionStats("Terhubung");
        } catch (Exception e) {
            e.printStackTrace();
            setNetConnectionStats("Tidak ada akses internet");
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getDbConnectionStats() {
        return dbConnectionStats;
    }

    private static void setDbConnectionStats(String dbConnectionStats) {
        Helper.dbConnectionStats = dbConnectionStats;
    }

    public static String getBotConnectionStats() {
        return botConnectionStats;
    }

    private static void setBotConnectionStats(String botConnectionStats) {
        Helper.botConnectionStats = botConnectionStats;
    }

    public static String getTwConnectionStats() {
        return twConnectionStats;
    }

    private static void setTwConnectionStats(String twConnectionStats) {
        Helper.twConnectionStats = twConnectionStats;
    }

    public static String getNetConnectionStats() {
        return netConnectionStats;
    }

    private static void setNetConnectionStats(String netConnectionStats) {
        Helper.netConnectionStats = netConnectionStats;
    }
}
