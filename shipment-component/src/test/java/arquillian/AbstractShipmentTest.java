package arquillian;

import fr.polytech.components.DeliveryInitializer;
import fr.polytech.entities.Delivery;
import fr.polytech.entities.DeliveryStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * AbstractTCFTest
 */
public class AbstractShipmentTest {

    @Deployment
    public static WebArchive createDeployment() {
        // @formatter:off
        return ShrinkWrap.create(WebArchive.class)
                // Entities
                .addPackage(Delivery.class.getPackage())
                .addPackage(DeliveryStatus.class.getPackage())
                // Components and Interfaces
                .addPackage(DeliveryInitializer.class.getPackage());
    }
}
