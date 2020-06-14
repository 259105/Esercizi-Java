package orari;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Percorso {
	private String codice;
	private String categoria;
	private boolean straordinario=false;
	private SortedMap<Orario,Fermata> fermate=new TreeMap<>();
	private Set<Treno> treni=new HashSet<>();

  public Percorso(String codice, String categoria) {
		super();
		this.codice = codice;
		this.categoria = categoria;
	}

  public String getCodice() {
    return codice;
  }

  public String getCategoria() {
    return categoria;
  }

  public boolean isStraordinario() {
    return straordinario;
  }

  public void setStraordinario(boolean b) {
    straordinario=b;
  }

  public Fermata aggiungiFermata(String nomeStazione, int ore, int minuti) {
    Orario orario=new Orario(ore,minuti);
	Fermata f=new Fermata(nomeStazione,orario);
    fermate.put(orario, f);
    return f;
  }

  public void addTreno(Treno treno) {
	  treni.add(treno);
  }
  public List<Treno> getTreni() {
	    return treni.stream().sorted(Comparator.comparing(Treno::getData).reversed()).collect(Collectors.toList());
	  }
  public LinkedList<Fermata> getFermate() {
    return fermate.values().stream()
    		.collect(Collectors.toCollection(LinkedList::new));
  }

  public int ritardoMassimo() {
    return treni.stream()
    		.filter(Treno::arrivato)
    		.map(Treno::ritardoFinale)
    		.max(Comparator.naturalOrder())
    		.get();
  }

  public int ritardoMedio() {
	double d =treni.stream()
    		.filter(Treno::arrivato)
    		.collect(Collectors.averagingInt(Treno::ritardoFinale));
	return (int) d;
  }

}
