package com.musalasoft.drone.station.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "drone")
public class Drone {
    public static final int MINIMUM_BATTERY_LEVEL_FOR_LOAD = 25;

    @Id
    private String serialNo;
    private  DroneModel model;
    private DroneState state;
    private double batteryLevel;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "drone_payload", joinColumns = @JoinColumn(name = "drone"),
            inverseJoinColumns = @JoinColumn(name = "payload_id"))
    private Payload payload;

    public Drone() {
    }

    public Drone(String serialNo, DroneModel model) {
        if (Objects.isNull(serialNo) || serialNo.isEmpty() || serialNo.length() > 100) {
            throw new RuntimeException("Invalid drone serial number received, " + serialNo);
        }
        if (Objects.isNull(model)) {
            throw new RuntimeException("Drone model cannot be null!");
        }
        this.serialNo = serialNo;
        this.model = model;
        this.state = DroneState.IDLE;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }


    public String getSerialNo() {
        return serialNo;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        if (( batteryLevel < 0 || batteryLevel > 100 )) {
            throw new RuntimeException("Invalid battery level, cannot be less than zero or greater than 100"
                    + batteryLevel);
        }
        this.batteryLevel = batteryLevel;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public Payload getPayload() {
        return payload;
    }

    public synchronized void setPayload(Payload payload) {
        double payloadWeight = payload.calculateWeight();
        if ( payloadWeight > model.getCapacityInKilos()) {
            throw new RuntimeException("Drone capacity inadequate to carry the payload!, payload = " + payloadWeight +
                    " drone capacity=" + model.getCapacityInKilos());
        }
        if (batteryLevel < MINIMUM_BATTERY_LEVEL_FOR_LOAD) {
            throw new RuntimeException("Drone battery level below minimum threshold to carry load!, battery level = "
                    + batteryLevel);
        }
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return serialNo.equals(drone.serialNo) && model == drone.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo, model);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "serialNo='" + serialNo + '\'' +
                ", model=" + model +
                ", batteryLevel=" + batteryLevel +
                ", state=" + state +
                ", payload=" + payload +
                '}';
    }
}
