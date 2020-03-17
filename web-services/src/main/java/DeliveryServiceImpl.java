import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import entities.Drone;
import fr.polytech.entities.Delivery;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryWS")
public class DeliveryServiceImpl implements DeliveryService {

    @EJB(name = "stateless-deliveryInitializer")
    private DeliveryInitializer deliveryInitializer;

    @Override
    public void startDelivery(String deliveryId) {
        deliveryInitializer.initializeDelivery(new Delivery(new Drone())); // todo
    }

}
