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

import java.util.Date;

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
        droneLauncher.initializeDroneLaunching(droneTest, new Date());
        assertEquals(DroneStatus.ON_DELIVERY, droneTest.getDroneStatus());
    }

    @Test
    public void initializeDroneLaunchingOtherDateTest() {
        Drone droneTest = new Drone();
        assertEquals(DroneStatus.AVAILABLE, droneTest.getDroneStatus());
        droneLauncher.initializeDroneLaunching(droneTest, new Date(2020, 3, 19, 18, 37));
        assertEquals(DroneStatus.ON_DELIVERY, droneTest.getDroneStatus());
    }
}
