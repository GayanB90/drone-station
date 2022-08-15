package com.musalasoft.drone.station.controller;

import com.musalasoft.drone.station.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface DroneController {
    @RequestMapping(name = "registerDrone", value = "/drone/register", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    DroneRegisterResponse registerDrone(@RequestBody Drone drone);

    @RequestMapping(name = "loadPayload", value = "/drone/load", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ResponseBody
    LoadDroneResponse loadPayloadToDrone(@RequestBody LoadDroneRequest loadDroneRequest);

    @RequestMapping(name = "getAllDrones", value = "/drone/all", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    Iterable<Drone> getAllDrones();

    @RequestMapping(name = "checkDroneCargo", value = "/drone/cargo/{droneSerialNo}", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    Payload getPayloadContents(@RequestParam String droneSerialNo);
}
