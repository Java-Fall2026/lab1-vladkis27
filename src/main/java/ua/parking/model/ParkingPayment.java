package ua.parking.model;
import java.time.LocalDateTime;
import java.util.Objects;
import ua.common.BaseEntity;
import ua.parking.util.ParkingUtils;
public class ParkingPayment extends BaseEntity {
    private String paymentId;
    private ParkingTicket ticket;
    private double ammount;
    private LocalDateTime paymentTime;

    private ParkingPayment(String paymentId, ParkingTicket ticket, double ammount, LocalDateTime paymentTime) {
        super();
        this.paymentId = ParkingUtils.requireNotBlank(paymentId, "Payment ID");
        ParkingUtils.requireNotNull(ticket, "Parking ticket");
        this.ticket = ticket;
        this.ammount = ParkingUtils.requirePositive(ammount, "price");
        ParkingUtils.requireValidPaymentTime(ticket.getEntryTime(), paymentTime);
        this.paymentTime = paymentTime;
    }
    public static ParkingPayment of(String paymentId, ParkingTicket ticket, double ammount, LocalDateTime paymentTime) {
        return new ParkingPayment(paymentId, ticket, ammount, paymentTime);
    }

    public String getPaymentId() { return paymentId; }
    public ParkingTicket getTicket() { return ticket; }
    public double getAmmount() { return ammount; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) { return false; }
        ParkingPayment payment = (ParkingPayment) o;
        return Objects.equals(paymentId, payment.paymentId);
    }
    public int hashCode() {
        return Objects.hash(paymentId);
    }
    public String toString() {
        return "ParkingPayment{" + paymentId + "}, car number=" + ticket.getVehiclePlate() + ", price=" + ammount + ", payment time: " + paymentTime + ". Created at: " + createdAt;
    }
}
