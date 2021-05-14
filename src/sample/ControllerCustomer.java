/**
 * Sample Skeleton for 'customer.fxml' Controller Class
 */
//Customer controller compiled and put together -Chris
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

public class ControllerCustomer {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCustomerId"
    private TextField txtCustomerId; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustFirstName"
    private TextField txtCustFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustLastName"
    private TextField txtCustLastName; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustAddress"
    private TextField txtCustAddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustCity"
    private TextField txtCustCity; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustProv"
    private TextField txtCustProv; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustPostal"
    private TextField txtCustPostal; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustCountry"
    private TextField txtCustCountry; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustHomePhone"
    private TextField txtCustHomePhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustBusPhone"
    private TextField txtCustBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustEmail"
    private TextField txtCustEmail; // Value injected by FXMLLoader

    @FXML // fx:id="txtCustAgentId"
    private TextField txtCustAgentId; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustEdit"
    private Button btnCustEdit; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustSave"
    private Button btnCustSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustClose"
    private Button btnCustClose; // Value injected by FXMLLoader

    @FXML // fx:id="cboCustomer"
    private ComboBox<Customer> cboCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnDeleteCustomer"
    private Button btnDeleteCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddCustomer"
    private Button btnAddCustomer; // Value injected by FXMLLoader

    Connection conn = getConnection();

    //Made Katrina's code into method for use -Jack
    void getCustomers() {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from customers");
            ObservableList<Customer> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Customer(rs.getInt("CustomerId"),
                        rs.getString("CustFirstName"),
                        rs.getString("CustLastName"),
                        rs.getString("CustAddress"),
                        rs.getString("CustCity"),
                        rs.getString("CustProv"),
                        rs.getString("CustPostal"),
                        rs.getString("CustCountry"),
                        rs.getString("CustHomePhone"),
                        rs.getString("CustBusPhone"),
                        rs.getString("CustEmail"),
                        rs.getInt("AgentId")));
            }
            cboCustomer.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustFirstName != null : "fx:id=\"txtCustFirstName\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustLastName != null : "fx:id=\"txtCustLastName\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustAddress != null : "fx:id=\"txtCustAddress\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustCity != null : "fx:id=\"txtCustCity\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustProv != null : "fx:id=\"txtCustProv\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustPostal != null : "fx:id=\"txtCustPostal\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustCountry != null : "fx:id=\"txtCustCountry\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustHomePhone != null : "fx:id=\"txtCustHomePhone\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustBusPhone != null : "fx:id=\"txtCustBusPhone\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustEmail != null : "fx:id=\"txtCustEmail\" was not injected: check your FXML file 'customer.fxml'.";
        assert txtCustAgentId != null : "fx:id=\"txtCustAgentId\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnCustEdit != null : "fx:id=\"btnCustEdit\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnCustSave != null : "fx:id=\"btnCustSave\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnCustClose != null : "fx:id=\"btnCustClose\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnDeleteCustomer != null : "fx:id=\"btnDeleteCustomer\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'customer.fxml'.";
        assert cboCustomer != null : "fx:id=\"cboCustomer\" was not injected: check your FXML file 'customer.fxml'.";

        getCustomers();

        // Selection of Customer ID from the combobox will generate the customer's record in the text fields -Katrina
        cboCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer t1) {
                txtCustomerId.setText(t1.getCustomerId() + "");
                txtCustFirstName.setText(t1.getCustFirstName());
                txtCustLastName.setText(t1.getCustLastName());
                txtCustAddress.setText(t1.getCustAddress());
                txtCustCity.setText(t1.getCustCity());
                txtCustProv.setText(t1.getCustProv());
                txtCustPostal.setText(t1.getCustPostal());
                txtCustCountry.setText(t1.getCustCountry());
                txtCustHomePhone.setText(t1.getCustHomePhone());
                txtCustBusPhone.setText(t1.getCustBusPhone());
                txtCustEmail.setText(t1.getCustEmail());
                txtCustAgentId.setText(t1.getCustAgentId() + "");

                //enable edit/ disable other btn's and boxes -Jack
                txtCustFirstName.setDisable(true);
                txtCustLastName.setDisable(true);
                txtCustAddress.setDisable(true);
                txtCustCity.setDisable(true);
                txtCustProv.setDisable(true);
                txtCustPostal.setDisable(true);
                txtCustCountry.setDisable(true);
                txtCustHomePhone.setDisable(true);
                txtCustBusPhone.setDisable(true);
                txtCustEmail.setDisable(true);
                txtCustAgentId.setDisable(true);
                btnCustEdit.setDisable(false);
                btnCustSave.setDisable(true);
                btnAddCustomer.setDisable(true);
                btnDeleteCustomer.setDisable(true);
            }
        });

        // Edit button will disable itself and enable the text fields and save button -Katrina
        btnCustEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtCustFirstName.setDisable(false);
                txtCustLastName.setDisable(false);
                txtCustAddress.setDisable(false);
                txtCustCity.setDisable(false);
                txtCustProv.setDisable(false);
                txtCustPostal.setDisable(false);
                txtCustCountry.setDisable(false);
                txtCustHomePhone.setDisable(false);
                txtCustBusPhone.setDisable(false);
                txtCustEmail.setDisable(false);
                txtCustAgentId.setDisable(false);
                btnCustEdit.setDisable(true);
                btnCustSave.setDisable(false);
                btnDeleteCustomer.setDisable(false);
                btnAddCustomer.setDisable(false);
            }
        });

        // Save button will update the customer fields, enable the edit button, and disable itself and the text fields -Katrina
        btnCustSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // SQL statement to update the customer fields
                String sql = "UPDATE `customers` SET `CustFirstName`=?, `CustLastName`=?, "
                        + "`CustAddress`=?, `CustCity`=?, `CustProv`=?, `CustPostal`=?, "
                        + "`CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, "
                        + "`CustEmail`=?, `AgentId`=? WHERE `CustomerId`=?";

                Connection conn = getConnection();
                try {
                    // Text fields used to set the customer fields in database -Katrina
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtCustFirstName.getText());
                    stmt.setString(2, txtCustLastName.getText());
                    stmt.setString(3, txtCustAddress.getText());
                    stmt.setString(4, txtCustCity.getText());
                    stmt.setString(5, txtCustProv.getText());
                    stmt.setString(6, txtCustPostal.getText());
                    stmt.setString(7, txtCustCountry.getText());
                    stmt.setString(8, txtCustHomePhone.getText());
                    stmt.setString(9, txtCustBusPhone.getText());
                    stmt.setString(10, txtCustEmail.getText());
                    stmt.setString(11, txtCustAgentId.getText());
                    stmt.setInt(12, Integer.parseInt(txtCustomerId.getText()));

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
                txtCustFirstName.setDisable(true);
                txtCustLastName.setDisable(true);
                txtCustAddress.setDisable(true);
                txtCustCity.setDisable(true);
                txtCustProv.setDisable(true);
                txtCustPostal.setDisable(true);
                txtCustCountry.setDisable(true);
                txtCustHomePhone.setDisable(true);
                txtCustBusPhone.setDisable(true);
                txtCustEmail.setDisable(true);
                txtCustAgentId.setDisable(true);
                btnCustEdit.setDisable(false);
                btnCustSave.setDisable(true);
                btnAddCustomer.setDisable(true);
                btnDeleteCustomer.setDisable(true);
            }
        });
        //closes the customer view and returns you to the main menu -Chris
        btnCustClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    conn.close();
                    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    Stage window = (Stage) btnCustClose.getScene().getWindow();
                    window.setTitle("Database manager home page");
                    window.setScene(new Scene(root));
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //Adds CUstomer to database from current info - Jack
        btnAddCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // SQL statement to update the agent fields
                    String sql = "INSERT INTO customers (CustFirstName, CustLastName, "
                            + "CustAddress, CustCity, CustProv, "
                            + "CustPostal, CustCountry, CustHomePhone,"
                            + "   CustBusPhone, CustEmail, AgentId)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



                    Connection conn = getConnection();

                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtCustFirstName.getText());
                        stmt.setString(2, txtCustLastName.getText());
                        stmt.setString(3, txtCustAddress.getText());
                        stmt.setString(4, txtCustCity.getText());
                        stmt.setString(5, txtCustProv.getText());
                        stmt.setString(6, txtCustPostal.getText());
                        stmt.setString(7, txtCustCountry.getText());
                        stmt.setString(8, txtCustHomePhone.getText());
                        stmt.setString(9, txtCustBusPhone.getText());
                        stmt.setString(10, txtCustEmail.getText());
                        stmt.setString(11, txtCustAgentId.getText());

                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println(("update worked"));
                            btnAddCustomer.setDisable(true);
                            txtCustFirstName.setDisable(true);
                            txtCustLastName.setDisable(true);
                            txtCustAddress.setDisable(true);
                            txtCustCity.setDisable(true);
                            txtCustProv.setDisable(true);
                            txtCustPostal.setDisable(true);
                            txtCustCountry.setDisable(true);
                            txtCustHomePhone.setDisable(true);
                            txtCustBusPhone.setDisable(true);
                            txtCustEmail.setDisable(true);
                            txtCustAgentId.setDisable(true);
                            btnCustEdit.setDisable(false);
                            btnCustSave.setDisable(true);
                            btnDeleteCustomer.setDisable(true);
                            getCustomers();
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

        //Deletes current Customer -Jack
        btnDeleteCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try
                {
                    String sql = "Delete From customers Where CustomerId = ?";
                    Connection conn = getConnection();

                    try
                    {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtCustomerId.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.print(("update worked\n"));
                            btnAddCustomer.setDisable(true);
                            txtCustFirstName.setDisable(true);
                            txtCustLastName.setDisable(true);
                            txtCustAddress.setDisable(true);
                            txtCustCity.setDisable(true);
                            txtCustProv.setDisable(true);
                            txtCustPostal.setDisable(true);
                            txtCustCountry.setDisable(true);
                            txtCustHomePhone.setDisable(true);
                            txtCustBusPhone.setDisable(true);
                            txtCustEmail.setDisable(true);
                            txtCustAgentId.setDisable(true);
                            btnCustEdit.setDisable(false);
                            btnCustSave.setDisable(true);
                            btnDeleteCustomer.setDisable(true);
                            getCustomers();
                        } else {
                            System.out.print("update failed\n");
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
