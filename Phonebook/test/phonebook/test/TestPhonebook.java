package phonebook.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phonebook.PhoneBook;

public class TestPhonebook {
	
	private PhoneBook rubrica;
	
	@Before
	public void setUp() {
		//FASE 1:(SETUP) si creano oggetti
		rubrica = new PhoneBook("Titolo");
	
		//ATTENZIONE ai side-effect, in particolare gli attriubti 'static'
	}
	
	@Test
	public void test0() {
		//FASE 2:(RISULTATI)si ottengono dei risultati
		String titolo=rubrica.getName();
		
		//FASE 3:(VERIFICA) si verifica la corrispondenza dei risultati con le aspettative
		assertEquals("Titolo",//Valore atteso
					titolo); //Valore effettivo
	}
	
	@Test
	public void test1() {
		//FASE 1.1: si eseguono operazioni
		rubrica.add("Lara", "Rossi", "333 4445556");
		
		//FASE 2:(RISULTATI)si ottengono dei risultati
		String primo=rubrica.first();
		
		//FASE 3:(VERIFICA) si verifica la corrispondenza dei risultati con le aspettative
		assertNotNull(primo);
		
		assertEquals("Lara Rossi: 333 4445556",primo);
		
	}

}
