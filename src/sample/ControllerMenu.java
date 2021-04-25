package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//menu controller compiled and put together -Chris
public class ControllerMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnMenuPackages;

    @FXML
    private Button btnMenuCustomers;

    @FXML
    private Button btnMenuAgents;

    @FXML
    private Button btnMenuClose;

    @FXML
    void initialize() {
        assert btnMenuPackages != null : "fx:id=\"btnMenuPackages\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnMenuCustomers != null : "fx:id=\"btnMenuCustomers\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnMenuAgents != null : "fx:id=\"btnMenuAgents\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnMenuClose != null : "fx:id=\"btnMenuClose\" was not injected: check your FXML file 'menu.fxml'.";
            //opens agents scene view -Chris
        btnMenuAgents.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("agent.fxml"));
                        Stage window = (Stage) btnMenuAgents.getScene().getWindow();
                        window.setScene(new Scene(root));
                    window.setTitle("Agent Data");;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //opens customers scene view -Chris
        btnMenuCustomers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("customer.fxml"));
                    Stage window = (Stage) btnMenuCustomers.getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.setTitle("Customer Data");;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //opens package scene view -Chris
        btnMenuPackages.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("package.fxml"));
                    Stage window = (Stage) btnMenuPackages.getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.setTitle("Package Data");;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //Close method -Chris
        btnMenuClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ((Stage)(((Button)mouseEvent.getSource()).getScene().getWindow())).close();
            }
        });
    }
}
