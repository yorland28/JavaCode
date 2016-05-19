package curso.mongodb.homework;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import curso.mongodb.util.DataBase;
import curso.mongodb.util.Estudiantes;
import curso.mongodb.util.Scores;

/*
 * @author Yorland Garcia
 */
public class SchoolScore {
	public Double lowerScore(MongoDatabase dataBase){
		MongoCollection<Document> collection = dataBase.getCollection("students");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        Gson json=new Gson();
        double lower=100;
        for(Document cur: all){
//        	 System.out.println(json.toJson(cur));
        	 Estudiantes students = json.fromJson(json.toJson(cur), Estudiantes.class);
        	 List<Scores> scores = students.getScores();
        	 System.out.println(json.toJson(students.get_id())+"\n");
        	 System.out.println(json.toJson(students.getName())+"\n");
        	 lower=100;
        	 for(Scores score : scores ){
        		 if(score.getType().equalsIgnoreCase("homework")) {
        			 if(score.getScore()<lower){
        				 lower=score.getScore();
        			 }
        			 //System.out.println(json.toJson(score)+"\n");
        		 }
        	 }
        	 //System.out.println("lower Score="+ lower);
        	 collection.updateOne(eq("scores.score", lower),new Document("$pull", new Document("score", lower)));

        }
        return lower;
	}
	
	public static void main(String[] args) {
		SchoolScore schoolScore=new SchoolScore();
		System.out.print("lower Score:"+schoolScore.lowerScore(DataBase.connect("school")));        
	}
}