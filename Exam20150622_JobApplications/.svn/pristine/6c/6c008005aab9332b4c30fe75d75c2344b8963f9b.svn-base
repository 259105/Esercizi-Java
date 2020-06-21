package applications;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
	private String name;
	private SortedMap<String,Applicant> applicants =new TreeMap<>();
	private Applicant winner;
	
	public Position(String name) {
		super();
		this.name = name;
	}

	public String getName() {return name;}
	
	public List<String> getApplicants() {
		return applicants.keySet().stream().collect(Collectors.toList());
	}
	
	public String getWinner() {
		if(winner==null)
			return null;
		return winner.getName(); 
	}
	
	public void setWinner(Applicant a) {
		winner=a;
	}
	public void addApplicant(Applicant a) {
		applicants.put(a.getName(),a);
	}
	public long getNOfApplicants() {
		return applicants.values().size();
	}
}