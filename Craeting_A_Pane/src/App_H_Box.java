import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App_H_Box extends Application {
    @Override
    public void start(Stage primaryStage)
    throws Exception {
        primaryStage.setTitle("HBox Experiment");

        Button button1 = new Button("Button number 1");
        Button button2 = new Button("Button number 2");

        HBox hbox = new HBox(button1, button2);

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}