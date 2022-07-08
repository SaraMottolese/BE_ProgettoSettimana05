package servizi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;

import dati.Auto;
import servizi.ConnectionFactory;

public class AutoDaoImpl implements AutoDao {
	//query per cercare un'auto specifica
	private static final String SELECT_AUTO = "select * from auto where targa=?";
	
	//query per cercare tutte le auto presenti nel db
	private static final String ALL_AUTO = "select * from auto";
	
	//query per creare nuova auto
	private static final String NUOVA_AUTO = "insert into multe.auto (targa, marca, modello) "
			+ "values (?,?,?);";
	
	private static org.slf4j.Logger Lg= LoggerFactory.getLogger(AutoDaoImpl.class);
	
	
	//metodo per inserire una nuova auto nel db
	@Override
	public boolean inserisciAuto(Auto auto) {
		Connection conn= ConnectionFactory.getConnection();
		PreparedStatement ps= null;
		int i=0;
		try {
			// usiamo il preparedStatement
			ps= conn.prepareStatement(NUOVA_AUTO);
			//segnaposto n 1 per la taga, n2 per la marca, n3 per il modello
			ps.setString(1, auto.getTarga());
			ps.setString(2, auto.getMarca());
			ps.setString(3, auto.getModello());
			
			i= ps.executeUpdate();
			Lg.info("Auto inserita correttamente");
		} catch (Exception e) {
			Lg.error("Errore nell'inserimento", e);
		}
		
		//chiusura della connessione
		try {conn.close();}catch(Exception e){}
		
		return i>0;
		
	}
	
	
	//metodo per avere la lista di auto all'interno del nostro db
	@Override
	public ArrayList <Auto> gettAllAuto() {
		Connection conn= ConnectionFactory.getConnection();
		
		//usiamo lo Statement, abbiamo bisogno del ResultSet
		Statement st= null;
		ResultSet rs= null;
		ArrayList<Auto> listaAuto=null;
		
		try {
			st= conn.createStatement();
			rs=st.executeQuery(ALL_AUTO);
			listaAuto= new ArrayList<Auto>();
			
			while (rs.next()) {
				Auto auto= new Auto(rs.getInt("id"),
						rs.getString("targa"),
						rs.getString("marca"),
						rs.getString("modello"));
				listaAuto.add(auto);
			}
		} catch (Exception e) {
			Lg.error("errore nel recupero della lista delle auto");
			e.printStackTrace();
		}
		
		//chiusura della connessione e rilascio dei dati
		try {rs.close();} catch (Exception e) {}
		try {conn.close();} catch (Exception e) {}
		
		return listaAuto;
	}
	
	@Override
	public Auto cercaAuto(String targa) {
		Connection conn= ConnectionFactory.getConnection();
		PreparedStatement ps= null;
		ResultSet rs= null;
		Auto auto=null;
		try {
			ps=conn.prepareStatement(SELECT_AUTO);
			ps.setString(1, targa);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				auto= new Auto(rs.getInt("id"),
						rs.getString("targa"),
						rs.getString("marca"),
						rs.getString("modello"));
			}
			Lg.info("Auto trovata: ");
		} catch (Exception e) {
			Lg.error("Errore nella ricerca dell'auto");
			e.printStackTrace();
		}
		try {ps.close();} catch (Exception e) {}
		try {rs.close();} catch (Exception e) {}
		try {conn.close();} catch (Exception e) {}
		
		return auto;
	}

}
