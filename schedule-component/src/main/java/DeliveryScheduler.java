import fr.polytech.entities.Delivery;

import java.util.Date;

public interface DeliveryScheduler
{
    boolean scheduleDelivery(Date date, Delivery delivery);
}
