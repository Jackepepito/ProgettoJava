package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * In questa classe si � creata la connessione con il database
 *
 */
public class Database {
	public static Connection con = null;
	public static Statement st = null;
	
	/**
	 * Costruttore: carica il driver e apre una connessione al DB
	 */
	public Database (){
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:db.db");
		}
		catch (ClassNotFoundException e){
			System.out.println("Errore nel caricamento del driver");
		}
		catch (SQLException e){
			System.out.println("Errore di connessione ad DB");	
		}
		
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Errore nella creazione di uno Statement");
		}
		
	}
	/**
	 * Metodo per la costruzione delle tabelle 
	 */
	public void testInsert() {
        try{
        	// creare tabelle 
        st.executeUpdate("CREATE TABLE IF NOT EXISTS utente (username varchar(25) PRIMARY KEY, password varchar(30), "
        		+ "nome varchar(25), cognome varchar(25), squadra varchar(25), stagione varchar(25))");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS giocatore (nome VARCHAR(50) ,cognome varchar(50),numero INTEGER,ruolo varchar(20) primary key ,altezza_cm integer)");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS partita (avversario VARCHAR(50) , n_giornata integer primary key,golfatti integer, golsubiti integer,falli_commessi integer)");
       //mancano i marcatori della partita e gli eventuali vincoli e i default..
        }
		catch (SQLException e){ 
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per interagire con il DB ed eseguire delle query
	 * @param qry
	 * @return ResultSet
	 */
	public static ResultSet query(String qry){
		ResultSet rs = null;
	
		try {
			rs = st.executeQuery(qry);
		}
		catch (SQLException e){
			System.out.println("Errore nell'interazione con il DB (select)");	
		}
		return rs;		
	}
	/**
	 * Metodo per interagire con il DB ed eseguire delle operazioni di INSERT, UPDATE or DELETE
	 * @param qry
	 */
	public static void update(String qry){
		try {
			st.executeUpdate(qry);
		}
		catch (SQLException e){
			System.out.println("Errore nell'interazione con il DB (update)");	
		}
			
	}
	
	/**
	 * Metodo con cui si chiude la connessione al database
	 */
	public static void closeConnection() {
		if (st != null){
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("Errore nella chiusura dello statement");
			}
		}
		
		if (con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Errore nella chiusura della connessione ad DB");
			}
		}
	}


}