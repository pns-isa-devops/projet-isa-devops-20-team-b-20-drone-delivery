public class Delivery implements DroneLauncher {

    private String deliveryId;
    private DeliveryStatus deliveryStatus;
    private Parcel parcel;

    /**
     * Initializes drone launching by sending the launch signal to the drone at the right time.
     *
     * @param drone
     * @param arrivalHour
     * @return
     */
    @Override
    public boolean initializeDroneLaunching(Drone drone, double arrivalHour) {
        return false;
    }
}
