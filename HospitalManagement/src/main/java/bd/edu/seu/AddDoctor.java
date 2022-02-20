package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddDoctor implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    ChoiceBox<String> wardChoiceBox;
    @FXML
    DatePicker datePicker;
    @FXML
    Label warningLabel;
    DBActions dbActions;
    public AddDoctor() {
        dbActions=new DBActions();
        nameField=new TextField();
        wardChoiceBox=new ChoiceBox<>();
        datePicker=new DatePicker();
        warningLabel=new Label();
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("mainMenu");
    }
    @FXML
    private void handleAddButton(){
        String name=nameField.getText();
        String wardName=wardChoiceBox.getValue();
        LocalDate joinDate=datePicker.getValue();
        if(name.equals("")||wardName.equals("")||joinDate==null){
            warningLabel.setText("Fill all the Fields!");
        }else {
            warningLabel.setText("");
            Doctor doctor=new Doctor(name,0,wardName,wardName,joinDate);
            int id=dbActions.addDoctor(doctor);
            if(id!=-1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Doctor Added with ID "+id);

                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Employee adding failed");
                alert.setContentText("Please try again");

                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> wards = FXCollections.observableArrayList();
        wards.addAll(dbActions.getAllWardName());
        wardChoiceBox.setItems(wards);
        datePicker.setEditable(false);

    }
}
