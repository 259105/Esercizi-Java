package libreria;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Libro {
	private String titolo,autore;
	private int anno,quantita;
	private double prezzo;
	private Editore editore;
	private Map<Integer,Map<Integer,Integer>> venditeSetMes=new TreeMap<>();
	private int soglia,qtaRiordino;
	private List<Ordine> ordini=new LinkedList<>();
	
    public Libro(String titolo, String autore, int anno, double prezzo, Editore editore) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno = anno;
		this.prezzo = prezzo;
		this.editore = editore;
	}

	public String getTitolo(){
        return titolo;
    }
    
    public String getAutore(){
        return autore;
    }
    
    public int getAnno(){
        return anno;
    }

    public double getPrezzo(){
        return prezzo;
    }
    
    public Editore getEditore(){
        return editore;
    }

    public void setQuantita(int q){   
    	quantita=q;
    }
    
    public int getQuantita(){
        return quantita;	
    }

    public void registraVendita(int settimana, int mese){
    	if(quantita==0) return;
    	if(venditeSetMes.containsKey(mese)) {
    		Map<Integer,Integer> mapMes=venditeSetMes.get(mese);
    		if(mapMes.containsKey(settimana)) {
    			mapMes.replace(settimana,mapMes.get(settimana)+1);
    			quantita--;
    			checkQta();
    			return;
    		}
    		mapMes.put(settimana, 1);
    		quantita--;
    		checkQta();
    		return;
    	}
    	Map<Integer,Integer> mapMes= new TreeMap<>();
    	mapMes.put(settimana, 1);
    	quantita--;
    	checkQta();
    	venditeSetMes.put(mese, mapMes);
    	return;
    }
    
    private void checkQta() {
    	if(quantita<=soglia) {
    		if(ordini.size()==0 || ordini.get(ordini.size()-1).isConsegnato())
    			ordini.add(new Ordine(editore,this,qtaRiordino));
    	}
    }
    
    public List<Ordine> getOrdini(){
    	return ordini;
    }
   
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(titolo);
		builder.append(" di ");
		builder.append(autore);
		builder.append("("+editore.getNome()+")");
		return builder.toString();
	}

	public int maxVenditeMese(int mese) {
		int max=0;
    	for(int i: venditeSetMes.get(mese).values()) {
    		max+=i;
    	}
    	return max;
    }
    public int maxVenditeSettimana(int settimana) {
    	for(Map.Entry<Integer,Map<Integer,Integer>> e:venditeSetMes.entrySet()) {
    		if(e.getValue().containsKey(settimana))
    			return e.getValue().get(settimana);
    	}
    	return 0;
    }

    public void setParametri(int soglia, int quantitaRiordino){
    	this.soglia=soglia;
    	this.qtaRiordino=quantitaRiordino;
    }
}
