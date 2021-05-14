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

public class ControllerAgent {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    /*********************** AGENT Controller ***********************/

    @FXML // fx:id="txtAgentId"
    private TextField txtAgentId; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtFirstName"
    private TextField txtAgtFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtMiddleInitial"
    private TextField txtAgtMiddleInitial; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtLastName"
    private TextField txtAgtLastName; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtBusPhone"
    private TextField txtAgtBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtEmail"
    private TextField txtAgtEmail; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgtPosition"
    private TextField txtAgtPosition; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgencyId"
    private TextField txtAgencyId; // Value injected by FXMLLoader

    @FXML // fx:id="cboAgent"
    private ComboBox<Agent> cboAgent; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgtEdit"
    private Button btnAgtEdit; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgtSave"
    private Button btnAgtSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgtClose"
    private Button btnAgtClose; // Value injected by FXMLLoader

    @FXML
    private Button btnAddAgent;

    @FXML
    private Button btnAgtDelete;

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

    Connection conn = getConnection();

    void getAgents() {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from agents");
            ObservableList<Agent> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Agent(rs.getInt("AgentId"),
                        rs.getString("AgtFirstName"),
                        rs.getString("AgtMiddleInitial"),
                        rs.getString("AgtLastName"),
                        rs.getString("AgtBusPhone"),
                        rs.getString("AgtEmail"),
                        rs.getString("AgtPosition"),
                        rs.getInt("AgencyId")));
            }
            cboAgent.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAgentId != null : "fx:id=\"txtAgentId\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtFirstName != null : "fx:id=\"txtAgtFirstName\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtMiddleInitial != null : "fx:id=\"txtAgtMiddleInitial\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtLastName != null : "fx:id=\"txtAgtLastName\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtBusPhone != null : "fx:id=\"txtAgtBusPhone\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtEmail != null : "fx:id=\"txtAgtEmail\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgtPosition != null : "fx:id=\"txtAgtPosition\" was not injected: check your FXML file 'agent.fxml'.";
        assert txtAgencyId != null : "fx:id=\"txtAgencyId\" was not injected: check your FXML file 'agent.fxml'.";
        assert cboAgent != null : "fx:id=\"cboAgent\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnAgtEdit != null : "fx:id=\"btnAgtEdit\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnAgtSave != null : "fx:id=\"btnAgtSave\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnAgtClose != null : "fx:id=\"btnAgtClose\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnAddAgent != null : "fx:id=\"btnAddAgent\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnAgtDelete != null : "fx:id=\"btnAgtDelete\" was not injected: check your FXML file 'agent.fxml'.";

        getAgents();

        // Selection of Agent ID from the combobox will generate the agent's record in the text fields -Katrina
        cboAgent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observableValue, Agent agent, Agent t1) {
                txtAgentId.setText(t1.getAgentId() + "");
                txtAgtFirstName.setText(t1.getAgtFirstName());
                txtAgtMiddleInitial.setText(t1.getAgtMiddleInitial());
                txtAgtLastName.setText(t1.getAgtLastName());
                txtAgtBusPhone.setText(t1.getAgtBusPhone());
                txtAgtEmail.setText(t1.getAgtEmail());
                txtAgtPosition.setText(t1.getAgtPosition());
                txtAgencyId.setText(t1.getAgencyId() + "");

                //enable edit/ disable other btn's and boxes
                txtAgtFirstName.setDisable(true);
                txtAgtMiddleInitial.setDisable(true);
                txtAgtLastName.setDisable(true);
                txtAgtBusPhone.setDisable(true);
                txtAgtEmail.setDisable(true);
                txtAgtPosition.setDisable(true);
                txtAgencyId.setDisable(true);
                btnAgtEdit.setDisable(false);
                btnAgtSave.setDisable(true);
            }
        });

        // Edit button will disable itself and enable the text fields and save button -Katrina
        btnAgtEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtAgtFirstName.setDisable(false);
                txtAgtMiddleInitial.setDisable(false);
                txtAgtLastName.setDisable(false);
                txtAgtBusPhone.setDisable(false);
                txtAgtEmail.setDisable(false);
                txtAgtPosition.setDisable(false);
                txtAgencyId.setDisable(false);
                btnAgtEdit.setDisable(true);
                btnAgtSave.setDisable(false);
                btnAddAgent.setDisable(false);
                btnAgtDelete.setDisable(false);
            }
        });

        // Save button will update the agent fields, enable the edit button, and disable itself and the text fields -Katrina
        btnAgtSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // SQL statement to update the agent fields
                String sql = "UPDATE `agents` SET `AgtFirstName`=?, `AgtMiddleInitial`=?, "
                        + "`AgtLastName`=?, `AgtBusPhone`=?, `AgtEmail`=?, "
                        + "`AgtPosition`=?, `AgencyId`=? WHERE `AgentId`=?";

                Connection conn = getConnection();
                try {
                    // Text fields used to set the agent fields in database -Katrina
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtAgtFirstName.getText());
                    stmt.setString(2, txtAgtMiddleInitial.getText());
                    stmt.setString(3, txtAgtLastName.getText());
                    stmt.setString(4, txtAgtBusPhone.getText());
                    stmt.setString(5, txtAgtEmail.getText());
                    stmt.setString(6, txtAgtPosition.getText());
                    stmt.setInt(7, Integer.parseInt(txtAgencyId.getText()));
                    stmt.setInt(8, Integer.parseInt(txtAgentId.getText()));

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
                txtAgtFirstName.setDisable(true);
                txtAgtMiddleInitial.setDisable(true);
                txtAgtLastName.setDisable(true);
                txtAgtBusPhone.setDisable(true);
                txtAgtEmail.setDisable(true);
                txtAgtPosition.setDisable(true);
                txtAgencyId.setDisable(true);
                btnAgtEdit.setDisable(false);
                btnAgtSave.setDisable(true);
            }
        });

        // Close button to return the user to the main menu -Chris
        btnAgtClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    conn.close();
                    Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                    Stage window = (Stage) btnAgtClose.getScene().getWindow();
                    window.setTitle("Database manager home page");
                    window.setScene(new Scene(root));
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //Create new agent from current boxes and add to database -Jack
        btnAddAgent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // SQL statement to update the agent fields
                    String sql = "INSERT INTO agents (AgtFirstName, AgtMiddleInitial, "
                            + "AgtLastName, AgtBusPhone, AgtEmail, "
                            + "AgtPosition, AgencyId)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?)";



                    Connection conn = getConnection();

                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtAgtFirstName.getText());
                        stmt.setString(2, txtAgtMiddleInitial.getText());
                        stmt.setString(3, txtAgtLastName.getText());
                        stmt.setString(4, txtAgtBusPhone.getText());
                        stmt.setString(5, txtAgtEmail.getText());
                        stmt.setString(6, txtAgtPosition.getText());
                        stmt.setInt(7, Integer.parseInt(txtAgencyId.getText()));

                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.print(("update worked\n"));
                            btnAddAgent.setDisable(true);
                            txtAgtFirstName.setDisable(true);
                            txtAgtMiddleInitial.setDisable(true);
                            txtAgtLastName.setDisable(true);
                            txtAgtBusPhone.setDisable(true);
                            txtAgtEmail.setDisable(true);
                            txtAgtPosition.setDisable(true);
                            txtAgencyId.setDisable(true);
                            btnAgtEdit.setDisable(false);
                            btnAgtSave.setDisable(true);
                            getAgents();
                        } else {
                            System.out.print("update failed\n");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Delete currently Selected Agent -Jack
        btnAgtDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try
                {
                    String sql = "Delete From agents Where AgentId = ?";
                    Connection conn = getConnection();

                    try
                    {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, txtAgentId.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.print(("update worked\n"));
                            btnAddAgent.setDisable(true);
                            txtAgtFirstName.setDisable(true);
                            txtAgtMiddleInitial.setDisable(true);
                            txtAgtLastName.setDisable(true);
                            txtAgtBusPhone.setDisable(true);
                            txtAgtEmail.setDisable(true);
                            txtAgtPosition.setDisable(true);
                            txtAgencyId.setDisable(true);
                            btnAgtEdit.setDisable(true);
                            btnAgtSave.setDisable(true);
                            btnAgtDelete.setDisable(true);
                            getAgents();
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
}



    /*********************** CUSTOMER Controller ***********************/

//    @FXML // fx:id="txtCustomerId"
//    private TextField txtCustomerId; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustFirstName"
//    private TextField txtCustFirstName; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustLastName"
//    private TextField txtCustLastName; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustAddress"
//    private TextField txtCustAddress; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustCity"
//    private TextField txtCustCity; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustProv"
//    private TextField txtCustProv; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustPostal"
//    private TextField txtCustPostal; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustCountry"
//    private TextField txtCustCountry; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustHomePhone"
//    private TextField txtCustHomePhone; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustBusPhone"
//    private TextField txtCustBusPhone; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustEmail"
//    private TextField txtCustEmail; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtCustAgentId"
//    private TextField txtCustAgentId; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnCustEdit"
//    private Button btnCustEdit; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnCustSave"
//    private Button btnCustSave; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnCustClose"
//    private Button btnCustClose; // Value injected by FXMLLoader
//
//    @FXML // fx:id="cboCustomer"
//    private ComboBox<Customer> cboCustomer; // Value injected by FXMLLoader
//
//    @FXML // This method is called by the FXMLLoader when initialization is complete
//    void initialize() {
//        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustFirstName != null : "fx:id=\"txtCustFirstName\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustLastName != null : "fx:id=\"txtCustLastName\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustAddress != null : "fx:id=\"txtCustAddress\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustCity != null : "fx:id=\"txtCustCity\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustProv != null : "fx:id=\"txtCustProv\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustPostal != null : "fx:id=\"txtCustPostal\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustCountry != null : "fx:id=\"txtCustCountry\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustHomePhone != null : "fx:id=\"txtCustHomePhone\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustBusPhone != null : "fx:id=\"txtCustBusPhone\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustEmail != null : "fx:id=\"txtCustEmail\" was not injected: check your FXML file 'customer.fxml'.";
//        assert txtCustAgentId != null : "fx:id=\"txtCustAgentId\" was not injected: check your FXML file 'customer.fxml'.";
//        assert btnCustEdit != null : "fx:id=\"btnCustEdit\" was not injected: check your FXML file 'customer.fxml'.";
//        assert btnCustSave != null : "fx:id=\"btnCustSave\" was not injected: check your FXML file 'customer.fxml'.";
//        assert btnCustClose != null : "fx:id=\"btnCustClose\" was not injected: check your FXML file 'customer.fxml'.";
//        assert cboCustomer != null : "fx:id=\"cboCustomer\" was not injected: check your FXML file 'customer.fxml'.";
//
//        // Connection to customers table -Katrina
//        try {
//            Connection conn = getConnection();
//            Statement stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery("select * from customers");
//            ObservableList<Customer> list = FXCollections.observableArrayList();
//            while (rs.next()) {
//                list.add(new Customer(rs.getInt("CustomerId"),
//                        rs.getString("CustFirstName"),
//                        rs.getString("CustLastName"),
//                        rs.getString("CustAddress"),
//                        rs.getString("CustCity"),
//                        rs.getString("CustProv"),
//                        rs.getString("CustPostal"),
//                        rs.getString("CustCountry"),
//                        rs.getString("CustHomePhone"),
//                        rs.getString("CustBusPhone"),
//                        rs.getString("CustEmail"),
//                        rs.getInt("AgentId")));
//            }
//            cboCustomer.setItems(list);
//            conn.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        // Selection of Customer ID from the combobox will generate the customer's record in the text fields -Katrina
//        cboCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
//            @Override
//            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer t1) {
//                txtCustomerId.setText(t1.getCustomerId() + "");
//                txtCustFirstName.setText(t1.getCustFirstName());
//                txtCustLastName.setText(t1.getCustLastName());
//                txtCustAddress.setText(t1.getCustAddress());
//                txtCustCity.setText(t1.getCustCity());
//                txtCustProv.setText(t1.getCustProv());
//                txtCustPostal.setText(t1.getCustPostal());
//                txtCustCountry.setText(t1.getCustCountry());
//                txtCustHomePhone.setText(t1.getCustHomePhone());
//                txtCustBusPhone.setText(t1.getCustBusPhone());
//                txtCustEmail.setText(t1.getCustEmail());
//                txtCustAgentId.setText(t1.getCustAgentId() + "");
//
//                // Enables the edit button when a selection is made -Katrina
//                btnCustEdit.setDisable(false);
//            }
//        });
//
//        // Edit button will disable itself and enable the text fields and save button -Katrina
//        btnCustEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                txtCustFirstName.setDisable(false);
//                txtCustLastName.setDisable(false);
//                txtCustAddress.setDisable(false);
//                txtCustCity.setDisable(false);
//                txtCustProv.setDisable(false);
//                txtCustPostal.setDisable(false);
//                txtCustCountry.setDisable(false);
//                txtCustHomePhone.setDisable(false);
//                txtCustBusPhone.setDisable(false);
//                txtCustEmail.setDisable(false);
//                txtCustAgentId.setDisable(false);
//                btnCustEdit.setDisable(true);
//                btnCustSave.setDisable(false);
//            }
//        });
//
//        // Save button will update the customer fields, enable the edit button, and disable itself and the text fields -Katrina
//        btnCustSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                // SQL statement to update the customer fields
//                String sql = "UPDATE `customers` SET `CustFirstName`=?, `CustLastName`=?, "
//                        + "`CustAddress`=?, `CustCity`=?, `CustProv`=?, `CustPostal`=?, "
//                        + "`CustCountry`=?, `CustHomePhone`=?, `CustBusPhone`=?, "
//                        + "`CustEmail`=?, `AgentId`=? WHERE `CustomerId`=?";
//
//                Connection conn = getConnection();
//                try {
//                    // Text fields used to set the customer fields in database -Katrina
//                    PreparedStatement stmt = conn.prepareStatement(sql);
//                    stmt.setString(1, txtCustFirstName.getText());
//                    stmt.setString(2, txtCustLastName.getText());
//                    stmt.setString(3, txtCustAddress.getText());
//                    stmt.setString(4, txtCustCity.getText());
//                    stmt.setString(5, txtCustProv.getText());
//                    stmt.setString(6, txtCustPostal.getText());
//                    stmt.setString(7, txtCustCountry.getText());
//                    stmt.setString(8, txtCustHomePhone.getText());
//                    stmt.setString(9, txtCustBusPhone.getText());
//                    stmt.setString(10, txtCustEmail.getText());
//                    stmt.setString(11, txtCustAgentId.getText());
//                    stmt.setInt(12, Integer.parseInt(txtCustomerId.getText()));
//
//                    int rowsAffected = stmt.executeUpdate();
//                    if (rowsAffected > 0) {
//                        System.out.print(("update worked\n"));
//                    }
//                    else {
//                        System.out.print("update failed\n");
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                // Enables the edit button and disables itself and the text fields -Katrina
//                txtCustFirstName.setDisable(true);
//                txtCustLastName.setDisable(true);
//                txtCustAddress.setDisable(true);
//                txtCustCity.setDisable(true);
//                txtCustProv.setDisable(true);
//                txtCustPostal.setDisable(true);
//                txtCustCountry.setDisable(true);
//                txtCustHomePhone.setDisable(true);
//                txtCustBusPhone.setDisable(true);
//                txtCustEmail.setDisable(true);
//                txtCustAgentId.setDisable(true);
//                btnCustEdit.setDisable(false);
//                btnCustSave.setDisable(true);
//            }
//        });
//    }

    /*********************** MENU Controller ***********************/
//
//    @FXML // fx:id="cboMenu"
//    private ComboBox<?> cboMenu; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnMenuOK"
//    private Button btnMenuOK; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnMenuExit"
//    private Button btnMenuExit; // Value injected by FXMLLoader
//
//    @FXML // This method is called by the FXMLLoader when initialization is complete
//    void initialize() {
//        assert cboMenu != null : "fx:id=\"cboMenu\" was not injected: check your FXML file 'menu.fxml'.";
//        assert btnMenuOK != null : "fx:id=\"btnMenuOK\" was not injected: check your FXML file 'menu.fxml'.";
//        assert btnMenuExit != null : "fx:id=\"btnMenuExit\" was not injected: check your FXML file 'menu.fxml'.";
//    }

    /*********************** PACKAGE Controller ***********************/
//
//    @FXML // fx:id="txtPackageId"
//    private TextField txtPackageId; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgName"
//    private TextField txtPkgName; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgStartDate"
//    private TextField txtPkgStartDate; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgEndDate"
//    private TextField txtPkgEndDate; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgDesc"
//    private TextField txtPkgDesc; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgBasePrice"
//    private TextField txtPkgBasePrice; // Value injected by FXMLLoader
//
//    @FXML // fx:id="txtPkgAgencyCommission"
//    private TextField txtPkgAgencyCommission; // Value injected by FXMLLoader
//
//    @FXML // fx:id="cboPackage"
//    private ComboBox<Package> cboPackage; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnPkgEdit"
//    private Button btnPkgEdit; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnPkgSave"
//    private Button btnPkgSave; // Value injected by FXMLLoader
//
//    @FXML // fx:id="btnPkgClose"
//    private Button btnPkgClose; // Value injected by FXMLLoader
//
//    @FXML // This method is called by the FXMLLoader when initialization is complete
//    void initialize() {
//        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgName != null : "fx:id=\"txtPkgName\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgStartDate != null : "fx:id=\"txtPkgStartDate\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgEndDate != null : "fx:id=\"txtPkgEndDate\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgDesc != null : "fx:id=\"txtPkgDesc\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgBasePrice != null : "fx:id=\"txtPkgBasePrice\" was not injected: check your FXML file 'package.fxml'.";
//        assert txtPkgAgencyCommission != null : "fx:id=\"txtPkgAgencyCommission\" was not injected: check your FXML file 'package.fxml'.";
//        assert cboPackage != null : "fx:id=\"cboPackage\" was not injected: check your FXML file 'package.fxml'.";
//        assert btnPkgEdit != null : "fx:id=\"btnPkgEdit\" was not injected: check your FXML file 'package.fxml'.";
//        assert btnPkgSave != null : "fx:id=\"btnPkgSave\" was not injected: check your FXML file 'package.fxml'.";
//        assert btnPkgClose != null : "fx:id=\"btnPkgClose\" was not injected: check your FXML file 'package.fxml'.";
//
//        // Connection to packages table -Katrina
//        try {
//            Connection conn = getConnection();
//            Statement stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery("select * from packages");
//            ObservableList<Package> list = FXCollections.observableArrayList();
//            while (rs.next()) {
//                list.add(new Package(rs.getInt("PackageId"),
//                        rs.getString("PkgName"),
//                        rs.getDate("PkgStartDate"),
//                        rs.getDate("PkgEndDate"),
//                        rs.getString("PkgDesc"),
//                        rs.getBigDecimal("PkgBasePrice"),
//                        rs.getBigDecimal("PkgAgencyCommission")));
//            }
//            cboPackage.setItems(list);
//            conn.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        // Selection of Package ID from the combobox will generate the package's record in the text fields -Katrina
//        cboPackage.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
//            @Override
//            public void changed(ObservableValue<? extends Package> observableValue, Package aPackage, Package t1) {
//                txtPackageId.setText(t1.getPackageId() + "");
//                txtPkgName.setText(t1.getPkgName());
//                txtPkgStartDate.setText(t1.getPkgStartDate() + "");
//                txtPkgEndDate.setText(t1.getPkgEndDate() + "");
//                txtPkgDesc.setText(t1.getPkgDesc());
//                txtPkgBasePrice.setText(t1.getPkgBasePrice() + "");
//                txtPkgAgencyCommission.setText(t1.getPkgAgencyCommission() + "");
//
//                // Enables the edit button when a selection is made -Katrina
//                btnPkgEdit.setDisable(false);
//            }
//        });
//
//        // Edit button will disable itself and enable the text fields and save button -Katrina
//        btnPkgEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                txtPkgName.setDisable(false);
//                txtPkgStartDate.setDisable(false);
//                txtPkgEndDate.setDisable(false);
//                txtPkgDesc.setDisable(false);
//                txtPkgBasePrice.setDisable(false);
//                txtPkgAgencyCommission.setDisable(false);
//                btnPkgEdit.setDisable(true);
//                btnPkgSave.setDisable(false);
//            }
//        });
//
//        // Save button will update the package fields, enable the edit button, and disable itself and the text fields -Katrina
//        btnPkgSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                // SQL statement to update the package fields -Katrina
//                String sql = "UPDATE `packages` SET `PkgName`=?, `PkgStartDate`=?, "
//                        + "`PkgEndDate`=?, `PkgDesc`=?, `PkgBasePrice`=?, "
//                        + "`PkgAgencyCommission`=? WHERE `PackageId`=?";
//
//                Connection conn = getConnection();
//                try {
//                    // Text fields used to set the package fields in database -Katrina
//                    PreparedStatement stmt = conn.prepareStatement(sql);
//                    stmt.setString(1, txtPkgName.getText());
//                    stmt.setString(2, txtPkgStartDate.getText());
//                    stmt.setString(3, txtPkgEndDate.getText());
//                    stmt.setString(4, txtPkgDesc.getText());
//                    stmt.setString(5, txtPkgBasePrice.getText());
//                    stmt.setString(6, txtPkgAgencyCommission.getText());
//                    stmt.setInt(7, Integer.parseInt(txtPackageId.getText()));
//
//                    int rowsAffected = stmt.executeUpdate();
//                    if (rowsAffected > 0) {
//                        System.out.print(("update worked\n"));
//                    }
//                    else {
//                        System.out.print("update failed\n");
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                // Enables the edit button and disables itself and the text fields -Katrina
//                txtPkgName.setDisable(true);
//                txtPkgStartDate.setDisable(true);
//                txtPkgEndDate.setDisable(true);
//                txtPkgDesc.setDisable(true);
//                txtPkgBasePrice.setDisable(true);
//                txtPkgAgencyCommission.setDisable(true);
//                btnPkgEdit.setDisable(false);
//                btnPkgSave.setDisable(true);
//            }
//        });
//
//        // Close button will close the package window - Katrina
//        btnPkgClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });
//    }
