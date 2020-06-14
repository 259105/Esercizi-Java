package it.polito.oop.books;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Topic implements Comparable<Topic>{
	private String keyword;
	private Map<String,Topic> subTopics=new TreeMap<>();
	
	public Topic(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	
	@Override
	public String toString() {
	    return keyword;
	}

	public boolean addSubTopic(Topic topic) {
        if(subTopics.containsValue(topic))
        	return false;
        subTopics.put(topic.getKeyword(), topic);
        return true;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
        return subTopics.values().stream()
        		.sorted(Comparator.naturalOrder())
        		.collect(Collectors.toList());
	}
	
	public List<Topic> getAllSubTopics(){
		List<Topic> lista =new LinkedList<>();
		for(Topic t: getSubTopics()) {
			lista.add(t);
			lista.addAll(t.getAllSubTopics());
		}
		return lista;
	}

	@Override
	public int compareTo(Topic arg0) {
		return this.getKeyword().compareTo(arg0.getKeyword());
	}
}
