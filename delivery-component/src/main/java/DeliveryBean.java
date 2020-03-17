import javax.ejb.EJB;
import javax.ejb.Stateless;

import entities.Delivery;
import entities.DeliveryStatus;

@Stateless
public class DeliveryBean implements DeliveryInitializer {

    @EJB
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
        delivery.setDeliveryStatus(DeliveryStatus.ONGOING);

        return droneLauncher.initializeDroneLaunching(delivery.getDrone());

    }

}
