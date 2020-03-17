package arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import fr.polytech.components.DeliveryModifier;
import fr.polytech.entities.Delivery;
import fr.polytech.utils.CarrierAPI;

/**
 * AbstractTCFTest
 */
public class AbstractTCFTest {

    @Deployment
    public static WebArchive createDeployment() {
        // @formatter:off
        return ShrinkWrap.create(WebArchive.class)
                // Entities
                .addPackage(Delivery.class.getPackage())
                // Utils
                .addPackage(CarrierAPI.class.getPackage())
                // Components and Interfaces
                .addPackage(DeliveryModifier.class.getPackage());
        // @formatter:on
    }
}
