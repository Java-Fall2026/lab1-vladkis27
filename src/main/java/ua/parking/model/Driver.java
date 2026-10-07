package ua.parking.model;

import java.util.Objects;
import ua.common.BaseEntity;
import ua.parking.util.ParkingUtils;
public class Driver extends BaseEntity {
    private String taxId;
    private String fullName;
    private String phone;

    public Driver(String taxId, String fullName, String phone) {
        super();
        this.taxId = ParkingUtils.requireNotBlank(taxId, "Tax ID");
        this.fullName = ParkingUtils.requireNotBlank(fullName, "Full name");
        this.phone = ParkingUtils.requireNotBlank(phone, "Phone");
    }


    public String getTaxId() { return taxId; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) { return false; }
        Driver driver = (Driver) o;
        return Objects.equals(taxId, driver.taxId);
    }
    public int hashCode() {
        return Objects.hash(taxId);
    }
    public String toString() {
        return "Driver{" + taxId + "}, fullname=" + fullName + ", phone=" + phone + ". Created at: " + createdAt;
    }
}
