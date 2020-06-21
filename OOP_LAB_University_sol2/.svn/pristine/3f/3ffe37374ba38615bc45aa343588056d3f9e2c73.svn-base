package university;

 class Course {
	
	private static final String SEPARATOR = ",";
	private int code;
	private String title;
	private String teacher;
	private Student[] students;
	
	public Course(int code, String title, String teacher) {
		this.code = code;
		this.title = title;
		this.teacher = teacher;
		students = new Student[University.MAX_STUDENTS_PER_COURSE];
	}
	
	public String toString(){
		return code + SEPARATOR + title + SEPARATOR + teacher;
	}

	public void enroll(Student s) {
		for (int i = 0; i < students.length; i++) {
			if(students[i] == null){
				students[i]=s;
				break;
			}
		}
	}
	
	public String attendees(){
		StringBuffer result = new StringBuffer();
		
		for(Student s : students){
			if(s!=null){
				result.append(s.toString()).append("\n");
			}
		}
		return result.toString();
	}

	public int getCode() {
		return code;
	}

}
