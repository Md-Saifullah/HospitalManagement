package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static bd.edu.seu.App.*;

public class PatientsInformationController implements Initializable {
    DBActions dbActions;
    @FXML
    ChoiceBox<String> searchChoiceBox;
    @FXML
    ChoiceBox<String> statusChoiceBox;
    @FXML
    TextField nameField;
    @FXML
    TextField idField;
    @FXML
    TextField wardNameField;
    @FXML
    TextField roomNumberField;
    @FXML
    TextField seatNumberField;
    @FXML
    DatePicker admitDatePicker;
    @FXML
    DatePicker releasedDatePicker;
    @FXML
    Button searchButton;


    @FXML
    TableView<Patient> patientsTable;
    @FXML
    TableColumn<Patient, String> nameColumn;
    @FXML
    TableColumn<Patient, Number> idColumn;
    @FXML
    TableColumn<Patient, LocalDate> admittedColumn;
    @FXML
    TableColumn<Patient, LocalDate> releasedColumn;
    @FXML
    TableColumn<Patient, String> wardColumn;
    @FXML
    TableColumn<Patient, Number> roomColumn;
    @FXML
    TableColumn<Patient, Number> seatColumn;
    @FXML
    TableColumn<Patient, String> statusColumn;


    public PatientsInformationController() {
        dbActions = new DBActions();
        statusChoiceBox = new ChoiceBox<>();
        searchChoiceBox = new ChoiceBox<>();
        nameField = new TextField();
        idField = new TextField();
        wardNameField = new TextField();
        roomNumberField = new TextField();
        seatNumberField = new TextField();
        admitDatePicker = new DatePicker();
        releasedDatePicker = new DatePicker();
        searchButton = new Button();

        patientsTable = new TableView<>();
        nameColumn = new TableColumn<>();
        idColumn = new TableColumn<>();
        admittedColumn = new TableColumn<>();
        releasedColumn = new TableColumn<>();
        wardColumn = new TableColumn<>();
        roomColumn = new TableColumn<>();
        seatColumn = new TableColumn<>();
        statusColumn = new TableColumn<>();
    }

    @FXML
    private void handleBackButton() throws IOException {
        setInitializeAll(true);
        setRoot("mainMenu");
    }


    @FXML
    private void handleSearchButton() throws IOException {
        String key = searchChoiceBox.getValue();
        System.out.println(key);
        String value;
        String searchWord;
        if (key.contains("Name")) {
            searchWord = "name";
            value = nameField.getText();
        } else if (key.contains("ID")) {
            searchWord = "id";
            value = idField.getText();
        } else {
            searchWord = "status";
            value = statusChoiceBox.getValue();
        }
        if (value.equals("")) {
            System.out.println("fill the field");
        } else {
            setKey(searchWord);
            setValue(value);
            setInitializeAll(false);
            setRoot("patientsInformation");
        }
    }

    @FXML
    private void handleRefreshButton() throws IOException {
        setInitializeAll(true);
        setRoot("patientsInformation");
    }

    private void setSearchChoiceBox() {
        ObservableList<String> searchItems = FXCollections.observableArrayList();
        searchItems.add("search by ID");
        searchItems.add("search by Name");
        searchItems.add("search by Status");
        searchChoiceBox.setItems(searchItems);
    }

    private void setStatusChoiceBox() {
        ObservableList<String> status = FXCollections.observableArrayList();
        status.add("Admitted");
        status.add("Released");
        statusChoiceBox.setItems(status);
    }

    private void setPatientsTable() {
        ArrayList<Patient> patient;
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        boolean isAll = isInitializeAll();
        if (isAll) {

            patient = dbActions.getAllPatients();

        } else {
            String key = getKey();
            String value = getValue();
            patient = dbActions.getPatient(key, value);
        }
        System.out.println(patient);
        patients.addAll(patient);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        admittedColumn.setCellValueFactory(new PropertyValueFactory<>("admitDate"));
        releasedColumn.setCellValueFactory(new PropertyValueFactory<>("releasedDate"));
        wardColumn.setCellValueFactory(new PropertyValueFactory<>("wardName"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        patientsTable.setItems(patients);
    }


    @FXML
    private void handleTableSelectAction() {
        Patient patient = patientsTable.getSelectionModel().getSelectedItem();
        if(patient!=null) {
            setSearchChoiceBox();
            nameField.setText(patient.getName());
            idField.setText(String.valueOf(patient.getId()));
            admitDatePicker.setValue(patient.getAdmitDate());
            releasedDatePicker.setValue(patient.getReleasedDate());
            wardNameField.setText(patient.getWardName());
            roomNumberField.setText(String.valueOf(patient.getRoomNumber()));
            seatNumberField.setText(String.valueOf(patient.getSeatNumber()));
            statusChoiceBox.setValue(patient.getStatus());
        }
    }

    private void reset() {
        nameField.clear();
        idField.clear();
        admitDatePicker.setValue(null);
        releasedDatePicker.setValue(null);
        wardNameField.clear();
        roomNumberField.clear();
        seatNumberField.clear();
        setStatusChoiceBox();
    }

    private void disableForm() {
        admitDatePicker.setDisable(true);
        releasedDatePicker.setDisable(true);
        wardNameField.setDisable(true);
        roomNumberField.setDisable(true);
        seatNumberField.setDisable(true);
        nameField.setDisable(true);
        idField.setDisable(true);
        statusChoiceBox.setDisable(true);
        searchButton.setDisable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPatientsTable();
        setSearchChoiceBox();
        setStatusChoiceBox();
        disableForm();
        searchChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            reset();
            disableForm();
            DoctorsInformationController.onSelectSearchType(newValue, nameField, statusChoiceBox, idField, searchButton);
        });
    }
}
