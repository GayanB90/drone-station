package com.musalasoft.drone.station.controller.impl;

import com.musalasoft.drone.station.controller.DroneController;
import com.musalasoft.drone.station.model.*;
import com.musalasoft.drone.station.repository.DroneRepository;
import com.musalasoft.drone.station.repository.MedicationRepository;
import com.musalasoft.drone.station.repository.PayloadRepository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Objects;

import static com.musalasoft.drone.station.model.Drone.MINIMUM_BATTERY_LEVEL_FOR_LOAD;


@RestController
public class DroneControllerImpl implements DroneController {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final PayloadRepository payloadRepository;

    public DroneControllerImpl(DroneRepository droneRepository, MedicationRepository medicationRepository,
                               PayloadRepository payloadRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.payloadRepository = payloadRepository;
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
        Drone drone = droneRepository.findBySerialNo(loadDroneRequest.getDroneSerialNo());
        if (!DroneState.IDLE.equals(drone.getState())) {
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.INVALID_DRONE_STATE);
        }
        if (droneCapacityExceeded(loadDroneRequest, drone)) {
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.DRONE_CAPACITY_EXCEEDED);
        }
        if (drone.getBatteryLevel() < MINIMUM_BATTERY_LEVEL_FOR_LOAD) {
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.DRONE_BATTERY_INSUFFICIENT);
        }
        payloadRepository.save(loadDroneRequest.getPayload());
        medicationRepository.saveAll(loadDroneRequest.getPayload().getMedications());
        return new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.SUCCESS);
    }

    private boolean droneCapacityExceeded(LoadDroneRequest loadDroneRequest, Drone drone) {
        return loadDroneRequest.getPayload().calculateWeight() > drone.getModel().getCapacityInKilos();
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

    @Override
    public DroneBatteryLevelResp checkDroneBatteryLevel(String droneSerialNo) {
        Drone drone = droneRepository.findBySerialNo(droneSerialNo);
        return new DroneBatteryLevelResp(droneSerialNo, drone.getBatteryLevel(),
                new Timestamp(System.currentTimeMillis()));
    }
}
