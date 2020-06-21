package applications;

import java.util.*;
import java.util.stream.Collectors;

public class HandleApplications {
	private SortedMap<String,Skill> skills=new TreeMap<>();
	private Map<String,Applicant> applicants=new HashMap<>();
	
	public void addSkills(String... names) throws ApplicationException {
		for(String name:names) {
			if(skills.containsKey(name))
				throw new ApplicationException(name+" duplicated");
			skills.put(name, new Skill(name));
		}
	}
	public void addPosition(String name, String... skillNames) throws ApplicationException {
		for(String skillname:skillNames) {
			if(!skills.containsKey(skillname))
				throw new ApplicationException(skillname + " undefined");
			if(getPosition(name)!=null)
				throw new ApplicationException(name +" duplicated");
		}
		Position p=new Position(name);
		for(String skillname:skillNames)
			skills.get(skillname).addPositions(p);
	}
	
	public Skill getSkill(String name) {return skills.get(name);}
	
	public Position getPosition(String name) {
		for(Skill skill:skills.values())
			if(skill.checkPosition(name))
				return skill.getPosition(name);
		return null;
	}
	
	public Set<Skill> getSkillsByPosition(String name) {
		SortedSet<Skill> skills=new TreeSet<>();
		for(Skill skill:this.skills.values())
			if(skill.checkPosition(name))
				skills.add(skill);
		if(skills.isEmpty())
			return null;
		return skills;
	}
	
	public Integer getLevelByPosition(String name) {
		return getSkillsByPosition(name).size()*6;
	}
	
	public void addApplicant(String name, String capabilities) throws ApplicationException {
		if(applicants.containsKey(name))
			throw new ApplicationException(name + " duplicated");
		String[] capabilityS=capabilities.split(",");
		for(String capability:capabilityS) {
			String[] element=capability.split(":");
			if(!skills.containsKey(element[0]))
				throw new ApplicationException(element[0]+" undefined");
			Integer level=Integer.parseInt(element[1]);
			if(level.compareTo(1)<0 || level.compareTo(10)>0)
				throw new ApplicationException(level+ " invalid level");
		}
		SortedMap<Skill,Integer> c=new TreeMap<>(Comparator.naturalOrder());
		for(String capability:capabilityS) {
			String[] element=capability.split(":");
			Skill s=skills.get(element[0]);
			Integer i=Integer.parseInt(element[1]);
			c.put(s, i);
		}
		Applicant a=new Applicant(name,c);
		applicants.put(name, a);
	}
	public String getCapabilities(String applicantName) throws ApplicationException {
		if(!applicants.containsKey(applicantName))
			throw new ApplicationException(applicantName+" undefined");
		return applicants.get(applicantName).getCapabilitiesToString();
	}
	
	public void enterApplication(String applicantName, String positionName) throws ApplicationException {
		if(!applicants.containsKey(applicantName) 
				|| getPosition(positionName)==null
				|| !applicants.get(applicantName).checkSkill(getSkillsByPosition(positionName))
				|| applicants.get(applicantName).getPosition()!=null)
			throw new ApplicationException();
		Position p=getPosition(positionName);
		Applicant a=applicants.get(applicantName);
		a.setPosition(p);
		p.addApplicant(a);
	}
	
	public int setWinner(String applicantName, String positionName) throws ApplicationException {
		Position p;
		if((p=getPosition(positionName))==null 
				|| !applicants.containsKey(applicantName) 
				|| !p.getApplicants().contains(applicantName)
				|| p.getWinner()!=null
				|| applicants.get(applicantName).getSumOfLevel(getSkillsByPosition(positionName)).compareTo(getLevelByPosition(positionName))<=0)
			throw new ApplicationException();
		Applicant a=applicants.get(applicantName);
		p.setWinner(a);
		a.setWinnedPosition(p);
		return a.getSumOfLevel(getSkillsByPosition(positionName));
	}
	
	public SortedMap<String, Long> skill_nApplicants() {
		return applicants.values().stream()
				.flatMap(a->a.getCapabilities().stream())
				.collect(Collectors.groupingBy(Skill::getName,
						TreeMap::new,
						Collectors.counting()));
	}
	public String maxPosition() {
		Set<Position> positions=new LinkedHashSet<>();
		for(Skill s:skills.values())
			positions.addAll(s.getPositions());
		return positions.stream()
				.max(Comparator.comparing(Position::getNOfApplicants))
				.get().getName();
	}
}

