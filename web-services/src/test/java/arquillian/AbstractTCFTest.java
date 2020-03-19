package arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import fr.polytech.DeliveryServiceImpl;
import fr.polytech.interceptors.LoggerParam;

/**
 * AbstractTCFTest
 */
public class AbstractTCFTest {

    // @Deployment
    // public static WebArchive createDeployment() {

    //     // @formatter:off
    //     return ShrinkWrap.create(WebArchive.class)
    //             // Interceptors
    //             .addPackage(LoggerParam.class.getPackage())
    //             // Components and Interfaces
    //             .addPackage(DeliveryServiceImpl.class.getPackage())
    //             // libraries
    //             .addAsLibraries(Maven.resolver()
    //                         .loadPomFromFile("pom.xml")
    //                         .importRuntimeDependencies()
    //                         .resolve()
    //                         .withTransitivity()
    //                         .asFile());
    //     // @formatter:on
    // }
}
