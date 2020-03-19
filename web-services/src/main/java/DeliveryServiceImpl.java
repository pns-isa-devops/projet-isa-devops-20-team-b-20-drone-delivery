import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import entities.Drone;
import fr.polytech.components.DeliveryModifier;
import fr.polytech.components.DeliveryScheduler;
import fr.polytech.entities.Delivery;

import java.text.SimpleDateFormat;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryWS")
public class DeliveryServiceImpl implements DeliveryService {

    @EJB(name = "stateless-deliveryInitializer")
    private DeliveryInitializer deliveryInitializer;

    @EJB(name = "stateless-deliveryScheduler")
    private DeliveryScheduler deliveryScheduler;

    @EJB
    private DeliveryModifier deliveryModifier;

    @Override
    /**
     * Get the delivery corresponding to deliveryId from the deliveryModifier component and
     * start the shipment with it
     */
    public void startDelivery(String deliveryId) throws Exception {
        Delivery deliveryFromWharehouse = deliveryModifier.findDelivery(deliveryId);

        // If the delivery doesn't have a drone associated there is a problem
        if(deliveryFromWharehouse.getDrone() == null){
            throw new Exception("There is no drone on this delivery");
        }
        deliveryInitializer.initializeDelivery(deliveryFromWharehouse);
    }

    public void scheduleDelivery(String deliveryId, String date) throws Exception {
        Delivery delivery = deliveryModifier.findDelivery(deliveryId);

        if (delivery == null) {
            throw new Exception("Wrong delivery id");
        }

        deliveryScheduler.scheduleDelivery(new SimpleDateFormat("HH:mm").parse(date), delivery);
    }

}
