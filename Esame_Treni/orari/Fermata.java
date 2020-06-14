package orari;


public class Fermata{
	private String stazione;
	private Orario orario;
  public Fermata(String stazione,Orario ora) {
		super();
		this.stazione = stazione;
		this.orario=ora;
	}

public String getStazione() {
   return stazione;
  }

  public int getOre() {
    return orario.ore;
  }

  public int getMinuti() {
    return orario.minuti;
  }

}