package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Drone{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int droneId;

    @NotNull
    private DroneStatus droneStatus;

    Drone(){
        // Default : the drone newly created is available
        // Necessary for JPA instantiation process
        this.droneStatus = DroneStatus.AVAILABLE;
    }

    Drone(DroneStatus droneStatus){
        this.droneStatus = droneStatus;
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


}
