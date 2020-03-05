import entities.Delivery;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryWS")
public class DeliveryServiceImpl implements DeliveryService {

    @EJB(name = "stateless-cart")
    private DeliveryInitializer deliveryInitializer;

    @Override
    public void startDelivery(String deliveryId) {
        //deliveryInitializer.initializeDelivery(new Delivery()); //todo
    }



}
