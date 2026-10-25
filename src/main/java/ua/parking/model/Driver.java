package ua.parking.model;

import ua.parking.util.ParkingUtils;

public record Driver(String taxId, String FullName, String phone) {
    public Driver {
        ParkingUtils.requireNotBlank(taxId, "Tax ID");
        ParkingUtils.requireNotBlank(FullName, "Full name");
        ParkingUtils.requireNotBlank(phone, "Phone");
    }
}