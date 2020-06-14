package orari;

public class Data implements Comparable<Data> {
	int anno,mese,giorno;

	public Data(int anno, int mese, int giorno) {
		super();
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
	}

	@Override
	public int compareTo(Data o) {
		int aa=this.anno-o.anno;
		if(aa!=0)
			return aa;
		int mm=this.mese-o.mese;
		if(mm!=0)
			return mm;
		return this.giorno-o.giorno;
	}
	
}
