package warehouse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Supplier {
	private String codice;
	private String nome;
	private Map<String,Product> supply =new HashMap<>();

	public Supplier(String codice, String nome) {
		super();
		this.codice = codice;
		this.nome = nome;
	}

	public String getCodice(){
		return codice;
	}

	public String getNome(){
		return nome;
	}
	
	public void newSupply(Product product){
		supply.put(product.getCode(), product);
		product.newSupplier(this);
	}
	
	public List<Product> supplies(){
		return supply.values().stream()
				.sorted(Comparator.comparing(Product::getDescription))
				.collect(Collectors.toList());
	}
}
