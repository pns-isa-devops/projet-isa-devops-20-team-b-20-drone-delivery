import entities.Drone;
import entities.DroneStatus;
import utils.DroneAPI;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DroneParkBean implements DroneLauncher {


    private DroneAPI droneAPI;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the right time.
     *
     * @param drone
     * @param arrivalHour
     * @return
     */
    @Override
    public boolean initializeDroneLaunching(Drone drone, double arrivalHour) {
        // Call the dotnet API
        this.droneAPI.launchDrone(drone);
        drone.setDroneStatus(DroneStatus.ON_DELIVERY);
        entityManager.persist(drone);

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
