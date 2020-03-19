import fr.polytech.entities.Delivery;

import java.util.List;

public interface DeliveryOrganizer {
    public List<Delivery> getNextDeliveries();

}
