package servizi.dao;

import java.util.ArrayList;

import dati.Auto;

public interface AutoDao {

	//metodo per inserire una nuova auto nel db
	boolean inserisciAuto(Auto auto);

	//metodo per avere la lista di auto all'interno del nostro db
	ArrayList<Auto> gettAllAuto();

	Auto cercaAuto(String targa);

}