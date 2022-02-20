package bd.edu.seu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    private static boolean initializeAll = true;
    private static String key = "";
    private static String value = "";
    private static String currentPassword = "";
    private static String id;
    private static String patientName="";
    private static boolean admin=false;


    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        App.admin = admin;
    }

    public static String getPatientName() { return patientName; }

    public static void setPatientName(String patientName) { App.patientName = patientName; }

    public static String getId() { return id; }

    public static void setId(String id) { App.id = id; }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    public static void setCurrentPassword(String currentPassword) {
        App.currentPassword = currentPassword;
    }

    public static boolean isInitializeAll() {
        return initializeAll;
    }

    public static void setInitializeAll(boolean initializeAll) {
        App.initializeAll = initializeAll;
    }

    public static String getValue() {
        return value;
    }

    public static void setValue(String value) {
        App.value = value;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        App.key = key;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene=new Scene(loadFXML("logIn"),709,365);
        stage.setTitle("Hospital Management System");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}