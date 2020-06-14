package delivery;

public class Menu {
	private String descrizione;
	private double prezzo;
	private String categoria;
	private int tempoPreparazione;
	public Menu(String descrizione, double prezzo, String categoria, int tempoPreparazione) {
		super();
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.categoria = categoria;
		this.tempoPreparazione = tempoPreparazione;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("[%s] %s : %.2f", categoria,descrizione,prezzo);
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}
	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @return the tempoPreparazione
	 */
	public int getTempoPreparazione() {
		return tempoPreparazione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @param tempoPreparazione the tempoPreparazione to set
	 */
	public void setTempoPreparazione(int tempoPreparazione) {
		this.tempoPreparazione = tempoPreparazione;
	}
}
