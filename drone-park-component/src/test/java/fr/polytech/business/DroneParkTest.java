package fr.polytech.business;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractDroneParkTest;
import entities.Drone;
import entities.DroneStatus;
import fr.polytech.components.DroneLauncher;

/**
 * DronePark
 */
@RunWith(Arquillian.class)
public class DroneParkTest extends AbstractDroneParkTest {

    @EJB
    private DroneLauncher droneLaucnher;

    @Test
    public void initializeDroneLaunchingTest() {
        Drone droneTest = new Drone();
        assertEquals(DroneStatus.AVAILABLE, droneTest.getDroneStatus());
        droneLaucnher.initializeDroneLaunching(droneTest);
        assertEquals(DroneStatus.ON_DELIVERY, droneTest.getDroneStatus());
    }
}
