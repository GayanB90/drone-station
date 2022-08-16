package com.musalasoft.drone.station.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "current_payload")
public class Payload {

    @Id
    @Column(name = "payload_id")
    private String id;

    @Column(name = "received_time")
    private Timestamp receivedTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payload_id")
    private List<Medication> medications;

    public Payload() {
    }

    public Payload(String id) {
        this.id = id;
    }

    public List<Medication> getMedications() {
        if (Objects.isNull(medications)) {
            return Collections.emptyList();
        }
        return medications;
    }

    public String getId() {
        return id;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Timestamp getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Timestamp receivedTime) {
        this.receivedTime = receivedTime;
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
                ", receivedTime=" + receivedTime +
                ", medications=" + medications +
                '}';
    }
}
