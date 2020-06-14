package it.polito.oop.books;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Assignment {
	private String id;
	private ExerciseChapter exerciseChapter;
	private Map<Question,List<String>> response =new HashMap<>();
	
    public Assignment(String id, ExerciseChapter exerciseChapter) {
		this.id = id;
		this.exerciseChapter = exerciseChapter;
	}

	public String getID() {
        return id;
    }

    public ExerciseChapter getChapter() {
        return exerciseChapter;
    }

    public double addResponse(Question q,List<String> answers) {
        response.put(q, answers);
        return calculateScore(q);
    }
    
    public double calculateScore(Question q) {
    	double n=0,fp=0,fn=0;
        n=q.numAnswers();
        for(String s:response.get(q))
        	if(q.getIncorrectAnswers().contains(s))
        		fp++;
        for(String s:q.getCorrectAnswers())
        	if(!response.get(q).contains(s)) 
        		fn++;
        return (n-fp-fn)/n;
    }
    
    public double totalScore() {
    	double score=0;
        for(Question q:response.keySet()) {
        	score+=calculateScore(q);
        }
        return score;
    }

}
