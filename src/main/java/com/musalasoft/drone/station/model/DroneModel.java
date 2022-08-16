package com.musalasoft.drone.station.model;

public enum DroneModel {

    LightWeight {
        @Override
        public double getCapacityInKilos() {
            return 0.5;
        }
    }, MiddleWeight {
        @Override
        public double getCapacityInKilos() {
            return 1;
        }
    }, CruiserWeight {
        @Override
        public double getCapacityInKilos() {
            return 2;
        }
    }, HeavyWeight {
        @Override
        public double getCapacityInKilos() {
            return 5;
        }
    };

    public abstract double getCapacityInKilos();
}
