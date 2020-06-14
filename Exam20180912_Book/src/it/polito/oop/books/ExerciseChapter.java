package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ExerciseChapter {
	private String title;
	private int numPages;
	private Set<Question> questions=new HashSet<>();
	
    public ExerciseChapter(String title, int numPages) {
		this.title = title;
		this.numPages = numPages;
	}


	public List<Topic> getTopics() {
        return questions.stream()
        		.map(Question::getMainTopic)
        		.distinct().sorted(Comparator.naturalOrder())
        		.collect(Collectors.toList());
	};
	

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
    

	public void addQuestion(Question question) {
		questions.add(question);
	}	
}
