package it.polito.oop.books;



import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Book {
	private Map<String,Topic> topics=new TreeMap<>();
	private Map<String,Question> questions =new TreeMap<>();
	private Map<String,TheoryChapter> theoryChapters = new TreeMap<>();
	private Map<String, ExerciseChapter> exerciseChapters =new TreeMap<>();
	private Map<String, Assignment> assignment =new TreeMap<>();

	/**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
		if( keyword==null || keyword.equals("") )
			throw new BookException();
		if(topics.containsKey(keyword))
			return topics.get(keyword);
		Topic t= new Topic(keyword);
		topics.put(keyword, t );
		return t;
	}

	public Question createQuestion(String question, Topic mainTopic) {
		questions.put(question, new Question(question,mainTopic));
        return questions.get(question);
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		theoryChapters.put(title, new TheoryChapter(title,numPages,text));
        return theoryChapters.get(title);
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		exerciseChapters.put(title,new ExerciseChapter(title,numPages));
		return exerciseChapters.get(title);
	}

	public List<Topic> getAllTopics() {
		return topics.values().stream().distinct().collect(Collectors.toList());
	}

	public boolean checkTopics() {
       for(ExerciseChapter exCh : exerciseChapters.values()) {
    	   for(Topic tEx : exCh.getTopics()) {
    		   int f=0;
    		   for(TheoryChapter thCh : theoryChapters.values()) {
    			   if(thCh.getTopics().contains(tEx)) {
    				   f=1;
    				   break;
    			   }
    		   }
    		   if(f==0)
    			   return false;
    	   }
       }
       return true;
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		assignment.put(ID,new Assignment(ID,chapter));
		return assignment.get(ID);
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
        return questions.values().stream()
        		.collect(Collectors.groupingBy(Question::numAnswers,
        				TreeMap::new,
        				Collectors.toList()));
    }
}
