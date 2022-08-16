package com.musalasoft.drone.station.controller.impl;

import com.musalasoft.drone.station.model.*;
import com.musalasoft.drone.station.repository.DroneRepository;
import com.musalasoft.drone.station.repository.PayloadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

class DroneControllerImplTest {
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private PayloadRepository payloadRepository;
    private DroneControllerImpl droneController;

    private Drone drone;
    private Payload payload;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        drone = new Drone("12345", DroneModel.CruiserWeight);
        drone.setState(DroneState.IDLE);
        drone.setBatteryLevel(50);
        payload = new Payload("PLD123");
        droneController  = new DroneControllerImpl(droneRepository, payloadRepository);
    }

    @Test
    void testRegisterDrone() {
        Mockito.when(droneRepository.save(drone)).thenReturn(drone);
        DroneRegisterResponse response = droneController.registerDrone(drone);
        Mockito.verify(droneRepository, Mockito.times(1)).save(drone);
        Assertions.assertEquals(response.getDroneSerialNo(), drone.getSerialNo());
        Assertions.assertEquals(response.getStatus(), DroneRequestStatus.SUCCESS);
    }

    @Test
    void testRegisterDroneFailure() {
        Mockito.when(droneRepository.save(drone)).thenThrow(new RuntimeException());
        DroneRegisterResponse response = droneController.registerDrone(drone);
        Mockito.verify(droneRepository, Mockito.times(1)).save(drone);
        Assertions.assertEquals(response.getDroneSerialNo(), drone.getSerialNo());
        Assertions.assertEquals(response.getStatus(), DroneRequestStatus.FAILURE);
    }

    @Test
    void testLoadPayloadToDrone() {
        Mockito.when(droneRepository.findBySerialNo(Mockito.anyString())).thenReturn(drone);
        Mockito.when(droneRepository.save(drone)).thenReturn(drone);
        Mockito.when(payloadRepository.save(payload)).thenReturn(payload);
        LoadDroneResponse loadDroneResponse = droneController
                .loadPayloadToDrone(new LoadDroneRequest(drone.getSerialNo(), payload));
        Mockito.verify(droneRepository, Mockito.times(1)).findBySerialNo(Mockito.anyString());
        Mockito.verify(droneRepository, Mockito.times(1)).save(drone);
        Mockito.verify(payloadRepository, Mockito.times(1)).save(payload);
        Assertions.assertEquals(loadDroneResponse.getStatus(), DroneRequestStatus.SUCCESS);
    }

    @Test
    void testLoadPayloadToDroneInvalidState() {
        drone.setState(DroneState.DELIVERING);
        Mockito.when(droneRepository.findBySerialNo(Mockito.anyString())).thenReturn(drone);
        LoadDroneResponse loadDroneResponse = droneController
                .loadPayloadToDrone(new LoadDroneRequest(drone.getSerialNo(), payload));
        Assertions.assertEquals(loadDroneResponse.getStatus(), DroneRequestStatus.INVALID_DRONE_STATE);
    }

    @Test
    void testLoadPayloadToDroneLowBattery() {
        drone.setBatteryLevel(10);
        Mockito.when(droneRepository.findBySerialNo(Mockito.anyString())).thenReturn(drone);
        LoadDroneResponse loadDroneResponse = droneController
                .loadPayloadToDrone(new LoadDroneRequest(drone.getSerialNo(), payload));
        Assertions.assertEquals(loadDroneResponse.getStatus(), DroneRequestStatus.DRONE_BATTERY_INSUFFICIENT);
    }

    @Test
    void testLoadPayloadToDroneOverload() {
        Mockito.when(droneRepository.findBySerialNo(Mockito.anyString())).thenReturn(drone);
        payload.setMedications(Collections.singletonList(new Medication("www", 20, "2", null)));
        LoadDroneResponse loadDroneResponse = droneController
                .loadPayloadToDrone(new LoadDroneRequest(drone.getSerialNo(), payload));
        Assertions.assertEquals(loadDroneResponse.getStatus(), DroneRequestStatus.DRONE_CAPACITY_EXCEEDED);
    }
}