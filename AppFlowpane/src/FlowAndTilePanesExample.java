import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlowAndTilePanesExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setHgap(10);
        flowPane.setVgap(10);

        // Create buttons for the FlowPane
        for (int i = 1; i <= 6; i++) {
            Button button = new Button("Button " + i);
            flowPane.getChildren().add(button);
        }

        // Create a TilePane
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(10));
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        // Create buttons for the TilePane
        for (int i = 7; i <= 12; i++) {
            Button button = new Button("Button " + i);
            tilePane.getChildren().add(button);
        }

        // Combine both panes into a VBox
        // You can use any layout pane to combine them according to your requirements
        // Here, VBox is used for simplicity
        VBox root = new VBox(flowPane, tilePane);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("FlowPane and TilePane Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
