public class Drone implements DroneLauncher {

    private String droneId;
    private DroneStatus droneStatus;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the right time.
     *
     * @param drone
     * @param arrivalHour
     * @return
     */
    @Override
    public boolean initializeDroneLaunching(Drone drone, double arrivalHour) {
        throw new UnsupportedOperationException();
    }
}
