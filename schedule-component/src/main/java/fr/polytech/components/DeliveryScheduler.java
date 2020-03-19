package fr.polytech.components;

import fr.polytech.entities.Delivery;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface DeliveryScheduler {
    /**
     * ask and update <class>Schedule</class> if it can assign a new delivery to
     * schedule
     *
     * @param date     of delivery
     * @param delivery id
     * @return if scheduling a delivery to this hour is possible
     */
    boolean scheduleDelivery(Date date, Delivery delivery);
}
