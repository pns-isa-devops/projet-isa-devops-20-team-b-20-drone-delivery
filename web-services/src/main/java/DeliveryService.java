import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/dronedelivery/delivery")
public interface DeliveryService {

    @WebMethod
    void startDelivery(
            @WebParam(name = "delivery_id") String deliveryId
    ) throws Exception;

    @WebMethod
    void scheduleDelivery(
            @WebParam(name = "date") String date,
            @WebParam(name = "delivery_id") String deliveryId
    ) throws Exception;

}
