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
import telegram.Bot;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("styling/style.css").toExternalForm());
        primaryStage.show();

//        telegram.Bot bot = new telegram.Bot();
//        bot.broadcast(primaryStage);
    }


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
//        try {
//            bot.sendMessageToChannel();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        launch(args);
    }

}
