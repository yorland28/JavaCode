package curso.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DataBase {
	public static MongoDatabase connect(String nameDataBase){
		MongoClient client = new MongoClient();
        MongoDatabase dataBase = client.getDatabase(nameDataBase);
        
        return dataBase;
	}
}