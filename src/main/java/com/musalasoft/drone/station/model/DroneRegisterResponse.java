package com.musalasoft.drone.station.model;

public class DroneRegisterResponse {
    private final DroneRequestStatus status;
    private final String droneSerialNo;

    public DroneRegisterResponse(DroneRequestStatus status, String droneSerialNo) {
        this.status = status;
        this.droneSerialNo = droneSerialNo;
    }

    public DroneRequestStatus getStatus() {
        return status;
    }

    public String getDroneSerialNo() {
        return droneSerialNo;
    }

    @Override
    public String toString() {
        return "DroneRegisterResponse{" +
                "status=" + status +
                ", droneSerialNo='" + droneSerialNo + '\'' +
                '}';
    }
}
