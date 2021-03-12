package co.edu.escuelaing.loadbalancer;

import static spark.Spark.*;

/**
 * Servicio web que consulta y envia cadenas al servicio LogService alojado en 
 * una máquina virtual de AWS
 * @author Federico Barrios Meneses
 */
public class SparkWebServer {

    /**
     * Método principal que ejecuta el servicio web que realiza peticiones 
     * get y post.
     * @param args 
     */
    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/static");
        LoadBalancerClient cliente = new LoadBalancerClient();
        get("/get", (req, res) -> {
            res.status(200);
            String url= "http://172.17.0.1:";
            cliente.changePort();
            return cliente.getResponse(url);
        });
        
        post("/post", (req, res) -> {
            String url= "http://172.17.0.1:";
            cliente.postResponse(url, req.body());
            cliente.changePort();
            return "";
        });
    }

    /**
     * Retorna el puerto por defecto del entorno, si no está definido retorna 
     * 4567
     * @return 4567 si no hay puerto definido
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
