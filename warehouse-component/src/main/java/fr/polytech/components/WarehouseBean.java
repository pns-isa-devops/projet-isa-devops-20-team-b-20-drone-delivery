package fr.polytech.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import fr.polytech.entities.Parcel;
import fr.polytech.exception.UncheckedException;
import fr.polytech.exception.UnknownDeliveryException;
import fr.polytech.exception.UnknownParcelException;
import fr.polytech.utils.CarrierAPI;

/**
 * WarehousBean
 */
@Stateful
public class WarehouseBean implements DeliveryModifier, ControlledParcel {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private CarrierAPI carrier;
    private List<Delivery> deliveries = new ArrayList<>();

    @Override
    public void useCarrierReference(CarrierAPI carrier) {
        this.carrier = carrier;
    }

    @Override
    public void useCarrierReference(CarrierAPI carrier, List<Parcel> parcels) {
        this.carrier = carrier.withControlledParcels(parcels);
    }

    @Override
    public Delivery scanParcel(String id) throws UnknownParcelException {
        Parcel p = carrier.getParcelInformation(id);
        if (p == null) {
            throw new UnknownParcelException(id);
        }
        Delivery d = new Delivery();
        d.setParcel(p);
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        d.setDeliveryNumber(UUID.randomUUID().toString());
        deliveries.add(d);
        return d;
    }

    @Override
    public Delivery findDelivery(String id) throws UnknownDeliveryException {
        Delivery d = getDeliveryInformation(id);
        if (d == null) {
            throw new UnknownDeliveryException(id);
        }
        return d;
    }

    @PostConstruct
    /**
     * Init the Carrier API on localhost
     */
    private void initializeRestPartnership() {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/carrier.properties"));
            carrier = new CarrierAPI(prop.getProperty("carrierHostName"), prop.getProperty("carrierPortNumber"));
            addMock();
        } catch (Exception e) {
            log.log(Level.INFO, "Cannot read bank.properties file", e);
            throw new UncheckedException(e);
        }
    }

    private void addMock() {
        List<Parcel> parcels = new ArrayList<>();
        Parcel p = new Parcel();
        p.setAddress("2255 route des Dolines, 06560 Valbonne");
        p.setCarrier("JPP");
        p.setCustomerName("Jasmine");
        p.setParcelNumber("c1");
        parcels.add(p);
        p = new Parcel();
        p.setAddress("2255 route des Dolines, 06560 Valbonne");
        p.setCarrier("JPP");
        p.setCustomerName("Alexis");
        p.setParcelNumber("c2");
        parcels.add(p);
        p = new Parcel();
        p.setAddress("2255 route des Dolines, 06560 Valbonne");
        p.setCarrier("JPP");
        p.setCustomerName("Betsara");
        p.setParcelNumber("c3");
        parcels.add(p);
        p = new Parcel();
        p.setAddress("2255 route des Dolines, 06560 Valbonne");
        p.setCarrier("JPP");
        p.setCustomerName("Arnold");
        p.setParcelNumber("c4");
        parcels.add(p);
        this.carrier.withControlledParcels(parcels);
        Delivery d = new Delivery();
        d.setDeliveryNumber("del106");
        d.setParcel(parcels.get(0));
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        deliveries.add(d);
        d = new Delivery();
        d.setDeliveryNumber("del206");
        d.setStatus(DeliveryStatus.NOT_DELIVERED);
        deliveries.add(d);
    }

    private Delivery getDeliveryInformation(String deliveryNumber) {
        // just to test
        // @formatter:off
        return deliveries.stream()
                    .filter(d -> d.getDeliveryNumber().equals(deliveryNumber))
                    .findAny()
                    .orElse(null);
        // @formatter:on
    }

}
