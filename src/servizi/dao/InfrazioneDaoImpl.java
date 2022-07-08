package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.LoggerFactory;

import dati.Auto;
import dati.Infrazione;
import servizi.ConnectionFactory;

public class InfrazioneDaoImpl implements InfrazioneDao {
	private static final String DELETE_INFRAZIONE = "delete from infrazione where id=";
	private static final String STAMPA_INFRAZIONE = "select a.targa, a.marca, a.modello,"
			+ " i.tipo, i.id_auto, i.importo, i.data"
			+ "	from multe.infrazione as i"
			+ "	inner join multe.auto as a"
			+ "	on a.id=i.id_auto"
			+ "	where a.targa=?;";
	private static final String INSERISCI_INFRAZIONE = "insert into multe.infrazione(" 
			+ "data, tipo, importo, id_auto)" 
			+ "values (?,?,?,?);";
	private static org.slf4j.Logger Lg = LoggerFactory.getLogger(AutoDaoImpl.class);

	// metodo per inserire una nuova infrazione nel db
	@Override
	public boolean inserisciInfrazione(Infrazione infrazione) {

		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		int i = 0;
		try {
			// usiamo il preparedStatement
			ps = conn.prepareStatement(INSERISCI_INFRAZIONE);

			// segnaposto n 1 per la data, n2 per il tipo , n3 per 'importo, n4 per id
			// dell'auto associata all'infrazione

			ps.setDate(1, infrazione.getData());
			ps.setString(2, infrazione.getTipo());
			ps.setDouble(3, infrazione.getImporto());
			ps.setInt(4, infrazione.getId_auto());

			i = ps.executeUpdate();
			Lg.info("Infrazione inserita con successo");
		} catch (

		Exception e) {
			Lg.error("Errore nell'inserimento", e);
		}
		// chiusura della connessione
		try {
			conn.close();
		} catch (Exception e) {
		}

		return i>0;
	}

	@Override
	public void stampaDatiInfrazioneAuto(String targa) {
		Connection conn= ConnectionFactory.getConnection();
		PreparedStatement ps= null;
		ResultSet rs=null;
		Auto auto= null;
		String infrazioneAuto= "Info infrazione per la macchina selezionata:\n"
				+ "targa	marca		modello		tipo 		importo		data\n";
		try {
			ps=conn.prepareStatement(STAMPA_INFRAZIONE);
			ps.setString(1, targa);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Infrazione i= new Infrazione(rs.getDate("data"),
						rs.getString("tipo"),
						rs.getFloat("importo"),
						rs.getInt("id_auto"));
				Auto a= new Auto(rs.getString("targa"),
						rs.getString("marca"),
						rs.getString("modello"));
				Lg.info("{} -> {}", a, i);
			}
			
		} catch (Exception e) {
			Lg.error("errore nella ricerca delle infrazioni per questa auto");
			e.printStackTrace();
		}
		try {ps.close();} catch (Exception e) {}
		try {rs.close();} catch (Exception e) {}
		try {conn.close();} catch (Exception e) {}
	}

	@Override
	public boolean eliminaInfrazione(int id) {
		Connection conn= ConnectionFactory.getConnection();
		Statement st=null;
		int i=0;
		try {
			st=conn.createStatement();
			i=st.executeUpdate(DELETE_INFRAZIONE+id);
			Lg.info("Infrazione eliminata con successo");
		} catch (Exception e) {
			Lg.error("Si e' verificato un errore nella cancellazione dell'infrazione");
			e.printStackTrace();
		}
		
		try{conn.close();}catch(Exception e){}
		
		return i>0;
	}

}
