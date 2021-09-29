package DAO;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Connection.ConnectionFactory;
import Exceptions.printSQLException;
import model.NewUser;
//"jdbc:mariadb://<RDS ENDPOINT FROM AWS RDS SERVICE>:<port>/<DATABASE NAME>?USER=<USER NAME>&password=<PASSWORD>"


public class NewUserDAO {
	//private static String jdbcURL = "jdbc:mariadb://revature-banking-project-0.cprymhiq2iiw.us-east-2.rds.amazonaws.com:3306/bankingAPI?USER=admin&password=DetroitandNewYork#1104";
	private static final String INSERT_USER_SQL ="INSERT INTO newUser (id,accountnumber,username, password,email, firstname, lastname) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_SQL = "SELECT * FROM newUser";
	private static final String SELECT_USER_BY_ID = "SELECT id,accountnumber,username,password,email,firstname, lastname FROM newUser WHERE id = ?";
	private static final String SELECT_USER_BY_NAME = "SELECT id,accountnumber,username,password,email,firstname, lastname FROM newUser WHERE name = ?";
	private static final String SELECT_USER_BY_ACCOUNT = "SELECT id,accountnumber,username,password,email,firstname, lastname FROM newUser WHERE accountnumber = ?";
	private static final String DELETE_USER_SQL = "DELETE FROM newUser WHERE id = ?";
	private static final String UPDATE_USER_SQL = "UPDATE newUser SET password = ?, email = ?, firstname = ?, lastname = ? WHERE id = ? ";
	public NewUserDAO() {	}
	public NewUserDAO(Connection conn)
	{
		conn=ConnectionFactory.getConnection();
	}
	public void insertNewUser(NewUser user) throws SQLException
	{
		System.out.println(INSERT_USER_SQL);
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL))
		{
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setInt(2, user.getAccountnumber());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getFirstname());
			preparedStatement.setString(7, user.getLastname());
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public NewUser selectUser(int id) {
		NewUser user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = ConnectionFactory.getConnection();
			 // Step 2:Create a statement using connection object
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) 
			{
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				int accountnumber = rs.getInt("accountnumber");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				user = new NewUser(id,accountnumber,username,password,email,firstname,lastname);
			}
			preparedStatement.execute();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public List<NewUser> selectUser(String username)
	{
		List<NewUser> user = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);) 
		{
				preparedStatement.setString(1, username);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
				int id = rs.getInt("id");
				int accountnumber = rs.getInt("accountnumber");
				String uname = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				user.add(new NewUser(id,accountnumber,uname,password,email,firstname,lastname));
				}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	public List<NewUser> selectAccountUser(int accountnumber)
	{
		List<NewUser> user = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ACCOUNT);) 
		{
				preparedStatement.setInt(1, accountnumber);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
				int id = rs.getInt("id");
				int anumber = rs.getInt("accountnumber");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				user.add(new NewUser(id,anumber,username,password,email,firstname,lastname));
				}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return user;
	}
	public List<NewUser> selectAllUsers() throws printSQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<NewUser> users = new ArrayList<NewUser>();
		// Step 1: Establishing a Connection
		try (Connection connection = ConnectionFactory.getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int accountnumber = rs.getInt("accountnumber");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				users.add(new NewUser(id,accountnumber, username,password, email, firstname, lastname));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	public boolean updateUser(NewUser user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);) {
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getFirstname());
			statement.setString(4, user.getLastname());
			statement.setInt(5, user.getId());
			rowUpdated=statement.executeUpdate()>0;
		}
		return rowUpdated;
	}
}
