package database.hibernate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatabaseConfig {
	
	
	public DatabaseConfig(String connectionURL, String username, String password) {
		super();
		this.connectionURL = connectionURL.trim();
		this.username = username.trim();
		this.password = password.trim();
	}


	private static final String CONFIG_FILENAME="database_config.json";
	private static final String URL_KEY="url";
	private static final String USERNAME_KEY="username";
	private static final String PASSWORD_KEY="password";
	
	private String connectionURL="";
	private String username="";
	private String password="";
	
/**
 * Default constructor reads the config data from the json data file
 */
	public DatabaseConfig() {
		loadConfig();
	}
	
	
	@SuppressWarnings("unchecked")
	public void writeConfig(String connectionURL,String username,String password) {
		JSONObject databaseConfig = new JSONObject();
		databaseConfig.put(URL_KEY,connectionURL);
		databaseConfig.put(USERNAME_KEY,username);
		databaseConfig.put(PASSWORD_KEY,password);
		//Write JSON file
        try (FileWriter file = new FileWriter(CONFIG_FILENAME)) {
 
            file.write(databaseConfig.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Configuration getHibernateConfig() {
		Configuration config = new Configuration();
		config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		config.setProperty("hibernate.connection.url",connectionURL);
		config.setProperty("hibernate.connection.username",username);
		config.setProperty("hibernate.connection.password",password);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
		config.setProperty("hibernate.show_sql","true");
		config.setProperty("hibernate.hbm2ddl.auto","update");
		config.addAnnotatedClass(database.Postcode.class);
		config.addAnnotatedClass(database.Address.class);
		config.addAnnotatedClass(database.PersonMultipleAddress.class);
	//	config.addAnnotatedClass(database.Doctor.class);		
		return(config);
	}
	private void loadConfig() {
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(CONFIG_FILENAME))
        {
            //Read JSON file
            JSONObject config = (JSONObject) jsonParser.parse(reader);
            
            System.out.println("Reading object object is "+config);
            connectionURL=((String)config.get(URL_KEY)).trim();
            username=((String)config.get(USERNAME_KEY)).trim();
            password=((String)config.get(PASSWORD_KEY)).trim();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getConnectionURL() {
		return connectionURL;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}
	

}
