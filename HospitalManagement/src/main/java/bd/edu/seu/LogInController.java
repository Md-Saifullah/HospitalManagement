package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static bd.edu.seu.App.*;

public class LogInController {
    @FXML
    TextField idField;
    @FXML
    TextField passwordField;
    @FXML
    Label warningLabel;
    DBActions dbActions;

    public LogInController() {
        dbActions=new DBActions();
        idField = new TextField();
        passwordField = new TextField();
        warningLabel = new Label();
    }

    @FXML
    private void handleLogInButton() throws IOException {
        String id = idField.getText();
        String password = passwordField.getText();
        if(password.equals("")||id.equals("")){
            warningLabel.setText("Fill all the fields!");
        }
        else{
            warningLabel.setText("");
            if(dbActions.isEmployee(id,password)) {
                System.out.println("employee true");
                setCurrentPassword(password);
                setId(id);
                if(id.equals("1")){
                    setAdmin(true);
                }
                setRoot("mainMenu");
            }
            else{
                System.out.println("not employee");
                warningLabel.setText("Invalid User ID or Password");
            }
        }
    }

}
