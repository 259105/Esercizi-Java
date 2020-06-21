package it.polito.oop.futsal;

public class Book {
	private Integer numfield;
	private Associate associate;
	private String time;
	public Book(Integer numfield, Associate associate, String time) {
		super();
		this.numfield = numfield;
		this.associate = associate;
		this.time = time;
	}
	/**
	 * @return the numfield
	 */
	public Integer getNumfield() {
		return numfield;
	}
	/**
	 * @return the associate
	 */
	public Associate getAssociate() {
		return associate;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
}
