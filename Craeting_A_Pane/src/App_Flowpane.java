
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App_Flowpane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HBox Experiment 1");

        Button button1 = new Button("Button Number 1");
        Button button2 = new Button("Button number 2");
        Button button3 = new Button("Button number 3");

        FlowPane flowpane  = new FlowPane();
        flowpane.setOrientation(Orientation.VERTICAL);

        flowpane.getChildren().add(button1);
        flowpane.getChildren().add(button2);
        flowpane.getChildren().add(button3);

        Scene scene = new Scene(flowpane, 200, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}