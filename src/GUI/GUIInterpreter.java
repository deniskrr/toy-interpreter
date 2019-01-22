package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIInterpreter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app.fxml"));

        BorderPane root = (BorderPane) fxmlLoader.load();
        Scene scene = new Scene(root, 800, 640);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TextInterpreter");
        GUIMainController controller = fxmlLoader.getController();
        controller.setMainStage(primaryStage);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
