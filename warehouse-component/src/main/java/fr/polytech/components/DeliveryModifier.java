package fr.polytech.components;

import javax.ejb.Local;

import fr.polytech.entities.Delivery;
import fr.polytech.exception.UnknownDeliveryException;
import fr.polytech.exception.UnknownParcelException;

@Local
public interface DeliveryModifier {

    /**
     * Retrieve a parcel information from his ID This method also store the parcel
     * in his state
     *
     * @param id
     * @return the scanned parcel in a new delivery
     * @throws UnknownParcelException if the parcel those not existe in the database
     *                                of the carrier
     */
    Delivery scanParcel(String parcelNumber) throws UnknownParcelException;

    /**
     * Find a specific delivery by his id
     *
     * @param id
     * @return the delivery if exist or null
     */
    Delivery findDelivery(String deliveryNumber) throws UnknownDeliveryException;
}
