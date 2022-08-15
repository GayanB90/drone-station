package com.musalasoft.drone.station.controller.impl;

import com.musalasoft.drone.station.controller.DroneController;
import com.musalasoft.drone.station.model.*;
import com.musalasoft.drone.station.repository.DroneRepository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Objects;


@RestController
public class DroneControllerImpl implements DroneController {
    private final DroneRepository droneRepository;

    public DroneControllerImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneRegisterResponse registerDrone(Drone drone) {
        try {
            initializePayloadTimestamp(drone);
            droneRepository.save(drone);
            return new DroneRegisterResponse(DroneRequestStatus.SUCCESS, drone.getSerialNo());
        } catch (Exception e) {
            e.printStackTrace();
            return new DroneRegisterResponse(DroneRequestStatus.FAILURE, drone.getSerialNo());
        }
    }

    private void initializePayloadTimestamp(Drone drone) {
        if (Objects.nonNull(drone.getPayload()) && Objects.isNull(drone.getPayload().getReceivedTime())) {
            drone.getPayload().setReceivedTime(new Timestamp(System.currentTimeMillis()));
        }
    }

    @Override
    public LoadDroneResponse loadPayloadToDrone(LoadDroneRequest loadDroneRequest) {
        return null;
    }

    @Override
    public Iterable<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public Payload getPayloadContents(String droneSerialNo) {
        Drone drone = droneRepository.findBySerialNo(droneSerialNo);
        if (Objects.isNull(drone)) {
            return new Payload();
        }
        return drone.getPayload();
    }

    @Override
    public Iterable<Drone> getAllAvailableDrones() {
        return droneRepository.findAllAvailable();
    }
}
