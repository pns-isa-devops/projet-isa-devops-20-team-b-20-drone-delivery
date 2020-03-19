package fr.polytech.business;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractTCFTest;
import fr.polytech.components.ControlledParcel;
import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import fr.polytech.entities.Parcel;
import fr.polytech.exception.UnknownDeliveryException;
import fr.polytech.exception.UnknownParcelException;
import fr.polytech.utils.CarrierAPI;

/**
 * WarehouseTest
 */
@RunWith(Arquillian.class)
// @Transactional(TransactionMode.COMMIT)
public class DeliveryModifierTest extends AbstractTCFTest {

    @EJB
    private ControlledParcel controlledParcel;

    // Test context
    private List<Parcel> parcels;
    private List<Delivery> deliveries;

    @Before
    public void setUpContext() throws Exception {
        initData();
        initMock();
    }

    private void initMock() {
        controlledParcel.useCarrierReference(new CarrierAPI(), parcels);
    }

    private void initData() {
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
        p = new Parcel();
        p.setAddress("5555 avenue des Trident, 06560 Valbonne");
        p.setCarrier("JPP3");
        p.setCustomerName("Jasmine");
        p.setParcelNumber("jpp306");
        parcels.add(p);
    }

    @Test(expected = UnknownParcelException.class)
    public void scanParcelNotFoundTest() throws Exception {
        controlledParcel.scanParcel("jpp10x");
    }

    @Test
    public void scanParcelTest() throws UnknownParcelException {
        Delivery d = controlledParcel.scanParcel("jpp106");
        assertNotNull(d);
        assertEquals(parcels.get(0), d.getParcel());
        assertEquals(DeliveryStatus.NOT_DELIVERED, d.getStatus());
        assertNotNull(d.getDeliveryNumber());

    }

    @Test(expected = UnknownDeliveryException.class)
    public void findDeliveryNotFoundTest() throws Exception {
        controlledParcel.findDelivery("unknown");
    }

    @Test
    public void findDeliveryTest() {

    }
}

// List<Parcel> parcels = new ArrayList<>();
// Parcel p = new Parcel();
// p.setAddress("2255 route des Dolines, 06560 Valbonne");
// p.setCarrier("JPP");
// p.setCustomerName("Jasmine");
// p.setParcelNumber("jpp106");
// parcels.add(p);
// p = new Parcel();
// p.setAddress("5522 avenue des Moutons, 06560 Valbonne");
// p.setCarrier("JPP2");
// p.setCustomerName("Arnold");
// p.setParcelNumber("jpp206");
// p = new Parcel();
// p.setAddress("5555 avenue des Trident, 06560 Valbonne");
// p.setCarrier("JPP3");
// p.setCustomerName("Jasmine");
// p.setParcelNumber("jpp306");
// parcels.add(p);
// List<Delivery> deliveries = new ArrayList<>();
// Delivery d = new Delivery();
// d.setDeliveryNumber("del106");
// d.setParcel(parcels.get(0));
// d.setPriceToCharge(15.60);
// d.setStatus(DeliveryStatus.NOT_DELIVERED);
// deliveries.add(d);
// d = new Delivery();
// d.setDeliveryNumber("del206");
// d.setPriceToCharge(10.60);
// d.setStatus(DeliveryStatus.NOT_DELIVERED);
// deliveries.add(d);
// carrier = new
// CarrierAPI().withMockedDeliveries(deliveries).withMockedParcels(parcels);
