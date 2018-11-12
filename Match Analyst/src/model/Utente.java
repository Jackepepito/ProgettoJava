package model;


	import java.sql.*;

	/**
	 * Questa classe rappresenta l'utente che ha effettuato il login e contiene tutte le sue informazioni 
	 *
	 */
	public class Utente {
		
	private String username;
	private String squadra;
	private String nome;
	private String cognome;
	    
		/**
		 * Costruttore che serve per inserire le informazione nell'utente
		 * @param username
		 */
		public Utente(String username){
			this.username=username;
			ResultSet rs = Database.query("SELECT * from Utente where username = '" +this.username+ "'");
			try {			
				this.squadra=rs.getString("squadra");
				this.nome=rs.getString("nome");
				this.cognome=rs.getString("cognome");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public String getUserName(){
			return username;
		}
		
		public String getSquadra(){
			return squadra;
		}
		
		public String getNome(){
			return nome;
		}
		
		public String getCognome(){
			return cognome;
		}
		
		public void setSquadra(String squadra){
			this.squadra=squadra;
		}
	}
	