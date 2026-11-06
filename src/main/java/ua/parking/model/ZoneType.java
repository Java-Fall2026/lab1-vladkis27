package ua.parking.model;

public enum ZoneType {
    VIP(2.0), UNDERGROUND(1.5), OVERGROUND(1.0);
    private final double coef;
    ZoneType(double coef) {
        this.coef = coef;
    }
    public double getCoef() {
        return coef;
    }
}
