package fr.polytech.dronepark.components;

import java.util.GregorianCalendar;

import javax.ejb.Local;

import fr.polytech.entities.Drone;

@Local
public interface DroneLauncher {

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time.
     *
     * @param drone
     * @return
     */
    boolean initializeDroneLaunching(Drone drone, GregorianCalendar launchHour);

}
