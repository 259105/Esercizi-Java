package it.polito.oop.books;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



public class TheoryChapter {
	private String title;
	private int numPages;
	private String text;
	private Map<String,Topic> topics=new TreeMap<>();
	
    public TheoryChapter(String title, int numPages, String text) {
		this.title = title;
		this.numPages = numPages;
		this.text = text;
	}

	public String getText() {
		return text;
		
	}

    public void setText(String newText) {
    	text=newText;
    }


	public List<Topic> getTopics() {
		List<Topic> lista=new LinkedList<>();
		for(Topic t: topics.values()) {
			lista.add(t);
			lista.addAll(t.getAllSubTopics());
		}
		lista.sort(Comparator.naturalOrder());
		return lista.stream().distinct().collect(Collectors.toList());
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
    	title=newTitle;
    }

    public int getNumPages() {
        return numPages;
    }
    
    public void setNumPages(int newPages) {
    	numPages=newPages;
    }
    
    public void addTopic(Topic topic) {
    	topics.put(topic.getKeyword(),topic);
    }
    
}
