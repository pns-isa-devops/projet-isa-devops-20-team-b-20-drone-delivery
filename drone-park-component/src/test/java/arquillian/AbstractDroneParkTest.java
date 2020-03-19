package arquillian;

import entities.Drone;
import entities.DroneStatus;
import fr.polytech.components.DroneLauncher;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import utils.DroneAPI;

/**
 * AbstractTCFTest
 */
public class AbstractDroneParkTest {

    @Deployment
    public static WebArchive createDeployment() {
        // @formatter:off
        return ShrinkWrap.create(WebArchive.class)
                // Entities
                .addPackage(Drone.class.getPackage())
                .addPackage(DroneStatus.class.getPackage())
                // Utils
                .addPackage(DroneAPI.class.getPackage())
                // Components and Interfaces
                .addPackage(DroneLauncher.class.getPackage());


        // @formatter:on
    }
}
