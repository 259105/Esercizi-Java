package groups;

import java.util.HashMap;
import java.util.Map;

public class Bid {
	private Group group;
	private Supplier supplier;
	private int price;
	private Map<String,Customer> votes=new HashMap<>();
	
	public Bid(Group group, Supplier supplier, int price) {
		super();
		this.group = group;
		this.supplier = supplier;
		this.price = price;
	}
	
	public String getSupplierName() {
		return supplier.getName();
	}
	public int getPrice() {
		return price;
	}
	public String toStringSupplierPrice() {
		return supplier.getName()+":"+price;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	
	public int getNumVotes() {
		return votes.size();
	}

	public void addVote(Customer c) {
		votes.put(c.getName(), c);
	}
}
