import university.*;

public class ExampleApp {

	public static void main(String[] args) {
		
		String universityName = "Politecnico di Torino";
		
		University poli = new University(universityName);
		
		poli.setRector("Guido", "Saracco");
		
		System.out.println("Rector of " + poli.getName() + " : " + poli.getRector()); // Guido Saracco
		
		int s1 = poli.enroll("Mario","Rossi");
		int s2 = poli.enroll("Giuseppe","Verdi");
		
		System.out.println("Enrolled students " + s1 + ", " + s2); // 10000, 10001
		System.out.println("s1 = " + poli.student(s1)); // 10000 Mario Rossi
		System.out.println("s2 = " + poli.student(s2)); // 10001 Giuseppe Verdi

		int macro = poli.activate("Macro Economics", "Paul Krugman");
		int oop = poli.activate("Object Oriented Programming", "James Gosling");
		
		System.out.println("Activated courses " + macro + " and " + oop); // 10 and 11
		poli.register(10000, 10);
		poli.register(10001, 10);
		poli.register(10001, 11);
		
		System.out.println(poli.listAttendees(macro));
		// 10000 Mario Rossi
		// 10001 Giuseppe Verdi
		
		System.out.println(poli.studyPlan(s2));
		// 10,Macro Economics,Paul Krugman
		// 11,Object Oriented Programming,Marco Torchiano
	}
}
