package fr.polytech.components;

import java.util.List;

import javax.ejb.Local;

import fr.polytech.entities.Parcel;
import fr.polytech.utils.CarrierAPI;

/**
 * ControlledParcel
 */
@Local
public interface ControlledParcel extends DeliveryModifier {

    void useCarrierReference(CarrierAPI carrier);

    void useCarrierReference(CarrierAPI carrier, List<Parcel> parcels);
}
