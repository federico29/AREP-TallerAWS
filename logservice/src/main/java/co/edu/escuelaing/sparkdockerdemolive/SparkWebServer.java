package co.edu.escuelaing.sparkdockerdemolive;

import org.json.JSONObject;
import static spark.Spark.*;

/**
 * Servicio web que se comunica con la base de datos
 * @author Federico Barrios Meneses
 */
public class SparkWebServer {
    
    /**
     * Método principal que ejecuta el servicio web que realiza peticiones
     * get y post. Consulta y crea documentos dentro de una colección en
     * una base de datos Mongodb
     * @param args 
     */
    public static void main(String[] args) {
        port(getPort());

        DBConnection db = new DBConnection();

        get("/hello", (req, res) -> "Hello docker");

        get("/", (req, res) -> {
            res.status(200);
            JSONObject json = new JSONObject();
            json.put("cadenas", db.getMessage());
            return json;
        });

        post("/", (req, res) -> {
            res.status(200);
            res.type("application/json");
            db.addMessage(req.body());
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
