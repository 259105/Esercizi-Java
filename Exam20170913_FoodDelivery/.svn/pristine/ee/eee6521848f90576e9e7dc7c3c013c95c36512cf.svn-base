package delivery;

public class Item {
	private int orderId;
	private Menu menu;
	private int qty;
	public Item(int orderId, Menu menu, int qty) {
		super();
		this.orderId = orderId;
		this.menu=menu;
		this.qty = qty;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty =this.qty+qty;
	}
	public String getDescrizione() {
		return menu.getDescrizione();
	}
	public double getPrezzo() {
		return menu.getPrezzo()*qty;
	}
	public int getTempo() {
		return menu.getTempoPreparazione();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return menu.getDescrizione()+", "+qty;
	}
}
