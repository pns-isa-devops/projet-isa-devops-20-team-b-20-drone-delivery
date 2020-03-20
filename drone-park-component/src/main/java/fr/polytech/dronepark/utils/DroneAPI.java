package fr.polytech.dronepark.utils;

import java.util.GregorianCalendar;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import fr.polytech.entities.Drone;

public class DroneAPI {
    private String url;

    public DroneAPI(String host, String port) {
        this.url = "http://" + host + ":" + port;
    }

    public DroneAPI() {
        this("localhost", "9090");
    }

    public boolean getDroneStatus() {
        // Retrieving the drone status
        String drone = null;
        try {
            String response = WebClient.create(url).path("/status").get(String.class);
            drone = response;
        } catch (Exception e) {
            System.out.println("get request failed on /status : " + e);
        }
        // Return the drone status
        return Boolean.parseBoolean(drone);
    }

    public void launchDrone(Drone drone, GregorianCalendar launchHour) {

        if (!getDroneStatus()) {
            return;
        }

        String launchHourString = launchHour.get(GregorianCalendar.HOUR) + ":"
                + launchHour.get(GregorianCalendar.MINUTE);

        // Build request
        JSONObject request = new JSONObject().put("id", drone.getDroneId()).put("hour", launchHourString);

        // Launch
        try {
            WebClient.create(url).path("/drone/launch").accept(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", MediaType.APPLICATION_JSON).post(request.toString(), String.class);
        } catch (Exception e) {
            System.out.println("post request failed on /drone/launch : " + e);
        }
    }
}
