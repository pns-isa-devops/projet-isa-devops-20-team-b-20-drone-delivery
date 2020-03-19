package business;

import arquillian.AbstractDeliveryTest;
import entities.Drone;
import entities.DroneStatus;
import entities.TimeSlot;
import fr.polytech.components.DeliveryInitializer;
import fr.polytech.components.DroneLauncher;
import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.mail.search.SearchTerm;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * DronePark
 */
@RunWith(Arquillian.class)
public class DeliveryTest extends AbstractDeliveryTest {

    @EJB
    private DeliveryInitializer deliveryInitializer;

    @Test
    public void initializeDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDrone(new Drone());
        Set<TimeSlot> timeSlots = new HashSet<>();
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDate(new Date());
        timeSlot.setDelivery(delivery);
        timeSlots.add(timeSlot);
        delivery.getDrone().setTimeSlots(timeSlots);
        assertEquals(DeliveryStatus.NOT_DELIVERED, delivery.getStatus());
        deliveryInitializer.initializeDelivery(delivery);
        assertEquals(DeliveryStatus.ONGOING, delivery.getStatus());
    }

}
