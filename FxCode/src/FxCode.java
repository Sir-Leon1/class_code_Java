import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxCode extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a TilePane layout
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(10));
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        // Add some buttons to the TilePane
        for (int i = 0; i < 10; i++) {
            Button button = new Button("Button " + (i + 1));
            tilePane.getChildren().add(button);
        }

        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Create File menu
        Menu fileMenu = new Menu("File");


        //create menu items with resized graphics
        MenuItem newMenuItem = createResizedMenuItem("New", "new.png", 16, 16);
        MenuItem openMenuItem = createResizedMenuItem("Open", "open.png", 16, 16);
        MenuItem saveMenuItem = createResizedMenuItem("Save", "save.png", 16, 16);
        MenuItem exitMenuItem = createResizedMenuItem("Exit", "exit.png", 16, 16);

        // Add menu items to File menu
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Handle events for menu items
        exitMenuItem.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });

        // Create Edit menu
        Menu editMenu = new Menu("Edit");

        // Create submenus
        Menu formatMenu = new Menu("Format");
        MenuItem boldMenuItem = new MenuItem("Bold");
        MenuItem italicMenuItem = new MenuItem("Italic");
        MenuItem underlineMenuItem = new MenuItem("Underline");
        formatMenu.getItems().addAll(boldMenuItem, italicMenuItem, underlineMenuItem);

        // Add submenus to Edit menu
        editMenu.getItems().addAll(formatMenu);

        // Add File and Edit menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu);

        // Create a VBox to hold the TilePane and MenuBar
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, tilePane);

        // Create the scene
        Scene scene = new Scene(vBox, 400, 300);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();
    }

    private MenuItem createResizedMenuItem(String text, String imagePath, double width, double height) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return new MenuItem(text, imageView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
