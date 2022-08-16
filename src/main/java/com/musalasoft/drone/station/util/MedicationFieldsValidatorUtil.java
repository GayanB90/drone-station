package com.musalasoft.drone.station.util;

import java.util.regex.Pattern;

public class MedicationFieldsValidatorUtil {
    private static final Pattern medicationNamePattern = Pattern.compile("^(\\w|_)+");
    private static final Pattern medicationCodePattern = Pattern.compile("^([A-Z]|[0-9]|_)+");

    /**
     * This method validates a suggested name string for a medication.
     * allowed only letters, numbers, ‘-‘, ‘_’
     * @param name
     * @return
     */
    public static boolean validateMedicationName(String name) {
        return medicationNamePattern.matcher(name).matches();
    }

    /**
     * This method validates a suggested code string for a medication.
     * allowed only upper case letters, underscore and numbers
     * @param code
     * @return
     */
    public static boolean validateMedicationCode(String code) {
        return medicationCodePattern.matcher(code).matches();
    }
}
