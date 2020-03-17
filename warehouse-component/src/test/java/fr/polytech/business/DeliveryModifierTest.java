package fr.polytech.business;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import arquillian.AbstractTCFTest;
import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;

/**
 * WarehouseTest
 */
@RunWith(Arquillian.class)
public class DeliveryModifierTest extends AbstractTCFTest {

    @EJB
    private DeliveryModifier deliveryModifier;

    @Test
    public void findDeliveryTest() {
        Delivery d = deliveryModifier.findDelivery("del106");
        assertEquals(15.60, d.getPriceToCharge(), 0.1);
    }
}
