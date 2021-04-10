/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cbProducts"
    private ComboBox<?> cbProducts; // Value injected by FXMLLoader

    @FXML // fx:id="lbProdId"
    private Label lbProdId; // Value injected by FXMLLoader

    @FXML // fx:id="lbProdName"
    private Label lbProdName; // Value injected by FXMLLoader

    @FXML // fx:id="tfProdId"
    private TextField tfProdId; // Value injected by FXMLLoader

    @FXML // fx:id="tfProdName"
    private TextField tfProdName; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbProducts != null : "fx:id=\"cbProducts\" was not injected: check your FXML file 'sample.fxml'.";
        assert lbProdId != null : "fx:id=\"lbProdId\" was not injected: check your FXML file 'sample.fxml'.";
        assert lbProdName != null : "fx:id=\"lbProdName\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfProdId != null : "fx:id=\"tfProdId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfProdName != null : "fx:id=\"tfProdName\" was not injected: check your FXML file 'sample.fxml'.";

    }
    public class Menu {

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // fx:id="btnGO"
        private Button btnGO; // Value injected by FXMLLoader

        @FXML // fx:id="cbTables"
        private ComboBox<?> cbTables; // Value injected by FXMLLoader

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert btnGO != null : "fx:id=\"btnGO\" was not injected: check your FXML file 'Menu.fxml'.";
            assert cbTables != null : "fx:id=\"cbTables\" was not injected: check your FXML file 'Menu.fxml'.";

        }
        public class Packages {

            @FXML // fx:id="cbPackages"
            private ComboBox<?> cbPackages; // Value injected by FXMLLoader

            @FXML // fx:id="tfPackageId"
            private TextField tfPackageId; // Value injected by FXMLLoader

            @FXML // fx:id="tfAgencyCommission"
            private TextField tfAgencyCommission; // Value injected by FXMLLoader

            @FXML // fx:id="tfBasePrice"
            private TextField tfBasePrice; // Value injected by FXMLLoader

            @FXML // fx:id="tfDescription"
            private TextField tfDescription; // Value injected by FXMLLoader

            @FXML // fx:id="tfEndDate"
            private TextField tfEndDate; // Value injected by FXMLLoader

            @FXML // fx:id="tfStartDate"
            private TextField tfStartDate; // Value injected by FXMLLoader

            @FXML // fx:id="tfPackageName"
            private TextField tfPackageName; // Value injected by FXMLLoader

            @FXML // fx:id="btnPackageEdit"
            private Button btnPackageEdit; // Value injected by FXMLLoader

            @FXML // fx:id="btnPackageSave"
            private Button btnPackageSave; // Value injected by FXMLLoader

            @FXML // fx:id="btnPackageDelete"
            private Button btnPackageDelete; // Value injected by FXMLLoader

            @FXML // fx:id="btnPackageClose"
            private Button btnPackageClose; // Value injected by FXMLLoader

        }
        public class Customers {

            @FXML
            private ComboBox<?> cbCustomers;

            @FXML
            private Button btnCustomerEdit;

            @FXML
            private Button btnCustomerSave;

            @FXML
            private Button btnCustomerDelete;

            @FXML
            private Button btnCustomerClose;

            @FXML
            private TextField tfCustomerId;

            @FXML
            private TextField tfCustAddress;

            @FXML
            private TextField tfCusBusPhone;

            @FXML
            private TextField tfCustCity;

            @FXML
            private TextField tfCustCountry;

            @FXML
            private TextField tfCustEmail;

            @FXML
            private TextField tfCustFirstName;

            @FXML
            private TextField tfCustHomePhone;

            @FXML
            private TextField tfCustLastNAME;

            @FXML
            private TextField tfCustProvince;

            @FXML
            private TextField tfCustPostal;

            @FXML
            private TextField tfCustAgentId;

        }

    }
}
