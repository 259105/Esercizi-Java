package ticketing;

import java.util.HashSet;
import java.util.Set;

import ticketing.IssueManager.UserClass;

public class User {
	private String name;
	private boolean reporter=false;
	private boolean maintainer=false;
	
	public User(String name,UserClass[] uclass) {
		super();
		this.name = name;
		for(UserClass uc:uclass) {
			if(uc.equals(UserClass.Maintainer))
				this.maintainer=true;
			if(uc.equals(UserClass.Reporter))
				this.reporter=true;
		}
	}
	
	public User(String name,Set<UserClass> classes) {
		super();
		this.name = name;
		for(UserClass uc:classes) {
			if(uc.equals(UserClass.Maintainer))
				this.maintainer=true;
			if(uc.equals(UserClass.Reporter))
				this.reporter=true;
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the reporter
	 */
	public boolean isReporter() {
		return reporter;
	}

	/**
	 * @return the maintainer
	 */
	public boolean isMaintainer() {
		return maintainer;
	}

	/**
	 * @param reporter the reporter to set
	 */
	public void setReporter(boolean reporter) {
		this.reporter = reporter;
	}

	/**
	 * @param maintainer the maintainer to set
	 */
	public void setMaintainer(boolean maintainer) {
		this.maintainer = maintainer;
	}
	
	public Set<UserClass> getUserClass(){
		Set<UserClass> uc=new HashSet<>();
		if(maintainer) uc.add(UserClass.Maintainer);
		if(reporter) uc.add(UserClass.Reporter);
		return uc;
	}
}
