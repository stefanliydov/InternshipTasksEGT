package egt.interactive.tic_tac_toe.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBCredentials {
    private final static String CONFIG_PATH = "src/main/resources/config.properties";
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public DBCredentials() {
	final Properties prop = getCredentials();
	this.USER = prop.getProperty("dbuser");
	this.PASSWORD = prop.getProperty("dbpassword");
	final String db = prop.getProperty("database");
	final String port = prop.getProperty("port");

	this.URL = "jdbc:mysql://" + db + ":" + port + "/tic_tac_toe";
    }

    private Properties getCredentials() {
	final Properties prop = new Properties();

	try (InputStream input = new FileInputStream(CONFIG_PATH);) {
	    prop.load(input);
	    return prop;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public String getURL() {
	return URL;
    }

    public String getUSER() {
	return USER;
    }

    public String getPASSWORD() {
	return PASSWORD;
    }

}
