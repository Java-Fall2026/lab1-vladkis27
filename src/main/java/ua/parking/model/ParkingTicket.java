package ua.parking.model;

import java.time.LocalDateTime;
import java.util.Objects;

import ua.common.BaseEntity;
import ua.parking.util.ParkingUtils;
public class ParkingTicket extends BaseEntity {
    private String vehiclePlate;
    private Zone zone;
    private Driver driver;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    private ParkingTicket(String vehiclePlate, Zone zone, Driver driver, LocalDateTime entryTime, LocalDateTime exitTime) {
        super();
        this.vehiclePlate = ParkingUtils.normalizeVehiclePlate(vehiclePlate);
        ParkingUtils.requireNotNull(zone, "Zone");
        this.zone = zone;
        ParkingUtils.requireNotNull(driver, "Driver");
        this.driver = driver;
        ParkingUtils.requireValidEntryTime(entryTime);
        this.entryTime = entryTime;
        setExitTime(exitTime);
    }
    public static ParkingTicket of(String vehiclePlate, Zone zone, Driver driver, LocalDateTime entryTime, LocalDateTime exitTime) {
        return new ParkingTicket(vehiclePlate, zone, driver, entryTime, exitTime);
    }
    public final void setExitTime(LocalDateTime exitTime) {
        ua.parking.util.ParkingUtils.requireValidExitTime(entryTime, exitTime);
        this.exitTime = exitTime;

    }
    public String getVehiclePlate() { return vehiclePlate; }
    public Zone getZone() { return zone; }
    public Driver getDriver() { return driver; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) { return false; }
        ParkingTicket ticket = (ParkingTicket) o;
        return Objects.equals(vehiclePlate, ticket.vehiclePlate) && Objects.equals(driver, ticket.driver);
    }
    public int hashCode() {
        return Objects.hash(vehiclePlate, entryTime);
    }
    public String toString() {
        return "ParkingTicket{" + vehiclePlate + "}, zone=" + zone.getZoneCode() + ", driver=" + driver.getFullName() + ", entry time: " + entryTime + ", exit time: " + exitTime + ". Created at: " + createdAt;
    }
}
