package orari;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Treno {
	private Percorso percorso;
	private Data data;
	private Map<String,Passaggio> passaggi=new HashMap<>();
	
  public Treno(Percorso percorso, Data data) {
		super();
		this.percorso = percorso;
		this.data = data;
	}

public Percorso getPercorso() {
    return percorso;
  }

  public int getGiorno() {
    return data.giorno;
  }

  public int getMese() {
    return data.mese;
  }

  public int getAnno() {
    return data.anno;
  }
  public Data getData() {
	  return data;
  }

  public Passaggio registraPassaggio(String string, int i, int j) 
  	throws StazioneNonValida {
    if(!percorso.getFermate().stream()
    		.map(Fermata::getStazione)
    		.collect(Collectors.toList())
    		.contains(string))
    	throw new StazioneNonValida();
    Orario o =new Orario(i,j);
    Passaggio p =new Passaggio(string,o);
    passaggi.put(string, p);
    Fermata fermata=null;
    for(Fermata f : percorso.getFermate())
    	if (f.getStazione().compareTo(string)==0) {
    		fermata=f;
    		break;
    	}
    int ritardo=fermata.getMinuti()+fermata.getOre()*60-(i*60+j);
    p.setRitardo(ritardo);
    return p;
  }

  public boolean arrivato() {
    return passaggi.containsKey(percorso.getFermate().getLast().getStazione());
  }

  public int ritardoMassimo() {
    return passaggi.values().stream()
    		.map(Passaggio::ritardo)
    		.max(Comparator.naturalOrder())
    		.get();
  }

  public int ritardoFinale() {
    return passaggi.get(percorso.getFermate().getLast().getStazione()).ritardo();
  }

}
