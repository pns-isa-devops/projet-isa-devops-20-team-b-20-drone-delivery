package entities;

import fr.polytech.entities.Delivery;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TimeSlot implements Comparable {
    @NotNull
    private Date date;

    @NotNull
    private TimeState state;

    private Delivery delivery;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeState getState() {
        return state;
    }

    public void setState(TimeState state) {
        this.state = state;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public int compareTo(Object o) {
        return date.compareTo(((TimeSlot) o).getDate());
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TimeSlot))
            return false;

        if (obj == this)
            return true;

        return date.equals(((TimeSlot) obj).getDate())
                && delivery.equals(((TimeSlot) obj).getDelivery())
                && state.equals(((TimeSlot) obj).getState());

    }
}
