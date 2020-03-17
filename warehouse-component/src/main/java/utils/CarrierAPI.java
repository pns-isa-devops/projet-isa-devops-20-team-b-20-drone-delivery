package utils;

import java.util.ArrayList;
import java.util.List;

import entities.Delivery;
import entities.DeliveryStatus;
import entities.Parcel;

/**
 * CarrierAPI
 */
public class CarrierAPI {

    private String url;
    private List<Parcel> parcels;
    private List<Delivery> deliveries;

    /**
     * not to be used for now (mocked value)
     *
     * @param host
     * @param port
     */
    public CarrierAPI(String host, String port) {
        this.url = "http://" + host + ":" + port;
        parcels = new ArrayList<>();
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
        deliveries = new ArrayList<>();
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
    }

    /**
     * prove of concept (mocked value)
     */
    public CarrierAPI() {
        this("localhost", "9090");
    }

    public Parcel getParcelInformation(String parcelNumber) {
        // just to test
        // @formatter:off
        return parcels.stream()
                    .filter(p -> p.getParcelNumber().equals(parcelNumber))
                    .findAny()
                    .orElse(null);
        // @formatter:on
    }

    public Delivery getDeliveryInformation(String deliveryNumber) {
        // just to test
        // @formatter:off
        return deliveries.stream()
                    .filter(d -> d.getDeliveryNumber().equals(deliveryNumber))
                    .findAny()
                    .orElse(null);
        // @formatter:on
    }
}
