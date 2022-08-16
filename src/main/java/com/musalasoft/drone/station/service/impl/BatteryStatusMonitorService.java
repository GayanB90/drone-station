package com.musalasoft.drone.station.service.impl;

import com.musalasoft.drone.station.model.Drone;
import com.musalasoft.drone.station.repository.DroneRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BatteryStatusMonitorService {

    private final DroneRepository droneRepository;

    private final ScheduledExecutorService executorService;

    public BatteryStatusMonitorService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @PostConstruct
    public void startBatteryStatusMonitoringService() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    String batteryStatus = extractDroneBatteryStatusString();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }, 0, 60000, TimeUnit.MILLISECONDS);
    }

    private String extractDroneBatteryStatusString() {
        Iterable<Drone> drones = droneRepository.findAll();
        return StreamSupport.stream(drones.spliterator(), false)
                .map(d -> new BatteryStatus(d.getSerialNo(), d.getBatteryLevel(),
                        new Timestamp(System.currentTimeMillis())))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    @PreDestroy
    public void shutdownBatteryMonitorService() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    private static class BatteryStatus {
        private String droneSerialNo;
        private double batteryStatus;
        private Timestamp timestamp;

        public BatteryStatus(String droneSerialNo, double batteryStatus, Timestamp timestamp) {
            this.droneSerialNo = droneSerialNo;
            this.batteryStatus = batteryStatus;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return droneSerialNo + "|" + batteryStatus + "|" + timestamp + "|";
        }
    }
}
