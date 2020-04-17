package phonebook;
/**
 * Represents a phonebook that contains
 * contact information entries.
 * 
 * Each entry includes first name, last name, 
 * and phone number.
 * 
 * Entries can be accessed by position using {@link #get(int)}
 * or searched by string match using {@link #find(String)}.
 *
 */
public class PhoneBook {

	private final String nome;
	private Contatto head;
	private Contatto tail;
	private int C;
	
  /**
   * Create a new phonebook with given name
   */
  public PhoneBook(String name) {  
	  this.nome=name;
  }

  /**
   * Return the phonebook name
   */
  public String getName() {
	  return nome;
  }

  /**
   * Insert a new contact at the end
   * 
   * @param first first name of the new contact
   * @param last  last name of the new contact
   * @param numner phone number of the contact
   */  
  public void add(String first, String last, String number){
	  System.out.println(1);
	  head=new Contatto(first,last,number,head);
	  if(C==0) tail=head;
	  C++;
  }

  /**
   * Return the first contact
   */  
  public String first() {
	  return tail.CtoString();
  }

  /**
   * Return the i-th contact (assume the first index is 1)
   * 
   * @param index index of the contact, starting at 1 
   */
  public String get(int index) {
	  //System.out.println(C);
	  if (index>C || index<=0) return "Contatto non presente.";
	  return head.findContattoByIndex(head.getNext(),1,C-(index-1));
  }

  /**
   * Return a string containing the list of textual 
   * representation of all contacts, separated by  {@code ", "}.
   * 
   * List starts with {@code "("} and ends with  {@code ")"} 
   */
  public StringBuffer toStringBuffer() {
	  StringBuffer s=new StringBuffer(); 
	  return head.BooktoString(head.getNext(),s,C,C);
  }

  /**
   * Return the textual representation of first
   * contact containing "needle"
   */
  public String find(String needle) {
	  String s=head.findContattoByNeedle(head.getNext(),needle);
	  if (s==null)
		  return "Non trovato.";
	  return s;
  }
  
}
