import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;

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
    public boolean initializeDelivery(final Delivery delivery) {
        delivery.setStatus(DeliveryStatus.ONGOING);

        return droneLauncher.initializeDroneLaunching(delivery.getDrone());

    }

}
