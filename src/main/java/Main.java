import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import utils.Bot;
import utils.TwitterHandler;
import twitter4j.TwitterException;
import utils.Helper;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        TwitterHandler twitterHandler = new TwitterHandler();
        Helper.connectDatabase();

        try {
            twitterHandler.configTwitter();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        launch(args);
    }

}
