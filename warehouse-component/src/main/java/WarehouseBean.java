import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import entities.Delivery;
import entities.Parcel;
import utils.CarrierAPI;

/**
 * WarehousBean
 */
@Stateful
public class WarehouseBean implements DeliveryModifier {

    CarrierAPI carrierApi;

    @Override
    public Parcel scanParcel(String id) {
        return carrierApi.getParcelInformation(id);
    }

    @Override
    public Delivery findDelivery(String id) {
        return carrierApi.getDeliveryInformation(id);
    }

    @PostConstruct
    /**
     * Init the Carrier API on localhost
     */
    private void initializeRestPartnership() {
        carrierApi = new CarrierAPI();
    }

}
