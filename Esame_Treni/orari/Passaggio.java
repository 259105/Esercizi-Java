package orari;


public class Passaggio {
	private String stazione;
	private Orario orario;
	private int ritardo;

  public Passaggio(String stazione, Orario orario) {
		super();
		this.stazione = stazione;
		this.orario = orario;
	}

public String getStazione() {
    return stazione;
  }

  public int getOra() {
   return orario.ore;
  }

  public int getMinuti() {
    return orario.minuti;
  }
  
  public Orario getOrario() {
	  return orario;
  }

  public int ritardo() {
    return -ritardo;
  }
  
  public void setRitardo(int ritardo) {
	  this.ritardo=ritardo;
  }

}
