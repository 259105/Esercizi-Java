package ticketing;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class IssueManager {
	private int id=1;
	private Map<String,User> users =new HashMap<>();
	private Map<String,Component> components=new HashMap<>();
	private Map<Integer,Ticket> tickets=new HashMap<>();
	
    /**
     * Eumeration of valid user classes
     */
    public static enum UserClass {
        /** user able to report an issue and create a corresponding ticket **/
        Reporter, 
        /** user that can be assigned to handle a ticket **/
        Maintainer }
    
    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, UserClass... classes) throws TicketException {
        if(users.containsKey(username) || classes.length==0)
        	throw new TicketException();
    	users.put(username, new User(username,classes));
    }

    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, Set<UserClass> classes) throws TicketException {
        if(users.containsKey(username) || classes.isEmpty())
        	throw new TicketException();
    	users.put(username,new User(username,classes));
    }
   
    /**
     * Retrieves the user classes for a given user
     * 
     * @param username name of the user
     * @return the set of user classes the user belongs to
     */
    public Set<UserClass> getUserClasses(String username){
        return users.get(username).getUserClass();
    }
    
    /**
     * Creates a new component
     * 
     * @param name unique name of the new component
     * @throws TicketException if a component with the same name already exists
     */
    public void defineComponent(String name) throws TicketException {
    	if(components.containsKey(name))
    		throw new TicketException();
        components.put(name, new Component(name));
    }
    
    /**
     * Creates a new sub-component as a child of an existing parent component
     * 
     * @param name unique name of the new component
     * @param parentPath path of the parent component
     * @throws TicketException if the the parent component does not exist or 
     *                          if a sub-component of the same parent exists with the same name
     */
    public void defineSubComponent(String name, String parentPath) throws TicketException {
    	String[] path = parentPath.split("/");
    	if(!components.containsKey(path[path.length-1]) || components.get(path[path.length-1]).getSubComponentNames().contains(name))
    		throw new TicketException();
    	Component subComp=new Component(name);
    	Component uppComp=components.get(path[path.length-1]);
        components.put(name,subComp);
        uppComp.addSubComponents(subComp);
        subComp.setPredecessor(uppComp);
    }
    
    /**
     * Retrieves the sub-components of an existing component
     * 
     * @param path the path of the parent
     * @return set of children sub-components
     */
    public Set<String> getSubComponents(String path){
    	String[] paths = path.split("/");
    	return components.get(paths[paths.length-1]).getSubComponentNames();
    }

    /**
     * Retrieves the parent component
     * 
     * @param path the path of the parent
     * @return name of the parent
     */
    public String getParentComponent(String path){
    	String[] paths = path.split("/");
    	if(components.get(paths[paths.length-1]).getPredecessor()==null)
    		return null;
    	Component prec=components.get(paths[paths.length-1]).getPredecessor();
    	return prec.getPath();
    }

    /**
     * Opens a new ticket to report an issue/malfunction
     * 
     * @param username name of the reporting user
     * @param componentPath path of the component or sub-component
     * @param description description of the malfunction
     * @param severity severity level
     * 
     * @return unique id of the new ticket
     * 
     * @throws TicketException if the user name is not valid, the path does not correspond to a defined component, 
     *                          or the user does not belong to the Reporter {@link IssueManager.UserClass}.
     */
    public int openTicket(String username, String componentPath, String description, Ticket.Severity severity) throws TicketException {
    	String[] path = componentPath.split("/");
    	if(!users.containsKey(username) || !components.containsKey(path[path.length-1]) || !users.get(username).isReporter())
    		throw new TicketException();
    	User u=users.get(username);
    	Component c=components.get(path[path.length-1]);
    	tickets.put(id, new Ticket(u,c,description,id,severity));
    	return id++;
    }
    
    /**
     * Returns a ticket object given its id
     * 
     * @param ticketId id of the tickets
     * @return the corresponding ticket object
     */
    public Ticket getTicket(int ticketId){
        return tickets.get(ticketId);
    }
    
    /**
     * Returns all the existing tickets sorted by severity
     * 
     * @return list of ticket objects
     */
    public List<Ticket> getAllTickets(){
        return tickets.values().stream()
        		.sorted(Comparator.naturalOrder())
        		.collect(Collectors.toList());
    }
    
    /**
     * Assign a maintainer to an open ticket
     * 
     * @param ticketId  id of the ticket
     * @param username  name of the maintainer
     * @throws TicketException if the ticket is in state <i>Closed</i>, the ticket id or the username
     *                          are not valid, or the user does not belong to the <i>Maintainer</i> user class
     */
    public void assingTicket(int ticketId, String username) throws TicketException {
        if(!tickets.containsKey(ticketId) || !users.containsKey(username) || !users.get(username).isMaintainer() || tickets.get(ticketId).getState()==Ticket.State.Closed)
        	throw new TicketException();
        Ticket t = tickets.get(ticketId);
        User u =users.get(username);
        t.setState(Ticket.State.Assigned);
        t.setMaintainer(u);
    }

    /**
     * Closes a ticket
     * 
     * @param ticketId id of the ticket
     * @param description description of how the issue was handled and solved
     * @throws TicketException if the ticket is not in state <i>Assigned</i>
     */
    public void closeTicket(int ticketId, String description) throws TicketException {
        Ticket t =tickets.get(ticketId);
        if(t.getState()!=Ticket.State.Assigned)
        	throw new TicketException();
        t.setSolutionDescription(description);
        t.setState(Ticket.State.Closed);
    }

    /**
     * returns a sorted map (keys sorted in natural order) with the number of  
     * tickets per Severity, considering only the tickets with the specific state.
     *  
     * @param state state of the tickets to be counted, all tickets are counted if <i>null</i>
     * @return a map with the severity and the corresponding count 
     */
    public SortedMap<Ticket.Severity,Long> countBySeverityOfState(Ticket.State state){
        return tickets.values().stream()
        		.filter(t-> t.getState()==state || state==null)
        		.collect(Collectors.groupingBy(Ticket::getSeverity,
        				TreeMap::new,
        				Collectors.counting()));
    }

    /**
     * Find the top maintainers in terms of closed tickets.
     * 
     * The elements are strings formatted as <code>"username:###"</code> where <code>username</code> 
     * is the user name and <code>###</code> is the number of closed tickets. 
     * The list is sorter by descending number of closed tickets and then by username.

     * @return A list of strings with the top maintainers.
     */
    public List<String> topMaintainers(){
        return tickets.values().stream()
        		.filter(t->t.getState()==Ticket.State.Closed)
        		.collect(Collectors.collectingAndThen(Collectors.groupingBy(Ticket::getMaintainerName,
        				HashMap::new,
        				Collectors.counting()),
        				(Map<String,Long> m) -> m.entrySet().stream()
        					.sorted(Comparator.comparing((Map.Entry<String, Long> e) -> e.getValue() ).reversed()
        							.thenComparing(Comparator.comparing((Map.Entry<String, Long> e) -> e.getKey())) )
        					.map((Map.Entry<String, Long> e) -> e.getKey()+":"+String.format("%3d", e.getValue()))
        					.collect(Collectors.toList())
        		));
    }

}
