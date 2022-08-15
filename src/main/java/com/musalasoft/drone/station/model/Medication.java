package com.musalasoft.drone.station.model;

import com.musalasoft.drone.station.util.MedicationFieldsValidatorUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Medication {
    private String name;
    private double weight;

    @Id
    private String code;
    private String image;

    public Medication() {
    }

    public Medication(String name, double weight, String code, String image) {
        if (Objects.isNull(name) || MedicationFieldsValidatorUtil.validateMedicationName(name)) {
            throw new RuntimeException("Invalid medication name provided, " + name);
        }
        if (Objects.isNull(code) || MedicationFieldsValidatorUtil.validateMedicationCode(code)) {
            throw new RuntimeException("Invalid medication code provided, " + code);
        }
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Double.compare(that.weight, weight) == 0 && name.equals(that.name) && code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, code);
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
