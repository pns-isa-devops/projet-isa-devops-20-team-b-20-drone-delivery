package entities;

import fr.polytech.entities.Delivery;

import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.Date;

public class TimeSlot implements Comparable
{
    @NotNull
    private Date date;

    @NotNull
    private TimeState state;

    private Delivery delivery;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public TimeState getState()
    {
        return state;
    }

    public void setState(TimeState state)
    {
        this.state = state;
    }

    public Delivery getDelivery()
    {
        return delivery;
    }

    public void setDelivery(Delivery delivery)
    {
        this.delivery = delivery;
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }
}
