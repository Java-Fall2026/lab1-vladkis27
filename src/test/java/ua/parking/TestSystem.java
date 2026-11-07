package ua.parking;

import org.junit.jupiter.api.Test;
import ua.parking.exception.InvalidDataException;
import ua.parking.model.Driver;
import ua.parking.model.ZoneType;
import ua.parking.model.Zone;
import ua.parking.util.DriverLoader;
import static org.junit.jupiter.api.Assertions.*;
public class TestSystem {
    @Test
    void testDriverCreation() {
        Driver driver = new Driver("12345", "Іван Іванов", "+380509876543");
        assertEquals("12345", driver.taxId(), "taxId has to equal");
        assertEquals("Іван Іванов", driver.FullName(), "Name has to equal");
    }
    @Test
    void testZoneCreation() {
        Zone zone = new Zone("Z-02", ZoneType.VIP, 50.0);
        assertEquals("Z-02", zone.getZoneCode(), "Code has to be equal");
        assertEquals(50.0, zone.getRatePerHour(), "Rate has to equal");
    }

    @Test
    void testParseLine() {
        String badLine = "12345,,+380509876543";
        assertThrows(InvalidDataException.class, () -> {DriverLoader.parseLine(badLine, 5);});
    }
    @Test
    void testParseLineNoComas() {
        String badline = "123456";
        assertThrows(InvalidDataException.class, () -> {
            DriverLoader.parseLine(badline, 10);
        });
    }

    @Test
    void testZoneCoef() {
        assertEquals(2.0, ZoneType.VIP.getCoef());
        assertEquals(1.5, ZoneType.UNDERGROUND.getCoef());
        assertEquals(1.0, ZoneType.OVERGROUND.getCoef());
    }
    @Test
    void testZoneSetter() {
        Zone zone = new Zone("A-02", ZoneType.UNDERGROUND, 30.0);
        assertThrows(IllegalArgumentException.class, () -> {
            zone.setRatePerHour(-10.0);
        });
        assertEquals(30.0, zone.getRatePerHour());
    }
}
