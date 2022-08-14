package com.musalasoft.drone.station.util;

import java.util.regex.Pattern;

public class MedicationFieldsValidatorUtil {
    private static final Pattern medicationNamePattern = Pattern.compile("^(\\w|_)+");
    private static final Pattern medicationCodePattern = Pattern.compile("^([A-Z]|[0-9]|_)+");

    public static boolean validateMedicationName(String name) {
        return medicationNamePattern.matcher(name).matches();
    }

    public static boolean validateMedicationCode(String code) {
        return medicationCodePattern.matcher(code).matches();
    }
}
