package com.musalasoft.drone.station.model;

public class LoadDroneRequest {
    private final String droneSerialNo;
    private final Payload payload;

    public LoadDroneRequest(String droneSerialNo, Payload payload) {
        this.droneSerialNo = droneSerialNo;
        this.payload = payload;
    }

    public String getDroneSerialNo() {
        return droneSerialNo;
    }

    public Payload getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "LoadDroneRequest{" +
                "droneSerialNo='" + droneSerialNo + '\'' +
                ", payload=" + payload +
                '}';
    }
}
