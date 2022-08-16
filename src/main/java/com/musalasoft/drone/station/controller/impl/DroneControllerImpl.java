package com.musalasoft.drone.station.controller.impl;

import com.musalasoft.drone.station.controller.DroneController;
import com.musalasoft.drone.station.model.*;
import com.musalasoft.drone.station.repository.DroneRepository;
import com.musalasoft.drone.station.repository.MedicationRepository;
import com.musalasoft.drone.station.repository.PayloadRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Objects;

import static com.musalasoft.drone.station.model.Drone.MINIMUM_BATTERY_LEVEL_FOR_LOAD;


@RestController
public class DroneControllerImpl implements DroneController {
    private static final Logger logger = LogManager.getLogger(DroneControllerImpl.class);

    private final DroneRepository droneRepository;
    private final PayloadRepository payloadRepository;

    public DroneControllerImpl(DroneRepository droneRepository, MedicationRepository medicationRepository,
                               PayloadRepository payloadRepository) {
        this.droneRepository = droneRepository;
        this.payloadRepository = payloadRepository;
    }

    @Override
    public DroneRegisterResponse registerDrone(Drone drone) {
        logger.info("Request received to register a new drone, {}", drone);
        try {
            initializePayloadTimestamp(drone);
            droneRepository.save(drone);
            logger.info("Successfully registered the new drone {}", drone.getSerialNo());
            return new DroneRegisterResponse(DroneRequestStatus.SUCCESS, drone.getSerialNo());
        } catch (Exception e) {
            logger.error("Unexpected error occurred while registering the new drone, ", e);
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
        logger.info("Request received to load the payload {}", loadDroneRequest);
        Drone drone = droneRepository.findBySerialNo(loadDroneRequest.getDroneSerialNo());
        if (!DroneState.IDLE.equals(drone.getState())) {
            logger.warn("Payload load failed due to invalid drone state {} {}", drone.getSerialNo(),
                    drone.getState());
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.INVALID_DRONE_STATE);
        }
        if (droneCapacityExceeded(loadDroneRequest, drone)) {
            logger.warn("Payload load failed due to capacity exceeded {} {}", drone.getSerialNo(),
                    drone.getModel().getCapacityInKilos());
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.DRONE_CAPACITY_EXCEEDED);
        }
        if (drone.getBatteryLevel() < MINIMUM_BATTERY_LEVEL_FOR_LOAD) {
            logger.warn("Payload load failed due to low battery {} {}", drone.getSerialNo(),
                    drone.getBatteryLevel());
            new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.DRONE_BATTERY_INSUFFICIENT);
        }
        try {
            loadDroneRequest.getPayload().getMedications()
                    .forEach(m -> m.setPayloadId(loadDroneRequest.getPayload().getId()));
            payloadRepository.save(loadDroneRequest.getPayload());
            logger.info("Successfully saved payload data into the DB {}", loadDroneRequest.getPayload().getId());
            drone.setPayload(loadDroneRequest.getPayload());
            drone.setState(DroneState.LOADING);
            droneRepository.save(drone);
            logger.info("Successfully saved updated drone details to the DB {}", drone.getSerialNo());
            //drone specific communication to actually load the drone with the payload should go here
            return new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.SUCCESS);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while loading the payload", e);
            return new LoadDroneResponse(loadDroneRequest.getDroneSerialNo(), DroneRequestStatus.FAILURE);
        }
    }

    private boolean droneCapacityExceeded(LoadDroneRequest loadDroneRequest, Drone drone) {
        return loadDroneRequest.getPayload().calculateWeight() > drone.getModel().getCapacityInKilos();
    }

    @Override
    public Iterable<Drone> getAllDrones() {
        logger.info("Request received to retrieve all drone details");
        return droneRepository.findAll();
    }

    @Override
    public Payload getPayloadContents(String droneSerialNo) {
        logger.info("Request received to retrieve payload details of drone {}", droneSerialNo);
        Drone drone = droneRepository.findBySerialNo(droneSerialNo);
        if (Objects.isNull(drone)) {
            logger.info("Drone payload was empty for drone {}", droneSerialNo);
            return new Payload();
        }
        return drone.getPayload();
    }

    @Override
    public Iterable<Drone> getAllAvailableDrones() {
        logger.info("Request received to retrieve all available drone details");
        return droneRepository.findAllAvailable();
    }

    @Override
    public DroneBatteryLevelResp checkDroneBatteryLevel(String droneSerialNo) {
        logger.info("Request received to check the battery level of drone {}", droneSerialNo);
        Drone drone = droneRepository.findBySerialNo(droneSerialNo);
        logger.info("Battery level for drone {} is {}", droneSerialNo, drone.getBatteryLevel());
        return new DroneBatteryLevelResp(droneSerialNo, drone.getBatteryLevel(),
                new Timestamp(System.currentTimeMillis()));
    }
}
