package ua.parking;

import ua.parking.model.Driver;
import ua.parking.util.ParkingUtils;
import ua.parking.model.ZoneType;
public class Main {
    public static void main(String[] args) {
        System.out.println("1.Створення record об'єкта та звернення до компонентів");
        Driver driver1 = new Driver("123456", "Іван Іванов", "+380501985678");
        System.out.println("Номер водія: " + driver1.phone());
        System.out.println("Ім'я водія: " + driver1.FullName());
        System.out.println("2.Робота з enum");
        System.out.println("Перебір values");
        for(ZoneType type : ZoneType.values()) {
            System.out.println(type.name() + "(coef: " + type.getCoef() + ")");
        }
        System.out.println("valueOf неправильний");
        try { 
            ZoneType invalidType = ZoneType.valueOf("wrong");
        } catch(IllegalArgumentException e) {
            System.out.println("Спіймано помилку " + e.getMessage());
        }
        System.out.println("3. Switch вирази");
        ZoneType currentZone = ZoneType.VIP;
        System.out.println("Опис зони: " + ParkingUtils.getZoneDescription(currentZone));
        System.out.println("Вартість зони: " + ParkingUtils.getZoneCoef(currentZone));
        System.out.println("4. record з невдалими даними");
        try {
            Driver badDriver = new Driver("", "Сергій Сергійович", "+380501112233");
        } catch(IllegalArgumentException e) {
            System.out.println("Помилка " + e.getMessage());
        }
        System.out.println("5. Порівняння records (equals, hashcode)");

        Driver driver2 = new Driver("12345", "Григорій Кисельов", "+380505132456");
        Driver driver3 = new Driver("12345", "Григорій Кисельов", "+380505132456");
        System.out.println("driver1 equals driver2? : " + driver2.equals(driver3));
        System.out.println("driver2 hashcode == driver2 hashcode? : " + (driver2.hashCode() == driver3.hashCode()));
        System.out.println("6. Вивід record за допомогою toString");
        System.out.println(driver2.toString());
    }
}