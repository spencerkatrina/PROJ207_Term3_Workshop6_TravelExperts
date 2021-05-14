/**
 * Sample Skeleton for 'package.fxml' Controller Class
 */
//Package controller compiled and put together -Chris
package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPackage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPackageId"
    private TextField txtPackageId; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgName"
    private TextField txtPkgName; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgStartDate"
    private TextField txtPkgStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgEndDate"
    private TextField txtPkgEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgDesc"
    private TextField txtPkgDesc; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgBasePrice"
    private TextField txtPkgBasePrice; // Value injected by FXMLLoader

    @FXML // fx:id="txtPkgAgencyCommission"
    private TextField txtPkgAgencyCommission; // Value injected by FXMLLoader

    @FXML // fx:id="cboPackage"
    private ComboBox<Package> cboPackage; // Value injected by FXMLLoader

    @FXML // fx:id="btnPkgEdit"
    private Button btnPkgEdit; // Value injected by FXMLLoader

    @FXML // fx:id="btnPkgSave"
    private Button btnPkgSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnPkgClose"
    private Button btnPkgClose; // Value injected by FXMLLoader

    @FXML
    private Button btnDeletePackage;

    @FXML
    private Button btnAddPackage;

    Connection conn = getConnection();

    //Turned Katrina's code into a method for re-use -Jack
    void getPackages() {
        try {
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
            cboPackage.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgName != null : "fx:id=\"txtPkgName\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgStartDate != null : "fx:id=\"txtPkgStartDate\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgEndDate != null : "fx:id=\"txtPkgEndDate\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgDesc != null : "fx:id=\"txtPkgDesc\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgBasePrice != null : "fx:id=\"txtPkgBasePrice\" was not injected: check your FXML file 'package.fxml'.";
        assert txtPkgAgencyCommission != null : "fx:id=\"txtPkgAgencyCommission\" was not injected: check your FXML file 'package.fxml'.";
        assert cboPackage != null : "fx:id=\"cboPackage\" was not injected: check your FXML file 'package.fxml'.";
        assert btnPkgEdit != null : "fx:id=\"btnPkgEdit\" was not injected: check your FXML file 'package.fxml'.";
        assert btnPkgSave != null : "fx:id=\"btnPkgSave\" was not injected: check your FXML file 'package.fxml'.";
        assert btnPkgClose != null : "fx:id=\"btnPkgClose\" was not injected: check your FXML file 'package.fxml'.";
        assert btnDeletePackage != null : "fx:id=\"btnDeletePackage\" was not injected: check your FXML file 'package.fxml'.";
        assert btnAddPackage != null : "fx:id=\"btnAddPackage\" was not injected: check your FXML file 'package.fxml'.";

        getPackages();

        // Selection of Package ID from the combobox will generate the package's record in the text fields -Katrina
        cboPackage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package aPackage, Package t1) {
                txtPackageId.setText(t1.getPackageId() + "");
                txtPkgName.setText(t1.getPkgName());
                txtPkgStartDate.setText(t1.getPkgStartDate() + "");
                txtPkgEndDate.setText(t1.getPkgEndDate() + "");
                txtPkgDesc.setText(t1.getPkgDesc());
                txtPkgBasePrice.setText(t1.getPkgBasePrice() + "");
                txtPkgAgencyCommission.setText(t1.getPkgAgencyCommission() + "");

                // Enable edit / Disable btn's and textboxes -Jack
                txtPkgName.setDisable(true);
                txtPkgStartDate.setDisable(true);
                txtPkgEndDate.setDisable(true);
                txtPkgDesc.setDisable(true);
                txtPkgBasePrice.setDisable(true);
                txtPkgAgencyCommission.setDisable(true);
                btnPkgEdit.setDisable(false);
                btnPkgSave.setDisable(true);
                btnAddPackage.setDisable(true);
                btnDeletePackage.setDisable(true);
            }
        });

        // Edit button will disable itself and enable the text fields and save button -Katrina
        btnPkgEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtPkgName.setDisable(false);
                txtPkgStartDate.setDisable(false);
                txtPkgEndDate.setDisable(false);
                txtPkgDesc.setDisable(false);
                txtPkgBasePrice.setDisable(false);
                txtPkgAgencyCommission.setDisable(false);
                btnPkgEdit.setDisable(true);
                btnPkgSave.setDisable(false);
                btnAddPackage.setDisable(false);
                btnDeletePackage.setDisable(false);
            }
        });

        // Save button will update the package fields, enable the edit button, and disable itself and the text fields -Katrina
        btnPkgSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // SQL statement to update the package fields -Katrina
                String sql = "UPDATE `packages` SET `PkgName`=?, `PkgStartDate`=?, "
                        + "`PkgEndDate`=?, `PkgDesc`=?, `PkgBasePrice`=?, "
                        + "`PkgAgencyCommission`=? WHERE `PackageId`=?";

                Connection conn = getConnection();
                try {
                    // Text fields used to set the package fields in database -Katrina
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtPkgName.getText());
                    stmt.setString(2, txtPkgStartDate.getText());
                    stmt.setString(3, txtPkgEndDate.getText());
                    stmt.setString(4, txtPkgDesc.getText());
                    stmt.setString(5, txtPkgBasePrice.getText());
                    stmt.setString(6, txtPkgAgencyCommission.getText());
                    stmt.setInt(7, Integer.parseInt(txtPackageId.getText()));

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
                txtPkgName.setDisable(true);
                txtPkgStartDate.setDisable(true);
                txtPkgEndDate.setDisable(true);
                txtPkgDesc.setDisable(true);
                txtPkgBasePrice.setDisable(true);
                txtPkgAgencyCommission.setDisable(true);
                btnPkgEdit.setDisable(false);
                btnPkgSave.setDisable(true);
                btnAddPackage.setDisable(true);
                btnDeletePackage.setDisable(true);
            }
        });

        // Close button to return user to the main menu
        btnPkgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    conn.close();
                    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    Stage window = (Stage) btnPkgClose.getScene().getWindow();
                    window.setTitle("Database manager home page");
                    window.setScene(new Scene(root));
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //Adds Current Package info as new package -Jack
        btnAddPackage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // SQL statement to update the agent fields
                    String sql = "INSERT INTO packages (PkgName, pkgStartDate, "
                            + "PkgEndDate, PkgDesc, PkgBasePrice, "
                            + "PkgAgencyCommission)"
                            + " VALUES (?, ?, ?, ?, ?, ?)";



                    Connection conn = getConnection();

                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtPkgName.getText());
                        stmt.setString(2, txtPkgStartDate.getText());
                        stmt.setString(3, txtPkgEndDate.getText());
                        stmt.setString(4, txtPkgDesc.getText());
                        stmt.setString(5, txtPkgBasePrice.getText());
                        stmt.setString(6, txtPkgAgencyCommission.getText());

                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println(("update worked"));
                            btnAddPackage.setDisable(true);
                            txtPackageId.setDisable(true);
                            txtPkgName.setDisable(true);
                            txtPkgDesc.setDisable(true);
                            txtPkgStartDate.setDisable(true);
                            txtPkgEndDate.setDisable(true);
                            txtPkgAgencyCommission.setDisable(true);
                            txtPkgBasePrice.setDisable(true);
                            btnPkgEdit.setDisable(true);
                            btnPkgSave.setDisable(true);
                            btnDeletePackage.setDisable(true);
                            getPackages();
                        } else {
                            System.out.println("update failed");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Deletes currently selected package -Jack
        btnDeletePackage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try
                {
                    String sql = "Delete From packages Where packageId = ?";
                    Connection conn = getConnection();

                    try
                    {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtPackageId.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println(("update worked"));
                            btnAddPackage.setDisable(true);
                            txtPackageId.setDisable(true);
                            txtPkgName.setDisable(true);
                            txtPkgDesc.setDisable(true);
                            txtPkgStartDate.setDisable(true);
                            txtPkgEndDate.setDisable(true);
                            txtPkgAgencyCommission.setDisable(true);
                            txtPkgBasePrice.setDisable(true);
                            btnPkgEdit.setDisable(true);
                            btnPkgSave.setDisable(true);
                            btnDeletePackage.setDisable(true);
                            getPackages();
                        } else {
                            System.out.println("update failed");
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                } catch(Exception e)
                {
                    e.printStackTrace();
                }
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
}

