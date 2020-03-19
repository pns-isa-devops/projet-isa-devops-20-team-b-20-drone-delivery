package fr.polytech.business;

import arquillian.AbstractScheduleTest;
import entities.TimeSlot;
import entities.TimeState;
import fr.polytech.components.DeliveryOrganizer;
import fr.polytech.components.DeliveryScheduler;
import fr.polytech.components.ScheduleBean;
import fr.polytech.entities.Delivery;
import gherkin.lexer.Da;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
public class ScheduleTest extends AbstractScheduleTest {

    @EJB
    private DeliveryScheduler deliveryScheduler;

    @EJB
    private DeliveryOrganizer deliveryOrganizer;

    @Inject
    private ScheduleBean schedule;

    @Test //Fonctionnel
    public void scheduleDeliveryTest() {

    }

    /**
     * Tests DateIsAvalable
     */
    @Test
    public void dateIsAvailableTestWithNothing() {
        assertTrue(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

    @Test
    public void dateIsAvailableTestWithOneDeliveryBefore() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), new Delivery());
        assertTrue(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

    @Test
    public void dateIsAvailableTestWithOneDeliveryAtSameTime() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), new Delivery());
        assertFalse(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

    @Test
    public void dateIsAvailableTestWithOneTimeSlotBefore() {
        schedule.createTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), TimeState.CHARGING);
        assertTrue(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

    @Test
    public void dateIsAvailableTestWithOneTimeSlotAtSameTime() {
        schedule.createTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), TimeState.CHARGING);
        assertFalse(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

    /**
     * Tests getTimeSlotsWithOnlyDeliveries
     */
    @Test
    public void getTimeSlotsWithOnlyDeliveriesEmpty() {
        assertEquals(0, schedule.getTimeSlotsWithOnlyDeliveries().size());
    }

    @Test
    public void getTimeSlotsWithOnlyDeliveriesWithDeliveries() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), new Delivery());
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), new Delivery());
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 30), new Delivery());

        assertEquals(3, schedule.getTimeSlotsWithOnlyDeliveries().size());
    }

    @Test
    public void getNextDeliveriesTest(){

        assertNull(deliveryOrganizer.getNextDelivery());
        assertTrue(deliveryScheduler.scheduleDelivery(new Date(),new Delivery()));
        assertNull(deliveryOrganizer.getNextDelivery());

    }

    @Test
    public void getTimeSlotsWithOnlyDeliveriesWithDeliveryAndOther() {
        schedule.createTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), TimeState.REVIEW);
        schedule.createTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 30), TimeState.CHARGING);
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), new Delivery());

        assertEquals(1, schedule.getTimeSlotsWithOnlyDeliveries().size());
    }

    /**
     * Tests getTimeSlotsWithOnlyDeliveries
     */
    @Test
    public void setChargingTimeSlotsTestWithOneDeliveries() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(1, ts.size());
        schedule.setChargingTimeSlots(ts);
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertEquals(1, ts.size());
    }

    @Test
    public void setChargingTimeSlotsTestWithTwoDeliveries1() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), new Delivery());
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 15), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        assertEquals(3, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 30)));
        assertTrue(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 45)));
    }

    @Test
    public void setChargingTimeSlotsTestWithTwoDeliveries2() {
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 0), new Delivery());
        schedule.createDeliveryTimeSlot(new Date(2001, Calendar.JANUARY, 2, 8, 30), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        assertEquals(3, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 45)));
        assertTrue(schedule.dateIsAvailable(new Date(2001, Calendar.JANUARY, 2, 8, 15)));
    }

}
