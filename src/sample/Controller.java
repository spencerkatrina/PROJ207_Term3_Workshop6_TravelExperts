/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

package sample;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


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

        @FXML
            // This method is called by the FXMLLoader when initialization is complete
        void initialize() {
            assert btnGO != null : "fx:id=\"btnGO\" was not injected: check your FXML file 'Menu.fxml'.";
            assert cbTables != null : "fx:id=\"cbTables\" was not injected: check your FXML file 'Menu.fxml'.";

        }
    }

    /********** Packages -KATRINA SPENCER ************/

    @FXML // fx:id="cbPackages"
    private ComboBox<Package> cbPackages; // Value injected by FXMLLoader

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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbPackages != null : "fx:id=\"cbPackages\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfPackageId != null : "fx:id=\"tfPackageId\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfAgencyCommission != null : "fx:id=\"tfAgencyCommission\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfBasePrice != null : "fx:id=\"tfBasePrice\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfDescription != null : "fx:id=\"tfDescription\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfEndDate != null : "fx:id=\"tfEndDate\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfStartDate != null : "fx:id=\"tfStartDate\" was not injected: check your FXML file 'packages.fxml'.";
        assert tfPackageName != null : "fx:id=\"tfPackageName\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnPackageEdit != null : "fx:id=\"btnPackageEdit\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnPackageSave != null : "fx:id=\"btnPackageSave\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnPackageDelete != null : "fx:id=\"btnPackageDelete\" was not injected: check your FXML file 'packages.fxml'.";
        assert btnPackageClose != null : "fx:id=\"btnPackageClose\" was not injected: check your FXML file 'packages.fxml'.";

        try {
            Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from packages");
            ObservableList<Package> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Package(rs.getInt("PackageId"),
                        rs.getString("PkgName"),
                        rs.getDate("PkgStartDate"),
                        rs.getDate("PkgEndDate"),
                        rs.getString("PkgDesc"),
                        rs.getBigDecimal("PkgBasePrice"),
                        rs.getBigDecimal("PkgAgencyCommission")));
            }
            cbPackages.setItems(list);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Selection of Package ID from the combobox will generate the package's record in the text fields -Katrina
        cbPackages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package aPackage, Package t1) {
                tfPackageId.setText(t1.getPackageId() + "");
                tfPackageName.setText(t1.getPkgName());
                tfStartDate.setText(t1.getPkgStartDate() + "");
                tfEndDate.setText(t1.getPkgEndDate() + "");
                tfDescription.setText(t1.getPkgDesc() + "");
                tfBasePrice.setText(t1.getPkgBasePrice() + "");
                tfAgencyCommission.setText(t1.getPkgAgencyCommission() + "");

                // Enables the edit button when a selection is made
                btnPackageEdit.setDisable(false);
            }
        });

        // Edit button will disable itself and enable the text fields and save button -Katrina
        btnPackageEdit.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tfPackageName.setDisable(false);
                tfStartDate.setDisable(false);
                tfEndDate.setDisable(false);
                tfDescription.setDisable(false);
                tfBasePrice.setDisable(false);
                tfAgencyCommission.setDisable(false);
                btnPackageEdit.setDisable(true);
                btnPackageSave.setDisable(false);
            }
        }));

        // Save button will update the package fields, enable the edit button, and disable itself and the text fields -Katrina
        btnPackageSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // SQL statement to update the package fields
                String sql = "UPDATE `packages` SET `PkgName`=?, `PkgStartDate`=?, "
                        + "`PkgEndDate`=?, `PkgDesc`=?, `PkgBasePrice`=?, "
                        + "`PkgAgencyCommission`=? WHERE `PackageId`=?";

                Connection conn = getConnection();
                try {
                    // Text fields used to set the package fields in database -Katrina
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, tfPackageName.getText());
                    stmt.setString(2, tfStartDate.getText());
                    stmt.setString(3, tfEndDate.getText());
                    stmt.setString(4, tfDescription.getText());
                    stmt.setString(5, tfBasePrice.getText());
                    stmt.setString(6, tfAgencyCommission.getText());

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.print(("update worked\n"));
                    }
                    else {
                        System.out.print("update failed\n");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                // Enables the edit button and disables itself and the text fields -Katrina
                tfPackageName.setDisable(true);
                tfStartDate.setDisable(true);
                tfEndDate.setDisable(true);
                tfDescription.setDisable(true);
                tfBasePrice.setDisable(true);
                tfAgencyCommission.setDisable(true);
                btnPackageEdit.setDisable(false);
                btnPackageSave.setDisable(true);
            }
        });
    }

    // getConnection method -Katrina
    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    // Customers
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
