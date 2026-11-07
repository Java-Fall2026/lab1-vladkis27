package ua.parking;

import ua.parking.model.Driver;
import ua.parking.model.Zone;
import ua.parking.util.DriverLoader;
import ua.parking.model.ZoneType;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
       log.info("1. Reading existing file");
       List<Driver> drivers = DriverLoader.loadDrivers("data/Driverjavaaa.csv");
       for(Driver driver : drivers) {
        log.warn("Read driver: {}", driver.taxId());
       }
       log.info("2. Reading not existing file");
       DriverLoader.loadDrivers("data/driver.csv");
       log.info("3. Error in setter");
       try {
        Zone zone = new Zone("Z-01", ZoneType.VIP, 50.0);
        zone.setRatePerHour(-15.0);
       } catch (IllegalArgumentException e) {
        log.error("Setter error " + e.getMessage());
       }
    }
}