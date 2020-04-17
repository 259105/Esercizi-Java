package university;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;


public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	
	public int enroll(String first, String last){
		int r=super.enroll(first, last);
		if(r>=0)
			logger.info("New student enrolled: "+r+", "+first+" "+last);
		return r;
	}
	
	public int activate(String title, String teacher){
		int r=super.activate(title, teacher);
		if(r>=0)
			logger.info("New course activated: "+r+", "+title+" "+teacher);
		return r;
	}
	
	public void register(int studentID, int courseCode){
		super.register(studentID, courseCode);
		logger.info("Student "+studentID+" signed up for course "+courseCode);
	}
	
	public void exam(int studentId, int courseID, int grade) {
		if(grade>30 || grade<0) {
			System.err.println("Errore voto non nel range(0-30)");
			return;
		}
		int idS=studentId-STARTMATR;
		int idC=courseID-STARTCOURSE;
		Esame e= new Esame(grade,studenti[idS],corsi[idC]);
		studenti[idS].regEsame(e);
		corsi[idC].regEsame(e);
		logger.info("Student "+studentId+" took an exam in course "+courseID+" with grade "+grade);
	}

	public String studentAvg(int studentId) {
		int id=studentId-STARTMATR;
		float avg=studenti[id].avg();
		if(avg<0) {
			return "Student "+ studentId + " hasn't taken any exams";
		}else {
			return "Student "+studentId+ " : "+ avg;
		}
	}
	
	public String courseAvg(int courseId) {
		int id=courseId-STARTCOURSE;
		float avg=corsi[id].avg();
		if(avg<0) {
			return "No student has taken the exam in "+corsi[id].getTitolo();
		}else {
			return "The average for the course "+corsi[id].getTitolo()+" is: "+avg;
		}
	}
	
	public String topThreeStudents() {
		Studente classifica[]= new Studente[3];
		for(int i=0;i<studenti.length;i++) {
			if(studenti[i]==null) break;
			float p=studenti[i].getPnt();
			for(int j=classifica.length-1;j>=0;j--) {
				if(classifica[j]==null && j==0) { classifica[j]=studenti[i]; continue;};
				if(classifica[j]==null) continue;
				if(p>classifica[j].getPnt()) {
					if(j==2) {
						classifica[j]=studenti[i];
					}else {
						classifica[j+1]=classifica[j];
						classifica[j]=studenti[i];
					}
				}
			}
		}
		StringBuffer s=new StringBuffer();
		for(int i=0;i<3;i++) {
			if(classifica[i]==null) break;
			s.append(String.format("%s %s : %.2f\n",classifica[i].getNome(),classifica[i].getCognome(),classifica[i].getPnt()));
		}
		return s.toString();
	}
}
