package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Course {
	private String nome;
	private int nPosti;
	private List<Student> studenti=new LinkedList<>();
	private List<Long> preferenze =new ArrayList<>(3);
	
	public Course(String nome, int nPosti) {
		super();
		this.nome = nome;
		this.nPosti = nPosti;
		for(int i=0;i<3;i++)
			preferenze.add(i, (long)0);
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return the nPosti
	 */
	public int getnPosti() {
		return nPosti;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @param nPosti the nPosti to set
	 */
	public void setnPosti(int nPosti) {
		this.nPosti = nPosti;
	}
	
	public void setPreferenze(int posizione) {
		preferenze.set(posizione-1,preferenze.get(posizione-1)+1);
	}
	public List<Long> getPreferenze(){
		return preferenze;
	}
	public void addStudent(Student s) {
		studenti.add(s);
	}
	public List<Student> getStudent(){
		return studenti;
	}
}
