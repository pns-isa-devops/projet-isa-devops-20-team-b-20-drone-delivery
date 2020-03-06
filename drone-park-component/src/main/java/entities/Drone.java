package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


public class Drone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int droneId;

    @NotNull
    private DroneStatus droneStatus;

    public Drone(){
        // Default : the drone newly created is available
        // Necessary for JPA instantiation process
        this.droneStatus = DroneStatus.AVAILABLE;
    }

    public int getDroneId() {
        return droneId;
    }

    public DroneStatus getDroneStatus() {
        return droneStatus;
    }

    public void setDroneStatus(DroneStatus droneStatus) {
        this.droneStatus = droneStatus;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;

        if (obj != null)
            return (obj.hashCode() == Objects.hash(this.droneId, this.droneStatus));

        return false;
    }


}
