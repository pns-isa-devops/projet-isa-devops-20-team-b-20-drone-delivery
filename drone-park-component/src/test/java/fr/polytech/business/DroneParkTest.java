package fr.polytech.business;

import javax.ejb.EJB;

import arquillian.AbstractDroneParkTest;
import entities.Drone;
import entities.DroneStatus;
import fr.polytech.components.DroneLauncher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


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
        assertEquals(DroneStatus.AVAILABLE,droneTest.getDroneStatus());
        droneLaucnher.initializeDroneLaunching(droneTest);
        assertEquals(DroneStatus.ON_DELIVERY,droneTest.getDroneStatus());
    }
}
