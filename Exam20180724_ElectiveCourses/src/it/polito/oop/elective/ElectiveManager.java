package it.polito.oop.elective;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Manages elective courses enrollment.
 * 
 *
 */
public class ElectiveManager {
	private SortedMap<String,Course> corsi=new TreeMap<>();
	private SortedMap<String,Student> studenti=new TreeMap<>();
	private List<Notifier> notificatori=new LinkedList<>();
	
    /**
     * Define a new course offer.
     * A course is characterized by a name and a number of available positions.
     * 
     * @param name : the label for the request type
     * @param availablePositions : the number of available positions
     */
    public void addCourse(String name, int availablePositions) {
        corsi.put(name, new Course(name,availablePositions));
    }
    
    /**
     * Returns a list of all defined courses
     * @return
     */
    public SortedSet<String> getCourses(){
        return (SortedSet<String>)corsi.keySet();
    }
    
    /**
     * Adds a new student info.
     * 
     * @param id : the id of the student
     * @param gradeAverage : the grade average
     */
    public void loadStudent(String id, 
                                  double gradeAverage){
    	if(studenti.containsKey(id)) {
    		studenti.get(id).setAvgVote(gradeAverage);
    		return;
    	}
        studenti.put(id,new Student(id,gradeAverage));
    }

    /**
     * Lists all the students.
     * 
     * @return : list of students ids.
     */
    public Collection<String> getStudents(){
        return studenti.keySet();
    }
    
    /**
     * Lists all the students with grade average in the interval.
     * 
     * @param inf : lower bound of the interval (inclusive)
     * @param sup : upper bound of the interval (inclusive)
     * @return : list of students ids.
     */
    public Collection<String> getStudents(double inf, double sup){
        return studenti.values().stream()
        		.filter(s -> s.getAvgVote()>=inf)
        		.filter(s -> s.getAvgVote()<=sup)
        		.map(Student::getId)
        		.collect(Collectors.toList());
    }


    /**
     * Adds a new enrollment request of a student for a set of courses.
     * <p>
     * The request accepts a list of course names listed in order of priority.
     * The first in the list is the preferred one, i.e. the student's first choice.
     * 
     * @param id : the id of the student
     * @param selectedCourses : a list of of requested courses, in order of decreasing priority
     * 
     * @return : number of courses the user expressed a preference for
     * 
     * @throws ElectiveException : if the number of selected course is not in [1,3] or the id has not been defined.
     */
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
        if(courses.size()<=0 || courses.size()>3 || !studenti.containsKey(id))
        	throw new ElectiveException();
        int n=0;
        List<Course> c=new LinkedList<>();
        for(String nomecorso : courses) {
        	if(!corsi.containsKey(nomecorso))
        		throw new ElectiveException();
        	c.add(corsi.get(nomecorso));
        	c.get(n).setPreferenze(n+1);
        	n++;
        }
        studenti.get(id).setEnroll(c);
        for(Notifier noti:notificatori) {
        	noti.requestReceived(id);
        }
        return n;
    }
    
    /**
     * Returns the number of students that selected each course.
     * <p>
     * Since each course can be selected as 1st, 2nd, or 3rd choice,
     * the method reports three numbers corresponding to the
     * number of students that selected the course as i-th choice. 
     * <p>
     * In case of a course with no requests at all
     * the method reports three zeros.
     * <p>
     * 
     * @return the map of list of number of requests per course
     */
    public Map<String,List<Long>> numberRequests(){
        return corsi.values().stream()
        .collect(Collectors.toMap(Course::getNome, Course::getPreferenze));
    }
    
    
    /**
     * Make the definitive class assignments based on the grade averages and preferences.
     * <p>
     * Student with higher grade averages are assigned to first option courses while they fit
     * otherwise they are assigned to second and then third option courses.
     * <p>
     *  
     * @return the number of students that could not be assigned to one of the selected courses.
     */
    public long makeClasses() {
        studenti.values().stream()
        		//.sorted((s1,s2) -> (int)-(s1.getAvgVote()*100-s2.getAvgVote()*100) )
        		.sorted(Comparator.comparingDouble(Student::getAvgVote).reversed())
        		.map(Student::getId)
        		.forEach(s-> {
        			
        			List<Course> classificaPersonale=studenti.get(s).getEnroll();
                	for(Course c:classificaPersonale) {
                		if(c.getStudent().size()<c.getnPosti()) {
                			c.addStudent(studenti.get(s));
                			studenti.get(s).setCorso(c);
                			for(Notifier noti:notificatori) {
                				noti.assignedToCourse(s, c.getNome());
                			}
                			break;
                		}
                	}
                	
        		});
        return getNotAssigned().size();
        
//        for(String studente:studentiXMedia) {
//        	List<Course> classificaPersonale=studenti.get(studente).getEnroll();
//        	for(Course c:classificaPersonale) {
//        		if(c.getStudent().size()<c.getnPosti()) {
//        			c.addStudent(studenti.get(studente));
//        			studenti.get(studente).setCorso(c);
//        			for(Notifier noti:notificatori) {
//        				noti.assignedToCourse(studente, c.getNome());
//        			}
//        			break;
//        		}
//        	}
//        	if(studenti.get(studente).getCorso()==null) {
//        		n++;
//        	}
//        }
//        return n;
    }
    
    
    /**
     * Returns the students assigned to each course.
     * 
     * @return the map course name vs. student id list.
     */
    public Map<String,List<String>> getAssignments(){
        return corsi.values().stream()
        		.collect(Collectors.toMap(Course::getNome,
        				c -> c.getStudent().stream().map(Student::getId).collect(Collectors.toList())
        				));
    }
    
    
    /**
     * Adds a new notification listener for the announcements
     * issues by this course manager.
     * 
     * @param listener : the new notification listener
     */
    public void addNotifier(Notifier listener) {
        notificatori.add(listener);
    }
    
    /**
     * Computes the success rate w.r.t. to first 
     * (second, third) choice.
     * 
     * @param choice : the number of choice to consider.
     * @return the success rate (number between 0.0 and 1.0)
     */
    public double successRate(int choice){
    	double n=0;
        for(Student s:studenti.values()) {
        	if(choice-1<s.getEnroll().size() && s.getEnroll().get(choice-1)==s.getCorso())
        		n++;
        }
        return (double)n/(double)studenti.size();
    }

    
    /**
     * Returns the students not assigned to any course.
     * 
     * @return the student id list.
     */
    public List<String> getNotAssigned(){
        return studenti.values().stream()
        		.filter(s -> s.getCorso()==null)
        		.map(Student::getId)
        		.collect(Collectors.toList());
    }
    
    
}
