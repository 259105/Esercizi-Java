package it.polito.oop.books;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question {
	private String text;
	private Topic topic;
	private Map<Boolean,Set<String>> answers=new HashMap<>();
	
	public Question(String text, Topic topic) {
		this.text = text;
		this.topic = topic;
		this.answers.put(true, new HashSet<>());
		this.answers.put(false, new HashSet<>());
	}

	public String getQuestion() {
		return text;
	}
	
	public Topic getMainTopic() {
		return topic;
	}

	public void addAnswer(String answer, boolean correct) {
		answers.get(correct).add(answer);
	}
	
    @Override
    public String toString() {
        return text + "("+topic.getKeyword()+")";
    }

	public long numAnswers() {
		long count=0;
	    Collection<Set<String>> vf=answers.values();
	    for(Set<String> s : vf)
	    	for(@SuppressWarnings("unused") String str : s)
	    		count++;
	    return count;
	}

	public Set<String> getCorrectAnswers() {
		return answers.get(true);
	}

	public Set<String> getIncorrectAnswers() {
        return answers.get(false);
	}
}
