package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deliveryId;

    @NotNull
    private DeliveryStatus deliveryStatus;

    private Drone drone;

    private Parcel parcel;

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

    @Override
    public String toString() {
        return "Delivery " + this.deliveryId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Delivery) {
            Delivery d = (Delivery) obj;
            return this.deliveryId == d.deliveryId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryId, this.deliveryStatus, this.drone, this.parcel);
    }

}
