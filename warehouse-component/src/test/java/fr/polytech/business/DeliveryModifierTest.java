package fr.polytech.business;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractTCFTest;
import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;
import fr.polytech.exception.UnknownDeliveryException;

/**
 * WarehouseTest
 */
@RunWith(Arquillian.class)
public class DeliveryModifierTest extends AbstractTCFTest {

    @EJB
    private DeliveryModifier deliveryModifier;

    @Test
    public void findDeliveryTest() throws UnknownDeliveryException {
        Delivery d = deliveryModifier.findDelivery("del106");
        assertEquals(15.60, d.getPriceToCharge(), 0.1);
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
