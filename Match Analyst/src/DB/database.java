package DB;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	public static Connection connessione = null;
	public static Statement st = null;
}

public database()
{
	try {
		Class.forName("org.sqlite.JDBC");
		connessione = DriverManager.getConnection("jdbc:sqlite:database.db");
	}
	catch (ClassNotFoundException e){
		System.out.println("Errore nel caricamento del driver");
	}
	catch (SQLException e){
		System.out.println("Errore di connessione ad DB");	
	}
	
	try {
		st = connessione.createStatement();
	} catch (SQLException e) {
		System.out.println("Errore nella creazione di uno Statement");
	}
	
}
}
