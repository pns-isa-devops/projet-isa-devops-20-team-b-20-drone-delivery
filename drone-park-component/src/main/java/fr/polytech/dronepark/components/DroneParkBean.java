package fr.polytech.dronepark.components;

import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import fr.polytech.dronepark.utils.DroneAPI;
import fr.polytech.entities.Drone;
import fr.polytech.entities.DroneStatus;

@Stateless
public class DroneParkBean implements DroneLauncher {

    private DroneAPI droneAPI;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time.
     *
     * @param drone
     * @return
     */
    @Override
    public boolean initializeDroneLaunching(final Drone drone, final GregorianCalendar launchHour) {
        // Call the dotnet API
        this.droneAPI.launchDrone(drone, launchHour);
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
