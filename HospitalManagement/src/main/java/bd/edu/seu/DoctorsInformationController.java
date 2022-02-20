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

public class DoctorsInformationController implements Initializable {
    DBActions dbActions;
    @FXML
    ChoiceBox<String> searchChoiceBox;
    @FXML
    ChoiceBox<String> wardChoiceBox;
    @FXML
    TextField nameField;
    @FXML
    TextField idField;
    @FXML
    TextField specialAtField;
    @FXML
    DatePicker joinedAtDatePicker;
    @FXML
    Button searchButton;


    @FXML
    TableView<Doctor> doctorTableView;
    @FXML
    TableColumn<Doctor, String> nameColumn;
    @FXML
    TableColumn<Doctor, Number> idColumn;
    @FXML
    TableColumn<Doctor, LocalDate> joinedAtColumn;
    @FXML
    TableColumn<Doctor, String> wardColumn;
    @FXML
    TableColumn<Doctor, String> specialAtColumn;


    public DoctorsInformationController() {

        dbActions = new DBActions();

        searchChoiceBox = new ChoiceBox<>();
        wardChoiceBox = new ChoiceBox<>();
        nameField = new TextField();
        idField = new TextField();
        specialAtField = new TextField();
        joinedAtDatePicker = new DatePicker();
        searchButton = new Button();

        doctorTableView = new TableView<>();
        nameColumn = new TableColumn<>();
        idColumn = new TableColumn<>();
        joinedAtColumn = new TableColumn<>();
        wardColumn = new TableColumn<>();
        specialAtColumn = new TableColumn<>();
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
            searchWord = "ward_name";
            value = wardChoiceBox.getValue();
        }
        if (value.equals("")) {
            System.out.println("fill the field");
        } else {
            setKey(searchWord);
            setValue(value);
            setInitializeAll(false);
            setRoot("DoctorsInformation");
        }
    }

    @FXML
    private void handleRefreshButton() throws IOException {
        setInitializeAll(true);
        setRoot("doctorsInformation");
    }


    private void setSearchChoiceBox() {
        ObservableList<String> searchItems = FXCollections.observableArrayList();
        searchItems.add("search by ID");
        searchItems.add("search by Name");
        searchItems.add("search by Ward");
        searchChoiceBox.setItems(searchItems);
    }
    private void setWardChoiceBox(){
        ObservableList<String> wards = FXCollections.observableArrayList();
        wards.addAll(dbActions.getAllWardName());
        wardChoiceBox.setItems(wards);
    }

    private void setDoctorsTable() {
        ArrayList<Doctor> doctor;
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        boolean isAll = isInitializeAll();
        if (isAll) {
            doctor = dbActions.getAllDoctors();
        } else {
            String key = getKey();
            String value = getValue();
            doctor = dbActions.getDoctor(key, value);
        }
        System.out.println(doctor);
        doctors.addAll(doctor);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        joinedAtColumn.setCellValueFactory(new PropertyValueFactory<>("joinedAt"));
        wardColumn.setCellValueFactory(new PropertyValueFactory<>("wardName"));
        specialAtColumn.setCellValueFactory(new PropertyValueFactory<>("specialAt"));
        doctorTableView.setItems(doctors);
    }


    @FXML
    private void handleTableSelectAction() {
        Doctor doctor = doctorTableView.getSelectionModel().getSelectedItem();
        if(doctor!=null) {
            setSearchChoiceBox();
            nameField.setText(doctor.getName());
            idField.setText(String.valueOf(doctor.getId()));
            joinedAtDatePicker.setValue(doctor.getJoinedAt());
            wardChoiceBox.setValue(doctor.getWardName());
            specialAtField.setText(doctor.getSpecialAt());
        }
    }

    private void reset() {
        nameField.clear();
        idField.clear();
        joinedAtDatePicker.setValue(null);
        specialAtField.clear();
        setWardChoiceBox();

    }

    private void disableForm() {
        joinedAtDatePicker.setDisable(true);
        wardChoiceBox.setDisable(true);
        nameField.setDisable(true);
        specialAtField.setDisable(true);
        idField.setDisable(true);
        searchButton.setDisable(true);
    }

    public static void onSelectSearchType(String newValue, TextField nameField, ChoiceBox<String> wardChoiceBox, TextField idField, Button searchButton) {
        if(newValue==null){
            newValue="";
        }
        searchButton.setDisable(false);
        if (newValue.contains("Name")) {
            nameField.setDisable(false);
        }
        if (newValue.contains("ID")) {
            idField.setDisable(false);

        }
        if (newValue.contains("Ward")||newValue.contains("Status")) {
            wardChoiceBox.setDisable(false);
        }
        if (newValue.equals("")) {
            searchButton.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDoctorsTable();
        setSearchChoiceBox();
        setWardChoiceBox();
        disableForm();
        searchChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            reset();
            disableForm();
           onSelectSearchType(newValue,nameField,wardChoiceBox,idField,searchButton);
        });
    }
}
