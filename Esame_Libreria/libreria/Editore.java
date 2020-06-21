package libreria;

public class Editore {
	private String nome;
	private int avgConsegna;
	private String email;
    
    public Editore(String nome, int avgConsegna, String email) {
		this.nome = nome;
		this.avgConsegna = avgConsegna;
		this.email = email;
	}

	public String getNome(){
        return nome;
    }
    
    public int getTempoConsegna(){
        return avgConsegna;
    }
    
    public String getEmail(){
        return email;
    }
 
}
