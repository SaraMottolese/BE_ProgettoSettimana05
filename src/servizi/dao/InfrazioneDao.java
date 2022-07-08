package servizi.dao;

import dati.Infrazione;

public interface InfrazioneDao {

	// metodo per inserire una nuova infrazione nel db
	boolean inserisciInfrazione(Infrazione infrazione);

	void stampaDatiInfrazioneAuto(String targa);

	boolean eliminaInfrazione(int id);

}