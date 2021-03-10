package co.edu.escuelaing.sparkdockerdemolive;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.List;


/**
 *
 * @author Federico Barrios Meneses
 */
public class DBConnection {

    private static MongoClient mongoClient;
    private static DB dataBase;
    private static DBCollection test;

    public DBConnection() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://ec2-54-198-93-83.compute-1.amazonaws.com:27017"));
        dataBase = mongoClient.getDB("cadenas");
        test = dataBase.getCollection("valores");
    }

    public List<DBObject> getMessage() {
        List<DBObject> data = test.find().toArray();
        return data;
    }
}
