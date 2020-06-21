package trail;

import java.util.LinkedList;
import java.util.List;

public class Runner implements Comparable<Runner>{
	private String name;
	private String surname;
	private int bibNumber;
	private List<Passage> passages=new LinkedList<>();

    
    public Runner(String name, String surname, int bibNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.bibNumber = bibNumber;
	}

	public int getBibNumber(){
        return bibNumber;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

	@Override
	public int compareTo(Runner arg0) {
		return this.bibNumber-arg0.bibNumber;
	}
	
	public void addPassage(Passage passage) {
		passages.add(passage);
	}
	
	public List<Passage> getPassages(){
		return passages;
	}
}
