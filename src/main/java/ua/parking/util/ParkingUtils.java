package ua.parking.util;

import java.time.Duration;
import java.time.LocalDateTime;
import ua.parking.model.ZoneType;
import ua.parking.model.ParkingTicket;
public class ParkingUtils {
    public static void requireNotNull(Object obj, String fieldName) {
        ValidationHelper.requireNotNull(obj, fieldName);
    }
    public static String requireNotBlank(String value, String fieldName) {
        ValidationHelper.requireNotBlank(value, fieldName);
        return FormatHelper.normalize(value);
    }
    public static double requirePositive(double value, String fieldName) {
        ValidationHelper.requirePositive(value, fieldName);
        return value;
    }
    public static String normalizeVehiclePlate(String plate) {
        ValidationHelper.requireNotBlank(plate, "Vehicle plate");
        return FormatHelper.capitalize(plate);
    }
    public static void requireValidEntryTime(LocalDateTime entryTime) {
        ValidationHelper.requireNotNull(entryTime, "Entry time");
        if(entryTime.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Entry time cannot be in the future, got: " + entryTime);
        }
    }
    public static void requireValidExitTime(LocalDateTime entryTime, LocalDateTime exitTime) {
        if(exitTime != null && exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time must be after entry time(" + entryTime +"), got: " + exitTime);
        }
    }
    public static void requireValidPaymentTime(LocalDateTime entryTime, LocalDateTime paymentTime) {
        ValidationHelper.requireNotNull(paymentTime, "Payment time");
        if(paymentTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Payment time must be after entry time(" + entryTime + "), got: " + paymentTime);
        }
    }
    public static int parkedHours(ParkingTicket ticket) {
        if(ticket.getExitTime() == null) { return -1; }
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        return (int) (duration.toMinutes() + 59) / 60;
    }

    public static double cost(ParkingTicket ticket) {
        if(parkedHours(ticket) == -1) {
            return 0.0;
        }
        return parkedHours(ticket) * ticket.getZone().getRatePerHour();
    }
    public static String getZoneDescription(ZoneType zoneType) {
        return switch(zoneType) {
            case VIP -> "Зона підвищеного комфорту";
            case UNDERGROUND -> "Підземна парковка";
            case OVERGROUND -> "Наземна парковка";
        };
    }
    public static double getZoneCoef(ZoneType zoneType) {
        return switch(zoneType) {
            case VIP -> 2.0;
            case UNDERGROUND -> 1.5;
            case OVERGROUND -> 1.0;
        };
    }
}