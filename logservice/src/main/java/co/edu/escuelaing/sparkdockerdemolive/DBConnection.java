package co.edu.escuelaing.sparkdockerdemolive;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.json.JSONObject;
import org.bson.Document;

/**
 *
 * @author Federico Barrios Meneses
 */
public class DBConnection {

    private static MongoClient mongoClient;
    private static DB dataBase;
    private static DBCollection test;

    public DBConnection() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://172.17.0.1:27017"));
        dataBase = mongoClient.getDB("cadenas");
        test = dataBase.getCollection("valores");
    }

    public List<DBObject> getMessage() {
        List<DBObject> data = test.find().toArray();
        System.out.println(data.size());
        if (data.size() <= 10) {
            return data;
        } else {
            return data.subList(Math.max(data.size() - 10, 0), data.size());
        }
    }

    public void addMessage(String source) {
        JSONObject message = new JSONObject(source);
        MongoDatabase dataBase2 = mongoClient.getDatabase("cadenas");
        Document document = new Document();
        document.append("valor", message.get("valor"));
        dataBase2.getCollection("valores").insertOne(document);
    }
}
