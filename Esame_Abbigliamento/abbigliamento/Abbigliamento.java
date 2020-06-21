package abbigliamento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Abbigliamento {
	private Map<String,Modello> modelli=new HashMap<>();
	private Map<String,Colore> colori=new HashMap<>();
	private Map<String,Materiale> materiali=new HashMap<>();
	private Map<String,Capo> capi=new HashMap<>();
	private Set<Collezione> collezioni=new HashSet<>();

	
	
	public void leggiFile(String fileName){
		try(BufferedReader in=new BufferedReader(new FileReader(fileName))) {
			List<String> righe=in.lines().collect(Collectors.toList());
			for(String riga:righe) {
				String[] celle=riga.split(";");
				if(celle[0].equals("MOD")) {
					modelli.put(celle[1], new Modello(celle[1],Double.parseDouble(celle[2]),Double.parseDouble(celle[3])));
				}else if(celle[0].equals("COL")) {
					if(celle.length==2) {
						colori.put(celle[1], new Colore(celle[1]));
					}else {
						Collezione col=new Collezione();
						for(int i=1;i<celle.length;i++)
							col.add(capi.get(celle[i]));
						collezioni.add(col);
					}
				}else if(celle[0].equals("MAT")) {
					materiali.put(celle[1], new Materiale(celle[1],Double.parseDouble(celle[2])));
				}else if(celle[0].equals("CAP")) {
					capi.put(celle[1], new Capo(celle[1],
							modelli.get(celle[2]),
							materiali.get(celle[3]),
							colori.get(celle[4])));
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Errore file non trovato");
			e.printStackTrace();
		} catch (IOException e1) {
			System.err.println("Errore chiusura file");
			e1.printStackTrace();
		}
	}

	public Modello getModello(String name){
		return modelli.get(name);
	}

	public Colore getColore(String name){
		return colori.get(name);
	}

	public Materiale getMateriale(String name){
		return materiali.get(name);
	}

	public Capo getCapo(String name){
		return capi.get(name);
	}

	public Collezione getCollezione(String name){
		for(Collezione col:collezioni) {
			if(col.getCapi().contains(capi.get(name)))
				return col;
		}
		return null;
	}

}
