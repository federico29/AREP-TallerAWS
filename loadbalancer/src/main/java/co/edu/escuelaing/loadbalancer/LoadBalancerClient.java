package co.edu.escuelaing.loadbalancer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Federico Barrios Meneses
 */
public class LoadBalancerClient {

    private String[] ports = {"35001", "35002", "35003"};
    private int currentPort = 0;

    public String getResponse(String url) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.get(url + ports[currentPort]).asString();
        System.out.println("Get request taken by port: " + ports[currentPort]);
        return apiResponse.getBody();
    }

    public void changePort() {
        currentPort = (currentPort + 1) % ports.length;
    }
}
