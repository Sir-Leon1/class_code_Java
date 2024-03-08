import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExperiments extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TilePane Experiment");

        Button btn1 = new Button("Btn1");
        Button btn2 = new Button("btn2");
        Button btn3  = new Button("Btn3");
        Button btn4 = new Button("btn4");
        Button btn5 = new Button("Btn5");
        Button btn6 = new Button("btn6");

        TilePane tilepane = new TilePane();
        //tilepane.getChildren().add(btn1);
        //tilepane.getChildren().add(btn2);
        //tilepane.getChildren().add(btn3);
        tilepane.getChildren().add(btn4);
        tilepane.getChildren().add(btn5);
        tilepane.getChildren().add(btn6);

        FlowPane flowpane  = new FlowPane();
        flowpane.setOrientation(Orientation.VERTICAL);

        flowpane.getChildren().add(btn1);
        flowpane.getChildren().add(btn2);
        flowpane.getChildren().add(btn3);

        tilepane.setTileAlignment(Pos.BASELINE_LEFT);
        tilepane.setHgap(10);
        tilepane.setVgap(20);

        Scene scene = new Scene(tilepane, 400, 400);
        Scene scene2 = new Scene(flowpane, 100, 100);
        primaryStage.setScene(scene);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}