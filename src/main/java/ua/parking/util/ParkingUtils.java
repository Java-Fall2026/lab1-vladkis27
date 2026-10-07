package ua.parking.util;

import java.time.Duration;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;
import ua.parking.model.ParkingTicket;
public class ParkingUtils {
    private static List<String> valid_types = Arrays.asList("VIP", "UNDERGROUND", "OVERGROUND");
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
    public static String validateZoneType(String type) {
        ValidationHelper.requireNotBlank(type, "Zone type");
        String normalized = FormatHelper.capitalize(type);
        if(!valid_types.contains(normalized)) {
            throw new IllegalArgumentException("Type must be one of " + valid_types + ", got: " + normalized);
        }
        return normalized;
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
}