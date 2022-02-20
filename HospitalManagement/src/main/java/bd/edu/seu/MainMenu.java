package bd.edu.seu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import static bd.edu.seu.App.*;

public class MainMenu implements Initializable {
    @FXML
    private Button addEmployeeButton;
    @FXML
    private Button addDoctorButton;

    public MainMenu() {
        addDoctorButton=new Button();
        addEmployeeButton=new Button();
    }

    @FXML
    private void handleAddEmployee() throws IOException {
     setRoot("addEmployee");
    }


    @FXML
    private void handleAddDoctor() throws IOException {
        System.out.println("asdsdfsafd");
        setRoot("addDoctor");
    }


    @FXML
    private void handleLogOutButton() throws IOException {
        setCurrentPassword("");
        setInitializeAll(true);
        setValue("");
        setKey("");
        setId("");
        setPatientName("");
        setAdmin(false);
        setRoot("logIn");
    }
    @FXML
    private void handleDoctorsInformationButton() throws IOException {
        setRoot("doctorsInformation");
    }
    @FXML
    private void handleChangePasswordButton() throws IOException {
        setRoot("changePassword");
    }
    @FXML
    private void handlePatientsInformationButton() throws IOException {
        setRoot("patientsInformation");
    }
    @FXML
    private void handleAdmitButton() throws IOException {
        setRoot("admitPatient");
    }
    @FXML
    private void handleReleaseButton() throws IOException {
        setRoot("releasePatient");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if(!isAdmin()){
           addEmployeeButton.setDisable(true);
           addDoctorButton.setDisable(true);
       }
    }
}