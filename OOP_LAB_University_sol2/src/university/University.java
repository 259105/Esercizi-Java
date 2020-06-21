package university;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class University {
	
	// System-level paramters (constants)
	
	public static final int MAX_STUDENTS = 1000;
	public static final int MAX_COURSES = 50;
	public static final int MAX_COURSES_PER_STUDENT = 25;
	public static final int MAX_STUDENTS_PER_COURSE = 100;

	public final static int INITIAL_ID = 10000;
	public final static int INITIAL_CODE = 10;
	
	// Attributes
	private String name;
	private String rector;
	
//	private Student[] include; // = null
	private int nextId = INITIAL_ID;
	private Collection<Student> collezioneStudenti;
	private List<Student> listaStudenti;
	private Map<Integer,Student> mappaStudenti;
	
	
//	private Course[] offers;
	private Map<Integer,Course> corsi = new HashMap<>();
	private int nextCode = INITIAL_CODE;

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name = name;
		this.rector = "<none>";
		
		//include = new Student[MAX_STUDENTS];
		
		collezioneStudenti = new ArrayList<>(); // <> diamond operator
		
		listaStudenti = new LinkedList<>(); // <> diamond operator
		listaStudenti = new LinkedList<Student>();
		
		mappaStudenti = new HashMap<>();
		
//		offers = new Course[MAX_COURSES];
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		this.rector = first + " " + last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * @return
	 */
	public String getRector(){
		return rector;
	}
	
	/**
	 * Enroll a student in the university
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		Student s = new Student( nextId , first, last);
		
		//include[ nextId - INITIAL_ID ] = s;
		listaStudenti.add(s);
		mappaStudenti.put( nextId, s );
		
		return nextId++;
	}
	
	/**
	 * Retrieves the information for a given student
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id) throws UniversityException {
		Student s = findStudent(id);
//		if( s == null ) {
//			return "<STUDENTE NON PRESENTE>";
//		}
		return s.toString();
	}

	private Student findStudent(int id) throws UniversityException {
		if(id < INITIAL_ID || id > INITIAL_ID+MAX_STUDENTS) { // il valore è errato
			//return null;
			throw new UniversityException("Invalid student id: " + id);
		}
//		Student s = include[ id - INITIAL_ID ];
//		if( s == null ) {  // lo studente non esiste
//			//return null;
//			throw new UniversityException("Missing student id: " + id);
//		}
//		return s;
		
		// Utilizzando una Collection<Student>
//		for(Student s : collezioneStudenti) { // se abbiamo a che fare con una Collection
//			if( s.getId() == id) {
//				return s;
//			}
//		}
//		throw new UniversityException("Missing student id: " + id);
				

		// Utilizzando una List<Student>
//		Student s = listaStudenti.get(id - INITIAL_ID);
//		if( s == null ) {  // lo studente non esiste
//			//return null;
//			throw new UniversityException("Missing student id: " + id);
//		}
//		return s;
		
		// Utilizzando una Map<Integer,Student>
		Student s = mappaStudenti.get(id);
		if( s == null ) {  // lo studente non esiste
			//return null;
			throw new UniversityException("Missing student id: " + id);
		}
		return s;

	}
	
	/**
	 * Activates a new course with the given teacher
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		Course c = new Course(nextCode,title,teacher);
		//offers[nextCode - INITIAL_CODE] = c;
		corsi.put( nextCode , c );
		return nextCode++;
	}
	
	/**
	 * Retrieve the information for a given course
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code) throws UniversityException {
//		Course c = findCourse(code);
		if( corsi.containsKey(code) ) {
			Course c = corsi.get(code);
			return c.toString();
		}else {
			throw new UniversityException("Cannot find course with code " + code);
		}
	}

//	private Course findCourse(int code) {
//		Course c = offers[code-INITIAL_CODE];
//		return c;
//	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode)  throws UniversityException {
		Student s = findStudent(studentID);
//		Course c = findCourse(courseCode);
		Course c = corsi.get(courseCode);
		
		s.enroll(c);
		c.enroll(s);
	}
	
	/**
	 * Retrieve a list of attendees
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
//		Course c = findCourse(courseCode);
		Course c = corsi.get(courseCode);
		return c.attendees();
	}

	/**
	 * Retrieves the study plan for a student
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID)  throws UniversityException {
		Student s = findStudent(studentID);
		return s.courses();
	}
	
	public String listAllStudent() {
		String elencoStudenti = "";
		
		for(Integer id : mappaStudenti.keySet()) {
			Student s = mappaStudenti.get(id);
			// ...
		}
		
		for( Student s : mappaStudenti.values() ) {
			elencoStudenti += s.toString() + "\n";
		}
		return elencoStudenti;
	}
	
}
