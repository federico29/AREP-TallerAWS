package co.edu.escuelaing.loadbalancer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Cliente web que hace peticiones al servicio LogService alojado en 
 * una máquina virtual de AWS
 * @author Federico Barrios Meneses
 */
public class LoadBalancerClient {

    private String[] ports = {"35001", "35002", "35003"};
    private int currentPort = 0;

    /**
     * Pide los diez últimos registros de una base de datos a la que no se
     * conecta directamente y los retorna en un formato JSON
     * @param url url del servicio al que se va a conectar
     * @return los diez últimos registros de una base de datos
     * @throws UnirestException si la conexión no se puede realizar
     */
    public String getResponse(String url) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.get(url + ports[currentPort]).asString();
        System.out.println("Get request taken by port: " + ports[currentPort]);
        return apiResponse.getBody();
    }

    /**
     * Envía una cadena con un mensaje a otro servicio conectado a una base de
     * datos
     * @param url url del servicio al que se va a conectar
     * @param mensaje cadena con un mensaje
     * @return el cuerpo de la página que realizará la petición post
     * @throws UnirestException si la conexión no se puede realizar
     */
    public String postResponse(String url, String mensaje) throws UnirestException {
        HttpResponse<String> apiResponse = Unirest.post(url + ports[currentPort]).body(mensaje).asString();
        System.out.println("Post request taken by port: " + ports[currentPort]);
        return apiResponse.getBody();
    }

    /**
     * Utiliza el algoritmo Round Robin para que las peticiones se distribuyan
     * en cada uno de los tres puertos donde corre el servicio
     */
    public void changePort() {
        currentPort = (currentPort + 1) % ports.length;
    }
}
