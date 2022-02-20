package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static bd.edu.seu.App.*;

public class ReleasePatientController {
    @FXML
    TextField idField;
    @FXML
    Label warningLabel;
    DBActions dbActions;

    public ReleasePatientController() {
        idField=new TextField();
        warningLabel=new Label();
        dbActions=new DBActions();
    }
    @FXML
    private void handleBackButton() throws IOException {
        setRoot("mainMenu");
    }
    @FXML
    private void handleReleaseButton(){
        String id=idField.getText();
        if(id.equals("")){
            warningLabel.setText("Please Enter a Patient ID");
        }else{
            if(dbActions.releasePatient(id)){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Patient is released!");

                alert.showAndWait();

                idField.clear();
            }else{
                warningLabel.setText("Invalid Patient ID");
            }
        }
    }
}
