package fr.polytech.components;

import fr.polytech.entities.Delivery;

import java.util.List;

public interface DeliveryOrganizer {
    List<Delivery> getNextDeliveries();

}
