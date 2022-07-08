package servizi;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
	
	private static final String URL= "jdbc:postgresql://localhost:5432/multedb?currentSchema=multe";
	private static final String  USER= "postgres";
	private static final String PASS= "Leoncino";
	private static Logger Lg= LoggerFactory.getLogger(ConnectionFactory.class);
	
	public static Connection getConnection() {
		Connection conn= null;
		
		try {
			conn=DriverManager.getConnection(URL,USER,PASS);
			Lg.info("connesso");
			
		} catch (Exception e) {
			Lg.info("connessione non riuscita");
		}
		return conn;
	}
	
	

}
