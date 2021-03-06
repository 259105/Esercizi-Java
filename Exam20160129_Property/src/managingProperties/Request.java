package managingProperties;

public class Request {
	private Owner owner;
	private Appartment appartment;
	private Profession profession;
	private String professionist;
	private int state=0;
	private int nReq;
	private int amount=0;
	public Request(Owner owner, Appartment appartment, Profession profession, int nReq) {
		super();
		this.owner = owner;
		this.appartment = appartment;
		this.profession = profession;
		this.nReq= nReq;
	}
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @return the appartment
	 */
	public Appartment getAppartment() {
		return appartment;
	}
	/**
	 * @return the profession
	 */
	public Profession getProfession() {
		return profession;
	}
	/**
	 * @return the pending
	 */
	public boolean isPending() {
		return state==0?true:false;
	}
	/**
	 * @return the nReq
	 */
	public int getnReq() {
		return nReq;
	}
	public void toAssigned() {
		state=1;
	}
	public boolean isAssigned() {
		return state==1?true:false;
	}
	public void toCompleted() {
		state=2;
	}
	public boolean isCompleted() {
		return state==2?true:false;
	}
	
	public void setAmount(int amount) {
		this.amount=amount;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	public String getProfessionist() {
		return professionist;
	}
	public void setProfessionist(String professionist) {
		this.professionist = professionist;
	}
}
