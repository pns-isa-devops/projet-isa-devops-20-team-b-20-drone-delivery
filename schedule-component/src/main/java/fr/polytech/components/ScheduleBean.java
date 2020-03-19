package fr.polytech.components;

import entities.Drone;
import entities.TimeSlot;
import entities.TimeState;
import fr.polytech.entities.Delivery;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.*;
import java.util.stream.Collectors;

@Stateful
public class ScheduleBean implements DeliveryOrganizer, DeliveryScheduler {

    @EJB
    private DeliveryModifier deliveryModifier;

    private Drone drone;
    private final static int DURING_15_MIN = 15*60*1000;

    @Override
    public List<Delivery> getNextDeliveries() {
        // TODO : collect the 5 last deliveries and return them, but HOW ?
        return null;
    }

    @Override
    public boolean scheduleDelivery(Date date, Delivery delivery)
    {

        // Stage 1 : Check that the asked timeslot is available
        for(TimeSlot ts : drone.getTimeSlots())
        {
            if(ts.getDate().equals(date)) return false;
        }

        // Stage 2 : Set the timeslot

        TimeSlot timeslot = new TimeSlot();
        timeslot.setDelivery(delivery);
        timeslot.setDate(date);
        timeslot.setState(TimeState.DELIVERY);
        drone.getTimeSlots().add(timeslot);

        // Stage 3 : Remove CHARGING and UNAVAILABLE slots in order to obtain only DELIVERY timeslots

        Set<TimeSlot> timeslots = new TreeSet<>(drone.getTimeSlots().stream().filter(ts -> ts.getState().equals(TimeState.DELIVERY)).collect(Collectors.toSet())) ;

        // Stage 3.1 : Set back the CHARGING time slots
        setChargingTimeSlots(timeslots);
        // Stage 3.2 : Set back UNAVAILABLE time slots
        setUnavailableTimeSlots(timeslots);

        return true;
    }

    /**
     * Take a set of time slot and add CHARGING time slots where the drone needs charge
     * @param timeslots
     */
    private void setChargingTimeSlots(Set<TimeSlot> timeslots){

        int count = 0;
        for(Iterator<TimeSlot> it = timeslots.iterator(); it.hasNext();) {
            TimeSlot ts =  it.next();
            if(ts.getState() == TimeState.DELIVERY) {
                count++;
                if(count%2 == 0) {
                    TimeSlot chargingTs = new TimeSlot();
                    chargingTs.setDate(new Date(ts.getDate().getTime() + DURING_15_MIN));
                    chargingTs.setState(TimeState.CHARGING);
                }
            }
        }
    }

    /**
     * Take a set of time slot and add UNAVAILABLE time slots where it's impossible to schedule a delivery
     * @param timeslots
     */
    private void setUnavailableTimeSlots(Set<TimeSlot> timeslots){
        Iterator<TimeSlot> it = timeslots.iterator();
        if(!it.hasNext()){
            return;
        }
        TimeSlot first = it.next();

        while(it.hasNext()) {
            TimeSlot next;
            do {
                next = it.next();
            } while (next.getState() != TimeState.DELIVERY);

            if (next.getDate().getTime() - first.getDate().getTime() < 2 * DURING_15_MIN) {
                TimeSlot ts = new TimeSlot();
                ts.setState(TimeState.UNAVAILABLE);
                ts.setDate(new Date(first.getDate().getTime() - DURING_15_MIN));
                timeslots.add(ts);
            } else if (next.getDate().getTime() - first.getDate().getTime() < 3 * DURING_15_MIN) {
                TimeSlot ts = new TimeSlot();
                ts.setState(TimeState.UNAVAILABLE);
                ts.setDate(new Date(first.getDate().getTime() + DURING_15_MIN));
                timeslots.add(ts);
            }
            first = next;
        }
    }

}
