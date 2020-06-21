package phonebook;
//questa classe potrebbe essere annidata a phonebook
class Contatto {//visibilità di package
	private String nome;
	private String cognome;
	private String telefono;
	private Contatto next;
	
	Contatto(String nome,String cognome,String telefono,Contatto next){
		this.nome=nome;
		this.cognome=cognome;
		this.telefono=telefono;
		this.next=next;
	}
	
	Contatto getNext(){
		return next;
	}
	
	Contatto addTail(String first, String last, String number) {
		next=new Contatto(first,last,number,null);
		return next;
	}
	
	public String CtoString() {
		return String.format("%s %s: %s",nome,cognome,telefono);
	}
	
	String findContattoByIndex(Contatto next,int index,int count) {
		//System.out.println(index);
		if(index==count)
			return CtoString();
		return next.findContattoByIndex(next.getNext(),index+1,count);
	}
	
	StringBuffer BooktoString(Contatto next,StringBuffer s,int i,int C) {
		if(getNext()==null)
			return s.append("( "+CtoString()+", ");;
		s=next.BooktoString(next.next,s,i-1,C);
		if(i==C)
			s.append(CtoString()+" )");
		else
			s.append(CtoString()+", ");
		return s;
	}
	
	String findContattoByNeedle(Contatto next,String needle) {
		if(this==null)
			return null;
		if(nome.contains(needle) || cognome.contains(needle) || telefono.contains(needle))
			return CtoString();
		return next.findContattoByNeedle(next.next, needle);
	}
}
