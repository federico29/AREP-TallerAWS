package co.edu.escuelaing.loadbalancer;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/static");
        LoadBalancerClient cliente = new LoadBalancerClient();
        get("/consultar", (req, res) -> {
            res.status(200);
            String url= "http://3.95.0.198:";
            cliente.changePort();
            return cliente.getResponse(url);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
