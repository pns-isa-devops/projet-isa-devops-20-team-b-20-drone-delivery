package fr.polytech.components;

import fr.polytech.entities.Delivery;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DeliveryOrganizer {
    Delivery getNextDelivery();

}
