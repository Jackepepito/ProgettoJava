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
   
        st.executeUpdate("CREATE TABLE IF NOT EXISTS utente (username varchar(25) PRIMARY KEY, password varchar(30), "
        		+ "nome varchar(25), cognome varchar(25), squadra varchar(25), stagione varchar(25))");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS giocatore (numero INTEGER primary key,nome VARCHAR(50), ruolo varchar(20), gol INTEGER, assist INTEGER,golsubiti INTEGER)");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS partita (avversario VARCHAR(50), casa_trasferta varchar(20), gol_segnati INTEGER, gol_subiti INTEGER, "
        		+ "possesso_palla INTEGER, tiri_tot INTEGER ,tiri_porta INTEGER, falli_commessi INTEGER, falli_subiti INTEGER, parate INTEGER, PRIMARY KEY (avversario, casa_trasferta))");
        st.executeUpdate("CREATE TABLE IF NOT EXISTS marcatore (partita varchar(50) references partita(avversario), casa_trasferta varchar(20) references partita(casa_trasferta), giocatore VARCHAR(50) references giocatore(nome), gol INTEGER, PRIMARY KEY (partita, casa_trasferta))");
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
	public ResultSet query(String qry){
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
	public void update(String qry){
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
	public void closeConnection() {
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
