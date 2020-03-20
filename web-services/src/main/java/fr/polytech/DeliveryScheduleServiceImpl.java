package fr.polytech;

import fr.polytech.components.DeliveryModifier;
import fr.polytech.components.DeliveryScheduler;
import fr.polytech.entities.Delivery;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.text.SimpleDateFormat;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryScheduleWS")
public class DeliveryScheduleServiceImpl  {

    @EJB(name = "stateless-deliveryScheduler")
    private DeliveryScheduler deliveryScheduler;

    @EJB
    private DeliveryModifier deliveryModifier;

    public void scheduleDelivery(String deliveryId, String date) throws Exception {
        Delivery delivery = deliveryModifier.findDelivery(deliveryId);

        if (delivery == null) {
            throw new Exception("Wrong delivery id");
        }

        deliveryScheduler.scheduleDelivery(new SimpleDateFormat("HH:mm").parse(date), delivery);
    }
}
