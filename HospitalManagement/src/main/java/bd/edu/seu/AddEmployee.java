package bd.edu.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddEmployee {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label warningLabel;
    DBActions dbActions;
    public AddEmployee() {
        nameField=new TextField();
        passwordField=new PasswordField();
        warningLabel=new Label();
        dbActions=new DBActions();
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("mainMenu");
    }
    @FXML
    private void handleAddButton(){
        String name=nameField.getText();
        String password=passwordField.getText();
        if(name.equals("")||password.equals("")){
            warningLabel.setText("Fill all the Fields!");
        }else {
            warningLabel.setText("");
            int id=dbActions.addEmployee(name,password);
            if(id!=-1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee Added with ID "+id);

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
}
