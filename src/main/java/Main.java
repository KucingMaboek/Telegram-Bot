import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.telegram.telegrambots.ApiContextInitializer;

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
        launch(args);
    }

}
