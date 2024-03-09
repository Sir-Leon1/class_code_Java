import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage){
        Pane pane = new Pane();

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");

        btn1.setLayoutX(50);
        btn1.setLayoutY(50);
        btn2.setLayoutX(150);
        btn2.setLayoutY(50);
        
        pane.getChildren().addAll(btn1, btn2);

        Scene scene = new Scene(pane, 300, 200);

        primaryStage.setTitle("Pane Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}