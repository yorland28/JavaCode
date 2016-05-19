package curso.mongodb.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import curso.mongodb.util.DataBase;

/*
 * @author Yorland Garcia 
 * 
 */
public class DeleteDocument {
	public int deleteEqualScores(MongoDatabase dataBase){
		MongoCollection<Document> albums= dataBase.getCollection("albums");
		MongoCollection<Document> images =dataBase.getCollection("images");
		List<Document> listImages= images.find().into(new ArrayList<Document>());
		int numDeleted=0;
		
		for(Document cur : listImages){
		System.out.print("id images: "+cur.get("_id")+"\n");
			Bson filterImages=eq("images",cur.get("_id"));
			List<Document>  busqueda=albums.find(filterImages).into(new ArrayList<Document>());
			//System.out.print("count images: "+busqueda.size()+"\n");
			
			if(busqueda.size()<1){
				numDeleted++;
				Bson idDelete=eq("_id",cur.get("_id"));
				images.deleteOne(idDelete);
			}
		}
		return numDeleted;
	}
	
	public Document highestScore(MongoDatabase dataBase){
		MongoCollection<Document> collection = dataBase.getCollection("grades");
	    Map<Integer, Double> m = new HashMap<Integer, Double>();
	    List<Document> all = collection.find().into(new ArrayList<Document>());

	    for(Document document : all) {
	        int id = document.getInteger("student_id");
	        double score = document.getDouble("score");
	        if(m.containsKey(id)) {
	            if(score < m.get(id)) {
	               m.put(id, score);
	            }
	        } else {
	            m.put(id, score);
	        }
	    }
	    //System.out.println(json.toJson(m));

	    for(Map.Entry<Integer, Double> entry : m.entrySet()) {
	        Bson deleteFilter = eq("_id", entry.getKey());
	        System.out.println(entry.getKey());
	        collection.deleteOne(deleteFilter);
	        System.out.println(m.get(entry.getKey()));
	    }

	    Bson sorting = orderBy(descending("score"));
	    Document doc = collection.find().sort(sorting).skip(100).limit(1).first();
	    //System.out.println(json.toJson(doc));
	    
	    return doc;
	}
	
	public static void main(String[] args) {
		DeleteDocument deleteDocument=new DeleteDocument();
		Gson json=new Gson();
		
		System.out.print("total deleted:"+deleteDocument.deleteEqualScores(DataBase.connect("tests")));
		System.out.print("List:"+json.toJson(deleteDocument.highestScore(DataBase.connect("students"))));
	}
}