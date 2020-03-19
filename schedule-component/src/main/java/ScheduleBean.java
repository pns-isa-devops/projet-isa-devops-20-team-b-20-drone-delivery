import entities.Drone;
import entities.TimeSlot;
import entities.TimeState;
import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateful
public class ScheduleBean implements DeliveryOrganizer, DeliveryScheduler {

    @EJB
    private DeliveryModifier deliveryModifier;

    private Drone drone;

    @Override
    public List<Delivery> getNextDeliveries() {
        // TODO : collect the 5 last deliveries and return them, but HOW ?
        return null;
    }

    @Override
    public boolean scheduleDelivery(Date date, Delivery delivery)
    {
//        for(TimeSlot ts : drone.getTimeSlots())
//        {
//            if(ts.getDate().equals(date)) return false;
//        }
//
//        TimeSlot timeslot = new TimeSlot();
//        timeslot.setDelivery(delivery);
//        timeslot.setDate(date);
//        timeslot.setState(TimeState.DELIVERY);
//        drone.getTimeSlots().add(timeslot);
//
//        List<TimeSlot> slots = new ArrayList<>(drone.getTimeSlots());
//        if(slots.get(slots.indexOf(timeslot) - 1).getState() == TimeState.DELIVERY)
//        {
//            TimeSlot timecharge = new TimeSlot();
//            timecharge.setDate(new Date(date.getTime() + DURING_15_MIN));
//            timecharge.setState(TimeState.CHARGING);
//            drone.getTimeSlots().add(timecharge);
//        }

        return true;
    }
}
