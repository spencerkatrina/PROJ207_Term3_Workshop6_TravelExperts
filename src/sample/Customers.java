package sample;

public class Customers {
    private int agentId;
    private String CustAddress;
    private String CustBusPhone;
    private String CustCity;
    private String CustCountry;
    private String CustEmail;
    private String CustFirstName;
    private String CustLastName;
    private String CustHomePhone;
    private String CustPostal;
    private String CustProv;
    private int CustId;

    public Customers(int agentId, String custAddress, String custBusPhone, String custCity, String custCountry, String custEmail, String custFirstName, String custLastName, String custHomePhone, String custPostal, String custProv, int custId) {
        this.agentId = agentId;
        CustAddress = custAddress;
        CustBusPhone = custBusPhone;
        CustCity = custCity;
        CustCountry = custCountry;
        CustEmail = custEmail;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustHomePhone = custHomePhone;
        CustPostal = custPostal;
        CustProv = custProv;
        CustId = custId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCustBusPhone() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        CustBusPhone = custBusPhone;
    }

    public String getCustCity() {
        return CustCity;
    }

    public void setCustCity(String custCity) {
        CustCity = custCity;
    }

    public String getCustCountry() {
        return CustCountry;
    }

    public void setCustCountry(String custCountry) {
        CustCountry = custCountry;
    }

    public String getCustEmail() {
        return CustEmail;
    }

    public void setCustEmail(String custEmail) {
        CustEmail = custEmail;
    }

    public String getCustFirstName() {
        return CustFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        CustFirstName = custFirstName;
    }

    public String getCustLastName() {
        return CustLastName;
    }

    public void setCustLastName(String custLastName) {
        CustLastName = custLastName;
    }

    public String getCustHomePhone() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        CustHomePhone = custHomePhone;
    }

    public String getCustPostal() {
        return CustPostal;
    }

    public void setCustPostal(String custPostal) {
        CustPostal = custPostal;
    }

    public String getCustProv() {
        return CustProv;
    }

    public void setCustProv(String custProv) {
        CustProv = custProv;
    }

    public int getCustId() {
        return CustId;
    }

    public void setCustId(int custId) {
        CustId = custId;
    }
}
