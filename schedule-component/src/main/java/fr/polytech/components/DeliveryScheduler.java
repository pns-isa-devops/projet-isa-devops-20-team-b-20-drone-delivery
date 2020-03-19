package fr.polytech.components;

import fr.polytech.entities.Delivery;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface DeliveryScheduler
{
    boolean scheduleDelivery(Date date, Delivery delivery);
}
