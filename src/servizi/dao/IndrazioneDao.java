package servizi.dao;

import dati.Infrazione;

public interface IndrazioneDao {

	// metodo per inserire una nuova infrazione nel db
	boolean inserisciInfrazione(Infrazione infrazione);

	void stampaDatiInfrazioneAuto(String targa);

	boolean eliminaInfrazione(int id);

}