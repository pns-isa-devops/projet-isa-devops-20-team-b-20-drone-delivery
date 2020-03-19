package utils;

import entities.Drone;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;

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

    public void launchDrone(Drone drone) {

        if (!getDroneStatus()) {
            return;
        }

        // Build request
        JSONObject request = new JSONObject().put("DroneId", drone.getDroneId());

        // Launch
        try {
            String str = WebClient.create(url).path("/drone/launch")
                    .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
                    .post(request.toString(), String.class);
        } catch (Exception e) {
            System.out.println("post request failed on /drone/launch : " + e);
        }
    }
}
