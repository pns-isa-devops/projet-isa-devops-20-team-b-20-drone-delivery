import entities.Delivery;
import entities.DeliveryStatus;
import entities.Drone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DeliveryBean implements DeliveryInitializer {

    // @PersistenceContext
    // private EntityManager entityManager;

    private DroneLauncher droneLauncher;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time and by updating drone and delivery status.
     *
     * @param delivery
     * @return
     */
    @Override
    public boolean initializeDelivery(Delivery delivery) {
        // entityManager.find(Drone.class, "0"); // todo the drone should have an ID of
        // 0
        delivery.setDeliveryStatus(DeliveryStatus.ONGOING);
        return droneLauncher.initializeDroneLaunching(delivery.getDrone());
    }
}
