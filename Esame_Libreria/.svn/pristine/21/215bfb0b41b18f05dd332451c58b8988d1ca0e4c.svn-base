package libreria;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Libreria {
	private SortedMap<String,Editore> editori=new TreeMap<>();
	private SortedMap<String,Libro> libri=new TreeMap<>();
	
    public Editore creaEditore(String nome, int tempoConsegna, String email){
    	Editore e=new Editore(nome,tempoConsegna,email);
    	editori.put(nome, e);
    	return e;	
    }

    public Editore getEditore(String nome){
        return editori.get(nome);
    }

    public Collection<Editore> getEditori(){
        return editori.values();
    }

    public Libro creaLibro(String titolo, String autore, int anno, double prezzo, String nomeEditore)
    			throws EditoreInesistente {
        if(!editori.containsKey(nomeEditore))
        	throw new EditoreInesistente();
        Libro l=new Libro(titolo,autore,anno,prezzo,editori.get(nomeEditore));
        String label=new String(titolo+":"+autore);
        libri.put(label, l);
        return l;
    }
    
    public Libro getLibro(String autore, String titolo){
    	if(titolo==null && autore==null) return null;
//    	for(String key:libri.keySet()) {
//    		String label[]=key.split(":");
//    		if(titolo!=null && titolo.equals(label[1]))
//    			return libri.get(key);
//    		if(autore!=null && autore.equals(label[0]))
//    			return libri.get(key);
//    		if(key.equals(titolo+":"+autore))
//    			return libri.get(key);
//    	}
    	for(Map.Entry<String,Libro> e:libri.entrySet()) {
    		String label[]=e.getKey().split(":");
    		if(titolo!=null && titolo.equals(label[0]))
    			return e.getValue();
    		if(autore!=null && autore.equals(label[1]))
    			return e.getValue();
    		if(e.getKey().equals(titolo+":"+autore))
    			return e.getValue();
    	}
    	return null;
    }
    
    public Collection<Libro> getClassificaSettimana(final int settimana){
        return libri.values().stream()
        		.filter(l -> l.maxVenditeSettimana(settimana)>0)
        		.sorted((l1,l2) -> -(l1.maxVenditeSettimana(settimana)-l2.maxVenditeSettimana(settimana)) )
        		.collect(Collectors.toList());
    }
    
    public Collection<Libro> getClassificaMese(final int mese){
        return libri.values().stream()
        		.filter(l -> l.maxVenditeMese(mese)>0)
        		.sorted((l1,l2) -> -(l1.maxVenditeMese(mese)-l2.maxVenditeMese(mese)) )
        		.collect(Collectors.toList());
    }
    
    public Collection<Ordine> getOrdini(){
        return libri.values().stream()
        		.map(Libro::getOrdini)//Ordini è una list di ordini
        		.flatMap(o -> o.stream())
        		.collect(Collectors.toList());
    }
    
//    TreeSet<Topic> all=this.theoryChaptersCollection
//    				.stram()
//    				.map(x-> x.getTopics())
//    				.flatMap(l -> l.stream())
//    				.collect(Collectors.toCollection(TreeSet::new));
    
    public void ordineRicevuto(int numOrdine){
    	Collection<Ordine> ordini=getOrdini();
    	for(Ordine o:ordini) {
    		if(o.getNumero()==numOrdine && !o.isConsegnato()) {
    				o.setConsegnato(true);
    				o.getLibro().setQuantita(o.getLibro().getQuantita()+o.getQuantita());
    		}
    	}
    } 
    
    public void leggi(String file){
    	List<String> righeFile=readData(file);
    	
    	for(String riga:righeFile) {
    		String[] cella=riga.split(";");
    		if(cella[0].matches("[E]") && cella.length==4) {
    			
    			try{
    				creaEditore(cella[1],Integer.parseInt(cella[2]),cella[3]);
    			}catch(NumberFormatException e) {
    				continue;
    			}
    		}else if(cella[0].matches("[L]") && cella.length==7) {
    			try {
					Libro l=creaLibro(cella[1],cella[2],Integer.parseInt(cella[3]),Double.parseDouble(cella[4]),cella[5]);
					l.setQuantita(Integer.parseInt(cella[6]));
				} catch (NumberFormatException e) {
					continue;
				} catch (EditoreInesistente e) {
					continue;
				}	
    		}
    	}
    }
    
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
