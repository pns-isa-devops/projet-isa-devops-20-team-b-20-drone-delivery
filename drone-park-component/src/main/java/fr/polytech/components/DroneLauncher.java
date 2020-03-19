package fr.polytech.components;

import entities.Drone;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface DroneLauncher {

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time.
     *
     * @param drone
     * @return
     */
    boolean initializeDroneLaunching(Drone drone , Date launchHour); // no arrival hour for now

}
