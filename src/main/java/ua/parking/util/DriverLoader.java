package ua.parking.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.parking.exception.InvalidDataException;
import ua.parking.model.Driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class DriverLoader {
    private static final Logger log = LoggerFactory.getLogger(DriverLoader.class);

    public static List<Driver> loadDrivers(String filePath) {
        List<Driver> drivers = new ArrayList<>();
        log.info("Reading file via FileReader: {}", filePath);
        try(FileReader reader = new FileReader(filePath)) {
            StringBuilder lineBuilder = new StringBuilder();
            int lineNumber = 1;
            boolean isFirstLine = true;
            int character;
            while((character = reader.read())  != -1) {
                char ch = (char) character;
                if(ch == '\n') {
                    String line = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    if(isFirstLine) {
                        isFirstLine = false;
                    } else {
                        lineNumber++;
                        processLine(line, lineNumber, drivers);
                    }
                } else if(ch != '\r') {
                    lineBuilder.append(ch);
                }
            }
            if(lineBuilder.length() > 0 && !isFirstLine) {
                lineNumber++;
                processLine(lineBuilder.toString(), lineNumber, drivers);
            }
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", filePath);
        } catch(IOException e) {
            log.error("Error reading the file : {}", filePath);
        } finally {
            log.info("Successfully loaded {} drivers", drivers.size());
        }
        return drivers;
    }
    private static void processLine(String line, int lineNumber, List<Driver> drivers) {
        try {
            Driver driver = parseLine(line, lineNumber);
            drivers.add(driver);
        } catch (InvalidDataException e) {
            log.warn("Skipped wrong line: {}", e.getMessage());
        }
    }
    public static Driver parseLine(String line, int lineNumber) throws InvalidDataException {
        try {
            String[] parts = line.split(",", -1);
            return new Driver(parts[0], parts[1], parts[2]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDataException("Row " + lineNumber + " has an error: " + line, e);
        }
    }
}