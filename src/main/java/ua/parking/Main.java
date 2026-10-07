package ua.parking;

import ua.parking.model.Driver;
import ua.parking.model.ParkingPayment;
import ua.parking.model.ParkingTicket;
import ua.parking.model.Zone;
import ua.parking.util.ParkingUtils;
import java.time.LocalDateTime;
public class Main {
    public static void main(String[] args) {
        System.out.println("1.Створення об'єктів за допомогою конструкторів та фабричним методом");
        Zone zone1 = new Zone("A-01", "vip", 50.0);
        Driver driver1 = new Driver("12345", "Григорій Кисельов", "+380505132456");
        ParkingTicket ticket = ParkingTicket.of("ah7777ax", zone1, driver1, LocalDateTime.now().minusHours(2).minusMinutes(5), LocalDateTime.now());
        ParkingPayment payment = ParkingPayment.of("1234", ticket, 100, LocalDateTime.now().plusHours(3));
        System.out.println("2.Нормалізація");
        System.out.println("Введено: ah7777ax, нормалізовано у " + ticket.getVehiclePlate());
        System.out.println("Введено: vip, нормалізовано у " + zone1.getType());
        System.out.println("3. Порушення правил");
        try {
            new Zone("", "vip", 20.0);
        } catch(IllegalArgumentException e) {
            System.out.println("Помилка 1: " + e.getMessage());
        }
        try {
            new Zone("fff", "vipp", 20.0);
        } catch(IllegalArgumentException e) {
            System.out.println("Помилка 2: " + e.getMessage());
        }
        try {
            ParkingTicket.of("aa1111bb", zone1, driver1, LocalDateTime.now(), LocalDateTime.now().minusHours(1));
        } catch(IllegalArgumentException e) {
            System.out.println("Помилка 3: " + e.getMessage());
        }
        System.out.println("4. Коректний об'єкт який намагаємось зіпсувати сеттером");
        try {
            zone1.setRatePerHour(-10.0);
        } catch(IllegalArgumentException e) {
            System.out.println("Помилка сеттера: " + e.getMessage());
        }
        System.out.println("Тариф залишився: " + zone1.getRatePerHour());
        System.out.println("5. Порівняння об'єктів (==, equals, hashcode)");

        Driver driver2 = new Driver("12345", "Григорій Кисельов", "+380505132456");
        Driver driver3 = new Driver("11111", "Сергій Мазута", "+3805023156789");
        System.out.println("driver1 == driver2? : " + (driver1 == driver2));
        System.out.println("driver1 equals driver2? : " + driver1.equals(driver2));
        System.out.println("driver1 hashcode == driver2 hashcode? : " + (driver1.hashCode() == driver2.hashCode()));
        System.out.println("driver1 hashcode == driver3 hashcode? : " + (driver1.hashCode() == driver3.hashCode()));
        System.out.println("driver1 equals driver3? : " + driver1.equals(driver3));
        System.out.println("6. Виклик обчислювальних методів");
        System.out.println("Оплата за стоянку: " + ParkingUtils.cost(ticket));
        System.out.println("Машина простояла: " + ParkingUtils.parkedHours(ticket));
        System.out.println("7. Вивід tostring");
        System.out.println(driver1.toString());
        System.out.println(zone1.toString());
        System.out.println(ticket.toString());
        System.out.println(payment.toString());
        System.out.println("8. Некомпілюючийся код");
        //ua.parking.util.ValidationHelper.requireNotNull(zone, "Zone"); оскільки validationhelper package private
        //ParkingTicket badTicket = new ParkingTicket("aa1111bb", zone1, driver1, LocalDateTime.now().minusHours(3), null); оскільки можна тільки через фабричний метод
    }
}