package ua.parking.model;
import java.time.LocalDateTime;
import ua.parking.util.ParkingUtils;
public record ParkingPayment(String paymentId, ParkingTicket ticket, double ammount, LocalDateTime paymentTime) {
    
    public ParkingPayment {
        ParkingUtils.requireNotBlank(paymentId, "Tax ID");
        ParkingUtils.requireNotNull(ticket, "Parking ticket");
        ParkingUtils.requirePositive(ammount, "Ammount");
        ParkingUtils.requireValidPaymentTime(ticket.getEntryTime(), paymentTime);
    }
}
