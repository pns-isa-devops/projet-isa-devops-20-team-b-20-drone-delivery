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
import java.util.GregorianCalendar;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ScheduleTest extends AbstractScheduleTest {

    @EJB(name = "schedule")
    private DeliveryScheduler deliveryScheduler;

    @EJB(name = "schedule")
    private DeliveryOrganizer deliveryOrganizer;

    @Inject
    private ScheduleBean schedule;

    @Test // Fonctionnel
    public void scheduleDeliveryTestWithNothing() {
        Delivery delivery = new Delivery();
        schedule.scheduleDelivery(new Date(new Date().getTime() + 1000), delivery);
        Delivery next = schedule.getNextDelivery();
        assertEquals(delivery, next);
    }

    /**
     * Tests DateIsAvailable
     */
    @Test
    public void dateIsAvailableTestWithNothing() {
        assertTrue(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
    }

    @Test
    public void dateIsAvailableTestWithOneDeliveryBefore() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), new Delivery());
        assertTrue(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
    }

    @Test
    public void dateIsAvailableTestWithOneDeliveryAtSameTime() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
    }

    @Test
    public void dateIsAvailableTestWithOneTimeSlotBefore() {
        schedule.createTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), TimeState.CHARGING);
        assertTrue(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
    }

    @Test
    public void dateIsAvailableTestWithOneTimeSlotAtSameTime() {
        schedule.createTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), TimeState.CHARGING);
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
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
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime(), new Delivery());

        assertEquals(3, schedule.getTimeSlotsWithOnlyDeliveries().size());
    }

    @Test
    public void getNextDeliveriesTest() {

        assertNull(deliveryOrganizer.getNextDelivery());
        assertTrue(deliveryScheduler.scheduleDelivery(new Date(), new Delivery()));
        assertNull(deliveryOrganizer.getNextDelivery());

    }

    @Test
    public void getTimeSlotsWithOnlyDeliveriesWithDeliveryAndOther() {
        schedule.createTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), TimeState.REVIEW);
        schedule.createTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime(), TimeState.CHARGING);
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());

        assertEquals(1, schedule.getTimeSlotsWithOnlyDeliveries().size());
    }

    /**
     * Tests getTimeSlotsWithOnlyDeliveries
     */
    @Test
    public void setChargingTimeSlotsTestWithOneDeliveries() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(1, ts.size());
        schedule.setChargingTimeSlots(ts);
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertEquals(1, ts.size());
    }

    @Test
    public void setChargingTimeSlotsTestWithTwoDeliveries1() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        assertEquals(3, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime()));
        assertTrue(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 45).getTime()));
    }

    @Test
    public void setChargingTimeSlotsTestWithTwoDeliveries2() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime(), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        assertEquals(3, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 45).getTime()));
        assertTrue(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime()));
    }

    /**
     * Tests getTimeSlotsWithOnlyDeliveries
     */
    @Test
    public void setUnavailableTimeSlotsTestsWithTwoDeliveries1() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime(), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        schedule.setUnavailableTimeSlots(ts);
        assertEquals(4, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 45).getTime()));
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 0).getTime()));

    }

    @Test
    public void setUnavailableTimeSlotsTestsWithTwoDeliveries2() {
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 15).getTime(), new Delivery());
        schedule.createDeliveryTimeSlot(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 45).getTime(), new Delivery());
        Set<TimeSlot> ts = schedule.getTimeSlotsWithOnlyDeliveries();
        assertEquals(2, ts.size());
        schedule.setChargingTimeSlots(ts);
        schedule.setUnavailableTimeSlots(ts);
        assertEquals(4, ts.size());
        schedule.setNewSchedule(schedule.getDrone(), ts);
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 8, 30).getTime()));
        assertFalse(schedule.dateIsAvailable(new GregorianCalendar(2001, Calendar.JANUARY, 2, 9, 0).getTime()));

    }

}
