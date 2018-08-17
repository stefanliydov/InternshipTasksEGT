package egt.interactive.tic_tac_toe.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {

    public static Connection getConnection(final DBCredentials credentials) {
	try {
	    return DriverManager.getConnection(credentials.getURL(), credentials.getUSER(), credentials.getPASSWORD());
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

}
