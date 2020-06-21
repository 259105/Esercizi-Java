package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	
	static final private int MAXSTUDENT=1000;
	static final private int MAXCOURSE=50;
	protected static final int STARTMATR=10000;
	protected static final int STARTCOURSE=10;
	
	static private int currMatr=STARTMATR;
	static private int currCourse=STARTCOURSE;
	private String nome;
	private String rector;
	protected Studente[] studenti=new Studente[MAXSTUDENT];
	protected Corso[] corsi=new Corso[MAXCOURSE];
	
	

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		nome=name;
		currMatr=STARTMATR;
		currCourse=STARTCOURSE;
	}
	
	/**
	 * Getter for the name of the university
	 * @return name of university
	 */
	public String getName(){
		return nome;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		rector= first+" "+last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return
	 */
	public String getRector(){
		return rector;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @return
	 */
	public int enroll(String first, String last){
		int id=currMatr-STARTMATR;
		if(id > MAXSTUDENT ) return -1;
		studenti[id]=new Studente(first,last,currMatr);
		currMatr++;
		return currMatr-1;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the id of the student
	 * @return information about the student
	 */
	public String student(int id){
		int i=id-STARTMATR;
		return studenti[i].getStudente();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		if(currCourse-STARTCOURSE > MAXCOURSE ) return -1;
		corsi[currCourse-STARTCOURSE]=new Corso(title,teacher,currCourse);
		currCourse++;
		return currCourse-1;
	}
	
	/**
	 * Retrieve the information for a given course
	 * 
	 * @param code unique code of the course
	 * @return information about the course
	 */
	public String course(int code){
		int i=code-STARTCOURSE;
		return corsi[i].getCorso();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		int ID,Code;
		ID=studentID-STARTMATR;
		Code=courseCode-STARTCOURSE;
		studenti[ID].iscrizione(corsi[Code]);
		corsi[Code].iscrizione(studenti[ID]);
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		return corsi[courseCode-STARTCOURSE].printIscritti();
	}

	/**
	 * Retrieves the study plan for a student
	 * 
	 * @param studentID id of the student
	 * @return list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		return studenti[studentID-STARTMATR].printIscrizioni();
	}
}
