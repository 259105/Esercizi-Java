package ticketing;

/**
 * Class representing the ticket linked to an issue or malfunction.
 * 
 * The ticket is characterized by a severity and a state.
 */
public class Ticket implements Comparable<Ticket> {
    private User reporter;
    private User maintainer;
    private Component component;
    private String description;
    private String solutionDescription;
    private int id;
    private Ticket.Severity severity;
    private Ticket.State state=Ticket.State.Open;
    
    public Ticket(User user, Component component, String description, int id, Severity severity) {
		super();
		this.reporter = user;
		this.component = component;
		this.description = description;
		this.id = id;
		this.severity = severity;
	}

	/**
     * Enumeration of possible severity levels for the tickets.
     * 
     * Note: the natural order corresponds to the order of declaration
     */
    public enum Severity { Blocking, Critical, Major, Minor, Cosmetic };
    
    /**
     * Enumeration of the possible valid states for a ticket
     */
    public static enum State { Open, Assigned, Closed }
    
    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }
    
    public Severity getSeverity() {
        return severity;
    }

    public String getAuthor(){
        return reporter.getName();
    }
    
    public String getComponent(){
        return component.getName();
    }
    
    public State getState(){
        return state;
    }
    
    public void setState(Ticket.State nstate) {
    	state=nstate;
    }
    
    public String getSolutionDescription() throws TicketException {
        if(state!=Ticket.State.Closed)
        	throw new TicketException();
        return solutionDescription;
    }
    
    public void setSolutionDescription(String s) {
    	solutionDescription = s;
    }
	/**
	 * @return the maintainer
	 */
	public User getMaintainer() {
		return maintainer;
	}
	public String getMaintainerName() {
		return maintainer.getName();
	}

	/**
	 * @param maintainer the maintainer to set
	 */
	public void setMaintainer(User maintainer) {
		this.maintainer = maintainer;
	}

	@Override
	public int compareTo(Ticket o) {
		return this.getSeverity().compareTo(o.getSeverity());
	}
}
