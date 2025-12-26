package ymcris.rogex.h.utilities.validations;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Validator class is the class responsible for be the class called to know
 * if data is in the correct type
 *
 * @author YmCris
 * @since Dec 11, 2025
 */
public class Validator {

    // SPECIFIC METHODS --------------------------------------------------------
    /**
     * Method responsible for check if the string is a local date time
     *
     * @param string string to check
     * @return true if it's a local date time
     */
    public boolean isLocalDateTime(String string) {
        try {
            LocalDateTime.parse(string, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while parsing a LocalDateTime because " + e.getMessage());
        }
        return false;
    }

    /**
     * Method responsible for check if the string is a local date
     *
     * @param string string to check
     * @return true if it's a local date
     */
    public boolean isLocalDate(String string) {
        try {
            LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("An exception of type " + e.getClass().getName()
                    + " occurred while parsing a LocalDateTime because " + e.getMessage());
        }
        return false;
    }

    /**
     * Method responsible for check if the string is an integer
     *
     * @param string string to check
     * @return true if it's an integer
     */
    public boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method responsible for check if the string is a boolean
     *
     * @param string string to check
     * @return true if it's a boolean
     */
    public boolean isBoolean(String string) {
        return "true".equalsIgnoreCase(string)
                || "false".equalsIgnoreCase(string);
    }

    /**
     * Method responsible for check if the string is a double
     *
     * @param string string to check
     * @return true if it's a double
     */
    public boolean isDouble(String string) {
        try {
            Double.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * To verify if the string is an email
     *
     * @param string
     * @return
     */
    public boolean isEmail(String string) {
        char[] characters = string.toCharArray();
        String[] terminals = {".gt", ".com", ".edu", ".gob"};
        boolean hasArroba = false;
        boolean hasTerminal = false;
        StringBuilder builder = new StringBuilder();

        for (char character : characters) {
            builder.append(character);
            if (character == '@') {
                hasArroba = true;
            }
        }

        for (String terminal : terminals) {
            if (builder.toString().endsWith(terminal)) {
                hasTerminal = true;
                break;
            }
        }

        return hasArroba && hasTerminal;
    }

    /**
     * Method responsible for check if the string is a file
     *
     * @param string string to check
     * @return true if it's a file
     */
    public boolean isFile(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        File file = new File(string);
        return file.exists() && file.isFile();
    }

    public boolean isValidDate(LocalDate date) {
        return !date.isBefore(LocalDate.now().minusYears(90))
                && !date.isAfter(LocalDate.now());
    }

    public boolean isValidLocalDateTime(LocalDateTime date) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(LocalDateTime.now().minusYears(90))
                && !date.isAfter(LocalDateTime.now());
    }
}
