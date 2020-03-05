import entities.Drone;

public interface DroneLauncher {

    /**
     * Initializes drone launching by sending the launch signal to the drone at the right time.
     * @param drone
     * @param arrivalHour
     * @return
     */
    boolean initializeDroneLaunching(Drone drone/*, double arrivalHour*/); // no arrival hour for now

}
