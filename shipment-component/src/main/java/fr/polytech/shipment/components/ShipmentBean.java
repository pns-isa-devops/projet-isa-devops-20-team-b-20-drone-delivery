package fr.polytech.shipment.components;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.polytech.dronepark.components.DroneLauncher;
import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import fr.polytech.entities.TimeSlot;

@Stateless
public class ShipmentBean implements DeliveryInitializer {

    @EJB
    private DroneLauncher droneLauncher;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the
     * right time and by updating drone and delivery status.
     *
     * @param delivery
     * @return
     */
    @Override
    public boolean initializeDelivery(Delivery delivery) {
        delivery.setStatus(DeliveryStatus.ONGOING);
        TimeSlot timeSlot = delivery.getDrone().getTimeSlot(delivery);
        return droneLauncher.initializeDroneLaunching(delivery.getDrone(), timeSlot.getDate());
    }

}
