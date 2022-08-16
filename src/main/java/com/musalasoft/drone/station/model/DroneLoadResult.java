package com.musalasoft.drone.station.model;

public class DroneLoadResult {
    private final String droneSerialId;
    private final String payloadId;
    private final PayloadLoadState state;

    public DroneLoadResult(String droneSerialId, String payloadId, PayloadLoadState state) {
        this.droneSerialId = droneSerialId;
        this.payloadId = payloadId;
        this.state = state;
    }

    public String getDroneSerialId() {
        return droneSerialId;
    }

    public String getPayloadId() {
        return payloadId;
    }

    public PayloadLoadState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "DroneLoadResult{" +
                "droneSerialId='" + droneSerialId + '\'' +
                ", payloadId='" + payloadId + '\'' +
                ", state=" + state +
                '}';
    }

    public static enum PayloadLoadState {
        SUCCESSFULLY_LOADED, WEIGHT_OVERLOAD, NO_AVAILABLE_DRONES
    }
}
