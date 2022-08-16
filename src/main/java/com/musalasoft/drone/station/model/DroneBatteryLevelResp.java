package com.musalasoft.drone.station.model;

import java.sql.Timestamp;

public class DroneBatteryLevelResp {
    private final String droneSerialNo;
    private final double batteryLevel;
    private final Timestamp timestamp;

    public DroneBatteryLevelResp(String droneSerialNo, double batteryLevel, Timestamp timestamp) {
        this.droneSerialNo = droneSerialNo;
        this.batteryLevel = batteryLevel;
        this.timestamp = timestamp;
    }

    public String getDroneSerialNo() {
        return droneSerialNo;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "DroneBatteryLevelResp{" +
                "droneSerialNo='" + droneSerialNo + '\'' +
                ", batteryLevel=" + batteryLevel +
                ", timestamp=" + timestamp +
                '}';
    }
}
