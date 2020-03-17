import entities.Drone;

import javax.ejb.Local;

@Local
public interface DeliveryModifier {

    Parcel scanParcel(String id);
}
