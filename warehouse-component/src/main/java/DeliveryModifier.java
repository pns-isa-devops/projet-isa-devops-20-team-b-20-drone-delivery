import javax.ejb.Local;

import entities.Delivery;
import entities.Parcel;

@Local
public interface DeliveryModifier {

    /**
     * Retrieve a parcel information from his ID This method also store the parcel
     * in his state
     *
     * @param id
     * @return the scanned parcel or null if it has not been found
     */
    Parcel scanParcel(String parcelNumber);

    /**
     * Find a specific delivery by his id
     *
     * @param id
     * @return the delivery if exist or null
     */
    Delivery findDelivery(String deliveryNumber);
}
