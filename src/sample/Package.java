package sample;

import java.math.BigDecimal;
import java.sql.Date;

public class Package {
    private int packageId;
    private String pkgName;
    private Date pkgStartDate;
    private Date pkgEndDate;
    private String pkgDesc;
    private BigDecimal pkgBasePrice;
    private BigDecimal pkgAgencyCommission;

    // Constructor
    public Package(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc, BigDecimal pkgBasePrice, BigDecimal pkgAgencyCommission) {
        this.packageId = packageId;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
        this.pkgEndDate = pkgEndDate;
        this.pkgDesc = pkgDesc;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    // Getters and setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Date getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public BigDecimal getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public BigDecimal getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    // toString method
    @Override
    public String toString() {
        return packageId + "";
    }
}
