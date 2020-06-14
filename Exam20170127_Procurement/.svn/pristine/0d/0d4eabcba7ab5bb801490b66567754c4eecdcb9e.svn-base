package warehouse;

public class Order {
	private int code;
	private Product product;
	private Supplier supplier;
	private int quantity;
	private boolean delivered=false;
	
	public Order(int code, Product product, Supplier supplier, int quantity) {
		super();
		this.code = code;
		this.product = product;
		this.supplier = supplier;
		this.quantity = quantity;
	}

	public String getCode(){
		return "ORD"+code;
	}
	
	public String getCodeProduct() {
		return product.getCode();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getNomeSupplier() {
		return supplier.getNome();
	}
	
	public boolean delivered(){
		return delivered;
	}

	public void setDelivered() throws MultipleDelivery {
		if(delivered==true)
			throw new MultipleDelivery();
		delivered=true;
		product.setQuantity(product.getQuantity()+quantity);
	}
	
	public String toString(){
		return "Order ORD"+code+" for "+quantity+" of "+product.getCode()+" : "
				+product.getDescription()+" from "+supplier.getNome();
	}
}
