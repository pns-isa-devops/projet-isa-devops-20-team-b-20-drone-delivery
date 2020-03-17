package utils;

import entities.Delivery;
import entities.DeliveryStatus;
import entities.Parcel;

/**
 * CarrierAPI
 */
public class CarrierAPI {

    private String url;

    /**
     * not to be used for now (mocked value)
     *
     * @param host
     * @param port
     */
    public CarrierAPI(String host, String port) {
        this.url = "http://" + host + ":" + port;
    }

    /**
     * prove of concept (mocked value)
     */
    public CarrierAPI() {
        this("localhost", "9090");
    }

    public Parcel getParcelInformation(String parcelNumber) {
        // just to test
        Parcel p = new Parcel();
        p.setAddress("address");
        p.setCarrier("carrier");
        p.setCustomerName("customerName");
        p.setParcelNumber("parcelNumber");
        return p;
    }

    public Delivery getDeliveryInformation(String deliveryNumber) {
        // just to test
        Delivery d = new Delivery();
        d.setDeliveryNumber("deliveryNumber");
        d.setPriceToCharge(10.2);
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        return d;
    }
}
