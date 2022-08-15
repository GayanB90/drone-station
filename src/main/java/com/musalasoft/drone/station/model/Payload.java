package com.musalasoft.drone.station.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "current_payload")
public class Payload {

    @Id
    @Column(name = "payload_id")
    private String id;

    @OneToOne
    @JoinColumn(name = "drone_serial_no")
    private Drone drone;

    @Transient
    private List<Medication> medications;

    public Payload() {
    }

    public Payload(String id) {
        this.id = id;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public String getId() {
        return id;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public double calculateWeight() {
        if (Objects.isNull(medications)) {
            return 0;
        }
        return medications.stream().map(Medication::getWeight).reduce((double) 0, Double::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payload payload = (Payload) o;
        return Objects.equals(id, payload.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id='" + id + '\'' +
                ", medications=" + medications +
                '}';
    }
}
