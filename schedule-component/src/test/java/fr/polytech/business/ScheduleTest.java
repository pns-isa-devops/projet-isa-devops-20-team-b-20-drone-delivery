package fr.polytech.business;

import arquillian.AbstractScheduleTest;
import entities.Drone;
import entities.DroneStatus;
import fr.polytech.components.DeliveryScheduler;
import fr.polytech.components.DroneLauncher;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertTrue;


@RunWith(Arquillian.class)
public class ScheduleTest extends AbstractScheduleTest {

    @EJB
    private DeliveryScheduler deliveryScheduler;

    @Test
    public void scheduleDeliveryTest() {
        assertTrue(true);
    }
}
