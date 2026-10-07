package ua.parking.util;

class ValidationHelper {

    static void requireNotNull(Object obj, String fieldName) {
        if(obj == null) {
            throw new IllegalArgumentException(fieldName + " must not be null, got: null");
        }
    }
    static void requireNotBlank(String value, String fieldName) {
        if(value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank, got: " + value);
        }
    }
    static void requirePositive(double value, String fieldName) {
        if(value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be over 0, got: " + value);
        }
    }
}