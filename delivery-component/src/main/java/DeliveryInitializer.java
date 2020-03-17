import javax.ejb.Local;

import entities.Delivery;

@Local
public interface DeliveryInitializer {

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time and by updating drone and delivery status.
     *
     * @param delivery
     * @return
     */
    boolean initializeDelivery(Delivery delivery);
}
