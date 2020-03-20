package fr.polytech.shipment.business;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractShipmentTest;
import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import fr.polytech.entities.Drone;
import fr.polytech.entities.TimeSlot;
import fr.polytech.shipment.components.DeliveryInitializer;

/**
 * DronePark
 */
@RunWith(Arquillian.class)
public class ShipmentTest extends AbstractShipmentTest {

    @EJB
    private DeliveryInitializer deliveryInitializer;

    @Test
    public void initializeDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDrone(new Drone());
        Set<TimeSlot> timeSlots = new HashSet<>();
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDate(new GregorianCalendar());
        timeSlot.setDelivery(delivery);
        timeSlots.add(timeSlot);
        delivery.getDrone().setTimeSlots(timeSlots);
        assertEquals(DeliveryStatus.NOT_DELIVERED, delivery.getStatus());
        deliveryInitializer.initializeDelivery(delivery);
        assertEquals(DeliveryStatus.ONGOING, delivery.getStatus());
    }

}
