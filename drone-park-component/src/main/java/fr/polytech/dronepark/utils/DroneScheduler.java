package fr.polytech.dronepark.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import fr.polytech.entities.Drone;

/**
 * DroneReturnChecker
 */
@Singleton
@Lock(LockType.WRITE)
public class DroneScheduler {
    private static final Logger log = Logger.getLogger(DroneScheduler.class.getName());

    // private DroneAPI droneAPI;
    private Set<Drone> drones = new HashSet<>();

    public DroneScheduler() {
    }

    public void add(Drone d) {
        this.drones.add(d);
        log.log(Level.INFO, "Drone [" + d.getDroneId() + "] in Delivery");
    }

    @Schedule(hour = "*", minute = "*", second = "1")
    public void processReturn() {

    }
}
