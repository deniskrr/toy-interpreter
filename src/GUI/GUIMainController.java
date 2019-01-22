package GUI;

import Commands.Command;
import Commands.RunCommand;
import Utils.AppMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class GUIMainController {

    private void addCommands(List<Command> list) {
        commands.addAll(list);
    }

    private Stage mainStage;
    private ObservableList<Command> commands;

    @FXML
    private Button runButton;

    @FXML
    private ListView<Command> programList;


    @FXML
    public void initialize() {
        commands = FXCollections.observableArrayList();
        addCommands(AppMain.getCommands());

        programList.setCellFactory(new Callback<ListView<Command>, ListCell<Command>>() {
            @Override
            public ListCell<Command> call(ListView<Command> param) {
                ListCell<Command> cell = new ListCell<Command>() {

                    @Override
                    protected void updateItem(Command item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getDescription());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
        programList.setItems(commands);

        // To set selection model
        programList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Select item at index = 1
        programList.getSelectionModel().selectIndices(0);

        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("program.fxml"));
                    GridPane secondaryLayout = (GridPane) fxmlLoader.load();
                    GUIProgramController controller = fxmlLoader.getController();;
                    RunCommand currentCommand = (RunCommand) programList.getSelectionModel().getSelectedItem();

                    controller.setController(currentCommand.getController(  ));

                    Scene newScene = new Scene(secondaryLayout, 600, 600);


                    Stage newWindow = new Stage();
                    newWindow.setTitle("Program Execution");
                    newWindow.setScene(newScene);

                    newWindow.initModality(Modality.APPLICATION_MODAL);

                    newWindow.initOwner(mainStage);

                    newWindow.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Focus
//        programList.getFocusModel().focus(5);
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

}
