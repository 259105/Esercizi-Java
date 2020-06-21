package university;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Student {
	
	private static final String SEPARATOR = " ";
	private int ID;
	private String first;
	private String last;
	
	//private Course[] courses;
	Set<Course> corsi;
	
	public Student(int id, String first, String last) {
		this.ID = id;
		this.first = first;
		this.last = last;
		//courses = new Course[University.MAX_COURSES_PER_STUDENT];
		//corsi = new LinkedList<>();
		corsi = new HashSet();
	}
	
	public String toString(){
		return ID + SEPARATOR + first + SEPARATOR + last;
		// TODO: probably to be optimized with StringBuffer
//		return (new StringBuffer()).append(ID).append(SEPARATOR).
//				append(first).append(SEPARATOR).
//				append(last).toString();
	}
	
	public void enroll(Course c) throws UniversityException {
//		for(int i=0; i< courses.length; ++i){
//			if( courses[i] == null){
//				courses[i] = c;
//				break;
//			}
//		}
		
		if( corsi.contains(c) ) {  // lo studente è già iscritto
			System.err.println("Doppia iscrizione");
			throw new UniversityException("Doppia iscrizione dello studente " + ID + " al corso " + c.getCode() ); 
		}
		
		corsi.add(c);
	}

	public String courses() {
		StringBuffer result = new StringBuffer();
//		for(Course c : courses){
		for(Course c : corsi) {
			if(c!=null){
				result.append(c).append("\n");
			}
		}
		return result.toString();
	}

	public int getId() {
		return this.ID;
	}

}
