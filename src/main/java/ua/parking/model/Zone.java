package ua.parking.model;

import java.util.Objects;
import ua.parking.util.ParkingUtils;
import ua.common.BaseEntity;

public class Zone extends BaseEntity {
    private String zoneCode;
    private String type;
    private double ratePerHour;

    public Zone(String zoneCode, String type, double ratePerHour) {
        super();
        this.zoneCode = ParkingUtils.requireNotBlank(zoneCode, "Zone code");
        this.type = ParkingUtils.validateZoneType(type);
        setRatePerHour(ratePerHour);
    }

    public final void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ParkingUtils.requirePositive(ratePerHour, "Rate per hour");
    }
    public String getZoneCode() { return zoneCode; }
    public String getType() { return type; }
    public double getRatePerHour() { return ratePerHour; }
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) { return false; }
        Zone zone = (Zone) o;
        return Objects.equals(zoneCode, zone.zoneCode);
    }
    public int hashCode() {
        return Objects.hash(zoneCode);
    }
    public String toString() {
        return "Zone{" + zoneCode + "}, type=" + type + ", ratePerHour=" + ratePerHour + ". Created at: " + createdAt;
    }
}
