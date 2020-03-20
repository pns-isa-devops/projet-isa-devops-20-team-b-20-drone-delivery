package fr.polytech.webservices;

import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import fr.polytech.entities.Delivery;
import fr.polytech.schedule.components.DeliveryScheduler;
import fr.polytech.warehouse.components.DeliveryModifier;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
@Stateless(name = "DeliveryScheduleWS")
public class DeliveryScheduleServiceImpl {

    @EJB(name = "stateless-deliveryScheduler")
    private DeliveryScheduler deliveryScheduler;

    @EJB
    private DeliveryModifier deliveryModifier;

    public void scheduleDelivery(String deliveryId, String stringDate) throws Exception {
        Delivery delivery = deliveryModifier.findDelivery(deliveryId);

        if (delivery == null) {
            throw new Exception("Wrong delivery id");
        }
        String[] time = stringDate.split(":");
        GregorianCalendar c = new GregorianCalendar();
        c.set(GregorianCalendar.HOUR, Integer.parseInt(time[0]));
        c.set(GregorianCalendar.MINUTE, Integer.parseInt(time[1]));
        deliveryScheduler.scheduleDelivery(c, delivery);
    }
}
