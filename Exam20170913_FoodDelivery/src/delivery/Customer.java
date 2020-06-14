package delivery;

public class Customer {
	private int id;
	private String nome;
	private String indirizzo;
	private String cell;
	private String email;
	public Customer(String nome, String indirizzo, String cell, String email,int Id) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.cell = cell;
		this.email = email;
		this.id=Id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nome+", "+indirizzo+", "+cell+", "+email;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	/**
	 * @return the cell
	 */
	public String getCell() {
		return cell;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	/**
	 * @param cell the cell to set
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
