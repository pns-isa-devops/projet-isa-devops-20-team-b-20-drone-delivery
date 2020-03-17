package fr.polytech.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import fr.polytech.entities.Parcel;
import fr.polytech.utils.CarrierAPI;

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
        List<Parcel> parcels = new ArrayList<>();
        Parcel p = new Parcel();
        p.setAddress("2255 route des Dolines, 06560 Valbonne");
        p.setCarrier("JPP");
        p.setCustomerName("Jasmine");
        p.setParcelNumber("jpp106");
        parcels.add(p);
        p = new Parcel();
        p.setAddress("5522 avenue des Moutons, 06560 Valbonne");
        p.setCarrier("JPP2");
        p.setCustomerName("Arnold");
        p.setParcelNumber("jpp206");
        parcels.add(p);
        List<Delivery> deliveries = new ArrayList<>();
        Delivery d = new Delivery();
        d.setDeliveryNumber("del106");
        d.setParcel(parcels.get(0));
        d.setPriceToCharge(15.60);
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        deliveries.add(d);
        d = new Delivery();
        d.setDeliveryNumber("del206");
        d.setPriceToCharge(10.60);
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        deliveries.add(d);
        carrierApi = new CarrierAPI().withMockedDeliveries(deliveries).withMockedParcels(parcels);
    }

}
