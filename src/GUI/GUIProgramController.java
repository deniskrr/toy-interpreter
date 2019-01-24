package GUI;

import Controller.Controller;
import Domain.FileData;
import Domain.PrgState;
import Domain.Statements.ProcedureEntry;
import Exceptions.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GUIProgramController {

    private Controller ctrl;
    private ObservableList<PrgState> prgStates;

    @FXML
    private TextField programNo;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> heapTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> heapAddress;

    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> heapValue;

    @FXML
    private TableView<Map.Entry<Integer, FileData>> fileTableView;

    @FXML
    private TableColumn<Map.Entry<Integer, FileData>, String> fileTableIdentifier;

    @FXML
    private TableColumn<Map.Entry<Integer, FileData>, String> fileTableFileName;

    @FXML
    private ListView<PrgState> identifierView;

    @FXML
    private ListView<String> outView;

    @FXML
    private TableView<Map.Entry<String, Integer>> symbolTable;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> symbolTableName;

    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> symbolTableValue;

    @FXML
    private TableView<Map.Entry<String, ProcedureEntry>> procedureTable;

    @FXML
    private TableColumn<Map.Entry<String, ProcedureEntry>, String> procedureName;

    @FXML
    private TableColumn<Map.Entry<String, ProcedureEntry>, String> procedureParameters;

    @FXML
    private ListView<String> execStackView;

    @FXML
    private Button oneStepButton;

    public void setController(Controller controller) {
        ctrl = controller;
    }

    public void refresh(PrgState currentProgram) {
        List<Map.Entry<Integer, Integer>> heapEntries = new ArrayList<>();
        if (currentProgram.getHeap().getProcTable().size() > 0) {
            currentProgram.getHeap().getAll().forEach(heapEntries::add);
        }

        List<Map.Entry<Integer, FileData>> fileTableEntries = new ArrayList<>();
        if (currentProgram.getFileTable().getProcTable().size() > 0) {
            currentProgram.getFileTable().getAll().forEach(fileTableEntries::add);
        }

        List<Map.Entry<String, Integer>> symbolTableEntries = new ArrayList<>();
        if (currentProgram.getSymTable().getProcTable().size() > 0) {
            currentProgram.getSymTable().getAll().forEach(symbolTableEntries::add);
        }


        List<Map.Entry<String, ProcedureEntry>> procedures = new ArrayList<>();
        if (currentProgram.getProcTable().getProcTable().size() > 0) {
            currentProgram.getProcTable().getAll().forEach(procedures::add);
        }


        procedureTable.setItems(FXCollections.observableArrayList(procedures));
        symbolTable.setItems(FXCollections.observableArrayList(symbolTableEntries));
        fileTableView.setItems(FXCollections.observableArrayList(fileTableEntries));
        heapTableView.setItems(FXCollections.observableArrayList(heapEntries));
        programNo.setText(String.valueOf(prgStates.size()));
        execStackView.setItems(FXCollections.observableArrayList(currentProgram.getExeStack().toString().split("\n")));
        outView.setItems(FXCollections.observableArrayList(currentProgram.getOut().toString().split("\n")));


        symbolTable.refresh();
        fileTableView.refresh();
        heapTableView.refresh();

    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            prgStates = FXCollections.observableArrayList(ctrl.getRepo().getProgramStates());

            identifierView.setItems(prgStates);

            heapAddress.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                    return new SimpleStringProperty(String.valueOf(param.getValue().getKey()));
                }
            });

            heapValue.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> param) {
                    return new SimpleStringProperty(String.valueOf(param.getValue().getValue()));
                }
            });

            fileTableIdentifier.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, String> param) {
                    return new SimpleStringProperty(String.valueOf(param.getValue().getKey()));
                }
            });

            fileTableFileName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, FileData>, String> param) {
                    return new SimpleStringProperty(param.getValue().getValue().getFileName());
                }
            });

            symbolTableName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> param) {
                    return new SimpleStringProperty(param.getValue().getKey());
                }
            });

            symbolTableValue.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> param) {
                    return new SimpleStringProperty(String.valueOf(param.getValue().getValue()));
                }
            });

            procedureName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, ProcedureEntry>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, ProcedureEntry>, String> param) {
                    return new SimpleStringProperty(param.getValue().getKey());
                }
            });

            procedureParameters.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, ProcedureEntry>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, ProcedureEntry>, String> param) {
                    return new SimpleStringProperty(param.getValue().getValue().toString());
                }
            });

            identifierView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PrgState>() {
                @Override
                public void changed(ObservableValue<? extends PrgState> observable, PrgState oldValue, PrgState newValue) {
                    if (observable.getValue() != null) {
                        refresh(observable.getValue());
                    }
                }
            });

            identifierView.setCellFactory(new Callback<ListView<PrgState>, ListCell<PrgState>>() {
                @Override
                public ListCell<PrgState> call(ListView<PrgState> param) {
                    ListCell<PrgState> cell = new ListCell<PrgState>() {

                        @Override
                        protected void updateItem(PrgState item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(String.valueOf(item.getId()));
                            } else {
                                setText("");
                            }
                        }
                    };
                    return cell;
                }
            });


            oneStepButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        if (prgStates.size() > 0) {
                            try {
                                ctrl.oneStepForAllPrg(prgStates);
                            } catch (DivisionByZeroException |
                                    FileAlreadyExistsException |
                                    FileReadException |
                                    HeapVariableNotFoundException |
                                    HeapWritingException |
                                    InvalidOperatorException |
                                    VariableNotFoundException e) {
                                e.printStackTrace();
                                Stage stage = (Stage) oneStepButton.getScene().getWindow();
                                stage.close();
                            }

                            // Refresh once more in case of the last program
                            refresh(identifierView.getSelectionModel().getSelectedItem());
                            // Remove the completed programs
                            prgStates.setAll(ctrl.removeCompletedPrg(prgStates));
                            identifierView.getSelectionModel().selectFirst();
                        }
                    } catch (DivisionByZeroException |
                            InvalidOperatorException |
                            VariableNotFoundException |
                            HeapWritingException |
                            HeapVariableNotFoundException |
                            EmptyStackException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
