package curso.mongodb.util;

import java.util.List;

public class Estudiantes {
	 private int _id;
	 private String name;
	 private List<Scores> scores;
	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the scores
	 */
	public List<Scores> getScores() {
		return scores;
	}
	/**
	 * @param scores the scores to set
	 */
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
	 
	
	

}
