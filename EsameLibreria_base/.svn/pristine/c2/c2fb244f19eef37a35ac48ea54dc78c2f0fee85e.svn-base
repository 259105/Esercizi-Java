package libreria;

public class Ordine implements Comparable<Ordine> {
	private static int nfatti=0;
	private Editore editore;
	private Libro libro;
	private int qta,numero;
	private boolean consegnato;
	
	public Ordine(Editore editore, Libro libro, int qta) {
		this.editore = editore;
		this.libro = libro;
		this.qta = qta;
		this.numero = nfatti+1;
		nfatti++;
		this.consegnato = false;
	}

	
    public Editore getEditore(){
        return editore;
    }
    
    public Libro getLibro(){
        return libro;
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ordine [editore=");
		builder.append(editore.getNome());
		builder.append(", libro=");
		builder.append(libro.getTitolo());
		builder.append(", qta=");
		builder.append(qta);
		builder.append(", numero=");
		builder.append(numero);
		builder.append(", consegnato=");
		builder.append(consegnato);
		builder.append("]");
		return builder.toString();
	}


	public int getQuantita(){
        return qta;
    }

    public boolean isConsegnato(){
        return consegnato;
    }
    public void setConsegnato(boolean consegnato) {
    	this.consegnato=consegnato;
    }

    public int getNumero(){
        return numero;
    }

	@Override
	public int compareTo(Ordine o) {
		// TODO Auto-generated method stub
		return numero-o.getNumero();
	}
}
