package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deliveryId;

    @NotNull
    private DeliveryStatus deliveryStatus;

    private Drone drone;

//    private Parcel parcel; todo implement

    /**
     *
     * @param drone to be assigned to the delivery
     */
    public Delivery(Drone drone){
        this.deliveryStatus = DeliveryStatus.NOT_DELIVERED;
        this.drone = drone;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
