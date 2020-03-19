package arquillian;

import entities.Drone;
import entities.TimeSlot;
import entities.TimeState;
import fr.polytech.components.DeliveryOrganizer;
import fr.polytech.components.DeliveryScheduler;
import fr.polytech.entities.Delivery;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * AbstractSheduleTest
 */
public class AbstractScheduleTest {

    @Deployment
    public static WebArchive createDeployment() {
        // @formatter:off
        return ShrinkWrap.create(WebArchive.class)
                // Entities
                .addPackage(TimeSlot.class.getPackage())
                .addPackage(TimeState.class.getPackage())
                .addPackage(Drone.class.getPackage())
                .addPackage(Delivery.class.getPackage())
                // Utils
                    // Empty
                // Components and Interfaces
                .addPackage(DeliveryScheduler.class.getPackage())
                .addPackage(DeliveryOrganizer.class.getPackage());


        // @formatter:on
    }
}
