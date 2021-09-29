package Connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static Connection connection;
	public ConnectionFactory() {}
	public static Connection getConnection() 
	{
		if(connection == null)
		{
			try
			{
				Properties props = new Properties();
				FileReader connectionProps = new FileReader("src/main/resources/connection.properties");
				props.load(connectionProps);
				
				String connectionString = "jdbc:mariadb://" + props.getProperty("endpoint") + ":" + 
				props.getProperty("port") + "/" +
				props.getProperty("dbname") + 
				"?user=" + props.getProperty("username") +
				"&password=" + props.getProperty("password");
				
				connection = DriverManager.getConnection(connectionString);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return connection;
	}

}
