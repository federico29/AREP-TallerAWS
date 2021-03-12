package co.edu.escuelaing.loadbalancer;

import static spark.Spark.*;

public class SparkWebServer {

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

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
