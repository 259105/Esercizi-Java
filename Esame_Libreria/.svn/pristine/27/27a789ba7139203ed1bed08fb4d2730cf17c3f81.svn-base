import java.util.Collection;

import libreria.Editore;
import libreria.EditoreInesistente;
import libreria.Libreria;
import libreria.Libro;


public class Esempio {

    public static void main(String[] args) throws EditoreInesistente {
        
        Libreria lib = new Libreria();
        
        Editore a = lib.creaEditore("Adelphi",5,"ordini@adlephi.it");
        Editore b = lib.creaEditore("Mondadori",10,"richieste@mondadori.it");
        
        Libro l1 = lib.creaLibro("Siddartha","Hesse",2000,5.5,"Adelphi");
        Libro l2 = lib.creaLibro("Promessi Sposi","Manzoni",1995,10.2,"Mondadori");
        
        l1.setParametri(8,10);

        l1.setQuantita(10);
        l2.setQuantita(15);
        
        l1.registraVendita(1,1);
        l2.registraVendita(1,1);
        l2.registraVendita(1,1);

        l1.registraVendita(3,1);
        l1.registraVendita(4,1);
        l1.registraVendita(4,1);
        
        Collection sett1=lib.getClassificaSettimana(1);
        Collection sett4=lib.getClassificaSettimana(4);
        Collection mes1=lib.getClassificaMese(1);
        System.out.println("Sett 1: " + sett1);
        System.out.println("Sett 4: " + sett4);
        System.out.println("Mese 1: " + mes1);
        
        System.out.println("Ordini: " + lib.getOrdini());
        
    }
}
