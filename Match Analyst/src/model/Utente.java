package model;


	import java.sql.*;

	/**
	 * Questa classe rappresenta l'utente che ha effettuato il login e contiene tutte le sue informazioni 
	 *
	 */
	public class Utente {
		
	private String username;
	private String squadra;
	
	    
		/**
		 * Costruttore che serve per inserire le informazione nell'utente
		 * @param username
		 */
		public Utente(String username){
			this.username=username;
			ResultSet rs = Database.query("SELECT * from Utente where username = '" +this.username+ "'");
			try {			
				this.squadra=rs.getString("squadra");
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
		
		
		public void setSquadra(String squadra){
			this.squadra=squadra;
		}
	}
	