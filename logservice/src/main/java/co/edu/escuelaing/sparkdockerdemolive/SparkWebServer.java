package co.edu.escuelaing.sparkdockerdemolive;

import org.json.JSONObject;
import static spark.Spark.*;

public class SparkWebServer {
    public static void main(String[] args) {
        port(getPort());

        DBConnection db = new DBConnection();
        
        get("/hello", (req, res) -> "Hello docker");
        
        get("/", (req, res) -> {
            JSONObject json = new JSONObject();
            json.put("cadenas", db.getMessage());
            return json;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
