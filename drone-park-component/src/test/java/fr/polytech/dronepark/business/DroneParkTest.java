package fr.polytech.dronepark.business;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractDroneParkTest;
import fr.polytech.dronepark.components.DroneLauncher;
import fr.polytech.entities.Drone;
import fr.polytech.entities.DroneStatus;

/**
 * DronePark
 */
@RunWith(Arquillian.class)
public class DroneParkTest extends AbstractDroneParkTest {

    @EJB
    private DroneLauncher droneLauncher;

    @Test
    public void initializeDroneLaunchingTest() {
        Drone droneTest = new Drone();
        assertEquals(DroneStatus.AVAILABLE, droneTest.getDroneStatus());
        droneLauncher.initializeDroneLaunching(droneTest, new GregorianCalendar());
        assertEquals(DroneStatus.ON_DELIVERY, droneTest.getDroneStatus());
    }

    @Test
    public void initializeDroneLaunchingOtherDateTest() {
        Drone droneTest = new Drone();
        assertEquals(DroneStatus.AVAILABLE, droneTest.getDroneStatus());
        droneLauncher.initializeDroneLaunching(droneTest, new GregorianCalendar(2020, 3, 19, 18, 37));
        assertEquals(DroneStatus.ON_DELIVERY, droneTest.getDroneStatus());
    }
}
