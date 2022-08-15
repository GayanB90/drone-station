package com.musalasoft.drone.station.model;

public enum DroneRequestStatus {
    SUCCESS {
        @Override
        public String getReason() {
            return "SUCCESS";
        }
    }, FAILURE {
        @Override
        public String getReason() {
            return "Load failed due to an error at the drone station!";
        }
    }, INVALID_REQUEST {
        @Override
        public String getReason() {
            return "Request rejected due to being invalid!";
        }
    }, DRONE_CAPACITY_EXCEEDED {
        @Override
        public String getReason() {
            return "Drone load rejected due to payload weight exceeding drone capacity!";
        }
    }, DRONE_BATTERY_INSUFFICIENT {
        @Override
        public String getReason() {
            return "Request rejected due to drone battery level being insufficient!";
        }
    };

    public abstract String getReason();
}
