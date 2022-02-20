package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

import static bd.edu.seu.App.*;

public class ChangePasswordController {
    DBActions dbActions;
    @FXML
    PasswordField previousPasswordField;
    @FXML
    PasswordField newPasswordField;
    @FXML
    PasswordField confirmPasswordField;
    @FXML
    Label warningLabel;
    public ChangePasswordController() {
        dbActions=new DBActions();
        previousPasswordField=new PasswordField();
        newPasswordField=new PasswordField();
        confirmPasswordField= new PasswordField();
        warningLabel=new Label();
    }
    @FXML
    private void handleBackButton() throws IOException {
        setRoot("mainMenu");
    }

    @FXML
    private void handleConfirmButton() throws IOException {
        String previousPassword=previousPasswordField.getText();
        String newPassword=newPasswordField.getText();
        String confirmPassword=confirmPasswordField.getText();
        if(previousPassword.equals("")||newPassword.equals("")||confirmPassword.equals("")){
            warningLabel.setText("Please Fill all the fields");
        }
        else if(!newPassword.equals(confirmPassword)){
            warningLabel.setText("Confirm password didn't matched");
        }else {
            if(getCurrentPassword().equals(previousPassword)){
                if(dbActions.updatePassword(getId(),newPassword)){
                    setCurrentPassword(newPassword);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Password updated!");

                    alert.showAndWait();
                    App.setRoot("mainMenu");
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong password");
                alert.setContentText("Password did not matched");

                alert.showAndWait();

                warningLabel.setText("");
            }
        }
    }
}
