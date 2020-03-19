package fr.polytech.components;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import entities.Drone;
import entities.DroneStatus;
import utils.DroneAPI;

@Stateless
public class DroneParkBean implements DroneLauncher {

    private DroneAPI droneAPI;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time.
     *
     * @param drone
     * @param arrivalHour
     * @return
     */
    @Override
    public boolean initializeDroneLaunching(Drone drone) {
        // Call the dotnet API
        this.droneAPI.launchDrone(drone);
        drone.setDroneStatus(DroneStatus.ON_DELIVERY);
        return false;
    }

    @PostConstruct
    /**
     * Init the drone API on localhost
     */
    private void initializeRestPartnership() {
        droneAPI = new DroneAPI();
    }

}
