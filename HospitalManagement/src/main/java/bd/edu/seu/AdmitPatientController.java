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

public class AdmitPatientController implements Initializable {
    DBActions dbActions;
    @FXML
    TextField nameField;
    @FXML
    ChoiceBox<String> wardChoiceBox;
    @FXML
    TextField roomNumberField;
    @FXML
    TextField seatNumberField;
    @FXML
    Label warningLabel;
    @FXML
    Button admitButton;
    @FXML
    TableView<Seat> tableView;
    @FXML
    TableColumn<Seat, Number> seatColumn;
    @FXML
    TableColumn<Seat, Number> roomColumn;
    @FXML
    TableColumn<Seat,String>wardColumn;

    public AdmitPatientController() {
        dbActions=new DBActions();
        nameField = new TextField();
        wardChoiceBox = new ChoiceBox<>();
        roomNumberField = new TextField();
        seatNumberField = new TextField();
        warningLabel = new Label();
        admitButton = new Button();
        tableView = new TableView<>();
        seatColumn = new TableColumn<>();
        roomColumn = new TableColumn<>();
        wardColumn=new TableColumn<>();
    }
    @FXML
    private void handleBackButton() throws IOException {
        setInitializeAll(true);
        setRoot("mainMenu");
    }
    @FXML
    private void handleRefreshButton() throws IOException {
        setInitializeAll(true);
        setRoot("admitPatient");
    }
    @FXML
    private void handleSearchSeatButton() throws IOException {
        String wardName=wardChoiceBox.getValue();
        if(wardName==null){
            warningLabel.setText("Please enter a ward name to search for a seat");
        }else{
            setPatientName(nameField.getText());
            setValue(wardName);
            setInitializeAll(false);
            setRoot("admitPatient");
        }
    }
    @FXML
    private void handleAdmitButton(){
        String wardName=wardChoiceBox.getValue();
        System.out.println(wardName);
        String name=nameField.getText();
        String roomNumber=roomNumberField.getText();
        String seatNumber=seatNumberField.getText();
        if(wardName == null ||name.equals("")||roomNumber.equals("")||seatNumber.equals("")){
            warningLabel.setText("Fill all the fields");
        }else{
            Patient patient=new Patient(name,0, LocalDate.now(),null,wardName,Integer.parseInt(roomNumber),Integer.parseInt(seatNumber),"Admitted");
            int id=dbActions.admitPatient(patient);
            if(id!=-1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Patient Admitted with ID "+id);

                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to admit Patient");
                alert.setContentText("Try again.");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleTableSelectAction() {
        Seat seat = tableView.getSelectionModel().getSelectedItem();
        if(seat!=null) {
            roomNumberField.setText(String.valueOf(seat.getRoomNumber()));
            seatNumberField.setText(String.valueOf(seat.getSeatNumber()));
            wardChoiceBox.setValue(seat.getWardName());
            wardChoiceBox.setDisable(true);
        }
    }

    private  void setSeatTable(){
        ArrayList<Seat> seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();
        boolean isAll = isInitializeAll();
        if (isAll) {
            seats.addAll(dbActions.getAllSeats());
        } else {
            String patientsName=getPatientName();
            nameField.setText(patientsName);
            String value = getValue();
            wardChoiceBox.setValue(value);
            seats.addAll(dbActions.getSeat(value));
        }
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        wardColumn.setCellValueFactory(new PropertyValueFactory<>("wardName"));
        tableView.setItems(seats);
    }


    private void setWardChoiceBox() {
        ObservableList<String> wards = FXCollections.observableArrayList();
        wards.addAll(dbActions.getAllWardName());
        wardChoiceBox.setItems(wards);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomNumberField.setEditable(false);
        seatNumberField.setEditable(false);
        setWardChoiceBox();
        setSeatTable();
    }
}
