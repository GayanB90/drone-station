package com.musalasoft.drone.station.model;

public class LoadDroneResponse {
    private String droneSerialNo;
    private DroneRequestStatus status;

    public LoadDroneResponse(String droneSerialNo, DroneRequestStatus status) {
        this.droneSerialNo = droneSerialNo;
        this.status = status;
    }

    public String getDroneSerialNo() {
        return droneSerialNo;
    }

    public DroneRequestStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LoadDroneResponse{" +
                "droneSerialNo='" + droneSerialNo + '\'' +
                ", status=" + status +
                '}';
    }
}
