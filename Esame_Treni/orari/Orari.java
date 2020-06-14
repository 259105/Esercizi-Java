package orari;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Orari {
	private Map<String,Percorso> percorsi=new HashMap<>();
	private Set<Treno> treni=new HashSet<>();
	
  public Percorso creaPercorso(String codice, String categoria) {
	  Percorso p =new Percorso(codice,categoria);
	  percorsi.put(codice, p);
	  return p;
  }

  public Collection<Percorso> getPercorsi() {
    return percorsi.values();
  }

  public Percorso getPercorso(String codice) {
    return percorsi.get(codice);
  }

  public Treno nuovoTreno(String codice, int giorno, int mese, int anno) 
  	throws PercorsoNonValido {
	  if(!percorsi.containsKey(codice))
		  throw new PercorsoNonValido();
	  Percorso p=percorsi.get(codice);
	  Data d=new Data(anno,mese,giorno);
	  Treno t=new Treno(p,d);
	  p.addTreno(t);
	  treni.add(t);
	  return t;
  }

  public List<Treno> getTreni() {
    return treni.stream().sorted(Comparator.comparing(Treno::getData).reversed()).collect(Collectors.toList());
  }

}
