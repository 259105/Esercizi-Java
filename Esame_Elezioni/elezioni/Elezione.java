package elezioni;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Elezione {
	private Map<String,Cittadino> elettori=new HashMap<>(); 
	private Map<String,Lista> liste=new HashMap<>();

	public Elezione(){
		
	}
	
	public Cittadino aggiungiElettore(String nome, String cognome){
		Cittadino c =new Elettore(nome,cognome);
		elettori.put(cognome+":"+nome,c);
		return c;
	}
	
	public Collection<Cittadino> getElettori(){
		return elettori.values();
	}
	
	public Cittadino getElettore(String nome, String cognome){
		return elettori.get(cognome+":"+nome);
	}
	
	public Collection<Lista> getListe(){
		return liste.values();
	}
	
	public void registraLista(Lista lista){
		liste.put(lista.getNome(), lista);
		lista.assegnaElezione(this);
	}

    /**
     * Il cittadino votante esprime un voto per la lista ed 
     * un voto di preferenza per il candidato identificato
     * da nome e cognome
     * @throws TentatoDoppioVoto se il cittadino ha già votato
     * @throws TaglioNonPermesso se il candidato per cui si esprime
     * 							la preferenza non appartiene alla lista
     */	
	public void vota(Cittadino votante, String lista, String nome, String cognome)
		throws TentatoDoppioVoto, TaglioNonPermesso{
		if(votante.haVotato())
			throw new TentatoDoppioVoto();
		Lista l =liste.get(lista);
		Elettore c=(Elettore) elettori.get(cognome+":"+nome);
		if(!l.getCandidati().contains(c) && !l.getCapolista().equals(c))
			throw new TaglioNonPermesso();
		Elettore e=(Elettore)votante;
		e.setVotato(true);
		l.addVoto();
		c.addvoto();
	}

	/**
	 * Il cittadino votante esprime un voto per la lista
	 * il voto di preferenza va automaticamente al capolista
	 * @throws TentatoDoppioVoto se il cittadino ha già votato
	 */	
	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
		if(votante.haVotato())
			throw new TentatoDoppioVoto();
		Lista l =liste.get(lista);
		Elettore e=(Elettore)votante;
		Elettore c = (Elettore)l.getCapolista();
		e.setVotato(true);
		l.addVoto();
		c.addvoto();
	}
	
	public long getNumeroVotanti(){
		return elettori.values().stream()
				.filter(c->c.haVotato())
				.count();
	}
	
	public Collection<Lista> getRisultatiListe(){
		return liste.values().stream()
				.sorted(Comparator.comparing(Lista::getNumeroVoti).reversed())
				.collect(Collectors.toList());
	}

	public Collection<Cittadino> getRisultatiCandidati(){
		return elettori.values().stream()
				.filter(Cittadino::isCandidato)
				.sorted(Comparator.comparing(Cittadino::getNumeroVoti).reversed())
				.collect(Collectors.toList());
	}
	
	
}
