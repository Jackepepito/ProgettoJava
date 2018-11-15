package model;

public class Giocatore {
	private int giocatoreNumero;
	private String giocatoreNome;
	private String giocatoreCognome;
	private String giocatoreRuolo;
	private int giocatoreGol;
	private int giocatoreAssist;
	private int giocatoreGolsubiti;
	private String squadra;
	
	
	
	public Giocatore(Utente user) {
		this.giocatoreNumero = 0;
		this.giocatoreNome = "";
		this.giocatoreCognome = "";
		this.giocatoreRuolo = "";
		this.giocatoreGol= 0;
		this.giocatoreAssist = 0;
		this.giocatoreGolsubiti =0;
		this.setSquadra(user.getSquadra());
	}



	public Giocatore(int giocatoreNumero, String giocatoreNome, String giocatoreCognome, String giocatoreRuolo,
			int giocatoreGol,int giocatoreAssist, int giocatoreGolsubiti,Utente user) {
		this.giocatoreNumero = giocatoreNumero;
		this.giocatoreNome = giocatoreNome;
		this.giocatoreCognome = giocatoreCognome;
		this.giocatoreRuolo = giocatoreRuolo;
		this.giocatoreGol= giocatoreGol;
		this.giocatoreAssist = giocatoreAssist;
		this.giocatoreGolsubiti = giocatoreGolsubiti;
		this.setSquadra(user.getSquadra());
	}



	public int getGiocatoreNumero() {
		return giocatoreNumero;
	}



	public void setGiocatoreNumero(int giocatoreNumero) {
		this.giocatoreNumero = giocatoreNumero;
	}



	public String getGiocatoreNome() {
		return giocatoreNome;
	}



	public void setGiocatoreNome(String giocatoreNome) {
		this.giocatoreNome = giocatoreNome;
	}



	public String getGiocatoreCognome() {
		return giocatoreCognome;
	}



	public void setGiocatoreCognome(String giocatoreCognome) {
		this.giocatoreCognome = giocatoreCognome;
	}



	public String getGiocatoreRuolo() {
		return giocatoreRuolo;
	}



	public void setGiocatoreRuolo(String giocatoreRuolo) {
		this.giocatoreRuolo = giocatoreRuolo;
	}

	public int getGiocatoreGol() {
		return giocatoreGol;
	}



	public void setGiocatoreGol(int giocatoreGol) {
		this.giocatoreGol = giocatoreGol;
	}


	public int getGiocatoreAssist() {
		return giocatoreAssist;
	}



	public void setGiocatoreAssist(int giocatoreAssist) {
		this.giocatoreAssist = giocatoreAssist;
	}



	public int getGiocatoreGolsubiti() {
		return giocatoreGolsubiti;
	}



	public void setGiocatoreGolsubiti(int giocatoreGolsubiti) {
		this.giocatoreGolsubiti = giocatoreGolsubiti;
	}
	
	

	public String getSquadra() {
		return squadra;
	}



	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}
	
}
