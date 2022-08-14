package com.musalasoft.drone.station.model;

import java.util.Objects;

public class Drone {
    private final String serialNo;
    private final DroneModel model;
    private double batteryLevel;
    private DroneState state;

    public Drone(String serialNo, DroneModel model) {
        if (Objects.isNull(serialNo)) {
            throw new RuntimeException("Drone serial number cannot be null!");
        }
        if (Objects.isNull(model)) {
            throw new RuntimeException("Drone model cannot be null!");
        }
        this.serialNo = serialNo;
        this.model = model;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public DroneModel getModel() {
        return model;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
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
                '}';
    }
}
