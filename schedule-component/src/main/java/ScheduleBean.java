import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;

import javax.ejb.EJB;
import java.util.List;

public class ScheduleBean implements DeliveryOrganizer {

    @EJB
    private DeliveryModifier deliveryModifier;

    @Override
    public List<Delivery> getNextDeliveries() {
        // TODO : collect the 5 last deliveries and return them, but HOW ?
        return null;
    }
}
