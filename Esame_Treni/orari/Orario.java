package orari;

public class Orario implements Comparable<Orario>{
	int ore;
	int minuti;
	
	public Orario(int ore, int minuti) {
		super();
		this.ore = ore;
		this.minuti = minuti;
	}

	@Override
	public int compareTo(Orario o) {
		int or=this.ore-o.ore;
		if(or!=0)
			return or;
		return this.minuti-o.minuti;
	}
}
