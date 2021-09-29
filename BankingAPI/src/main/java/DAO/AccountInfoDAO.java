package DAO;

import java.io.FileNotFoundException;
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

import org.apache.commons.lang3.BooleanUtils;

import Connection.ConnectionFactory;
import model.AccountInfo;
import io.javalin.http.Context;
import model.NewUser;

public class AccountInfoDAO 
{
	private final static String INSERT_ACCOUNT = "INSERT INTO accountinfo (accountid,firstname,lastname, balance) VALUES (?,?,?,?);";
	private final static String SELECT_ACCOUNT_INFO = "SELECT * FROM accountinfo";
	private final static String GET_BETWEEN = "SELECT * FROM accountinfo WHERE balance BETWEEN ? AND ?;";
	private final static String SELECT_ACCOUNT_BY_NUMBER = "SELECT accountid,balance FROM accountinfo WHERE accountid = ?";
	private final static String DEPOSIT_WITHDRAWAL = "SELECT balance FROM accountinfo WHERE accountid = ?";
	private final static String DELETE_ACCOUNT_INFO = "DELETE  FROM accountinfo WHERE accountid =?";
	private final static String UPDATE_ACCOUNT = "UPDATE accountinfo SET firstname = ?, lastname = ? WHERE accountid = ?;";
	private final static String UPDATE_BALANCE = "UPDATE accountinfo SET balance =  balance + ? WHERE accountid = ?;";
	private final static String UPDATE_BALANCE2 = "UPDATE accountinfo SET balance =  balance - ? WHERE accountid = ?;";
	Connection connection;
	public AccountInfoDAO() {	}
	public AccountInfoDAO(Connection conn)
	{
		connection = conn;
	}
	public void insertAccount(AccountInfo account) throws SQLException
	{
		System.out.println(INSERT_ACCOUNT);
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT))
		{
			preparedStatement.setInt(1, account.getAccountnumber());
			preparedStatement.setString(2, account.getFirstname());
			preparedStatement.setString(3, account.getLastname());
			preparedStatement.setDouble(4, account.getBalance());
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public AccountInfo SelectAccountInfo(int accountid) 
	{
		AccountInfo accountinfo = null;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER);)
		{
			preparedStatement.setInt(1, accountid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				double balance = rs.getDouble("balance");
				accountinfo = new AccountInfo(accountid, firstname, lastname, balance);
			}
		}
		catch (SQLException e) {
			printSQLException(e);
		}
		return accountinfo;
	}
		/*public AccountInfo SelectBalance(double balance) 
		{
			AccountInfo accountinfo = null;
			try (Connection connection = ConnectionFactory.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(GET_BETWEEN);)
			{
				preparedStatement.setDouble(1, balance);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					int accountid = rs.getInt("accountid");
					accountinfo = new AccountInfo(accountid, firstname, lastname, balance);
				}
			}
			catch (SQLException e) {
				printSQLException(e);
			}
			return accountinfo;
			
		}*/
	public List<AccountInfo> selectAllAccounts()
	{
		List<AccountInfo> accounts = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_INFO);)
		{
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next())
				{
					int accountid = rs.getInt("accountid");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					double balance = rs.getDouble("balance");
					accounts.add(new AccountInfo(accountid, firstname, lastname, balance));
					
				}
		}
		catch (SQLException e) {
			printSQLException(e);
		}
		return accounts;
		
	}
	public List<AccountInfo> getBetween(double less, double greater)
	{
		List<AccountInfo> accounts = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BETWEEN);)
		{
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next())
				{
					int accountid = rs.getInt("accountid");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					double balance = rs.getDouble("balance");
					accounts.add(new AccountInfo(accountid, firstname, lastname, balance));
					
				}
		}
		catch (SQLException e) {
			printSQLException(e);
		}
		return accounts;
		
	}
	public boolean deleteAccount(int accountid) throws SQLException 
	{
		boolean rowDeleted;
		try (Connection connection = ConnectionFactory.getConnection();
			 PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT_INFO);) {
			statement.setInt(1, accountid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	public boolean updateAccount(AccountInfo account) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);) {

			statement.setString(1, account.getFirstname());
			statement.setString(2, account.getLastname());
			statement.setInt(3, account.getAccountnumber());
			rowUpdated=statement.executeUpdate()>0;
		}
		return rowUpdated;
	}
	public boolean deposit(AccountInfo account) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);) {
			statement.setDouble(1, account.getBalance());
			statement.setInt(2, account.getAccountnumber());
			rowUpdated=statement.executeUpdate()>0;
			statement.execute();
		}
		return rowUpdated;
	}
	public boolean withdraw(AccountInfo account) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE2);) {
			statement.setInt(2, account.getAccountnumber());
			statement.setDouble(1, account.getBalance());
			rowUpdated=statement.executeUpdate()>0;
			statement.execute();
		}
		return rowUpdated;
	}
	public boolean[] transfer(AccountInfo account, AccountInfo account1) throws SQLException
	{
		boolean rowUpdated;
		boolean rowUpdated1;
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);
				PreparedStatement statement2 = connection.prepareStatement(UPDATE_BALANCE2);) 
		{
			statement.setDouble(1, account.getBalance());
			statement.setInt(2, account.getAccountnumber());
			statement2.setDouble(3, account1.getBalance());
			statement2.setInt(4, account1.getAccountnumber());
			rowUpdated = statement.executeUpdate() > 0;
			rowUpdated1 = statement2.executeUpdate()>0;
		}
		return new boolean[] {rowUpdated, rowUpdated1};
		/*int rowUpdated = 0;
		boolean row1 = withdraw(account);
		int value1 = BooleanUtils.toInteger(row1);
		for(int i = 0; i <= value1; i++)
		{
			 boolean row2 = deposit(account);
			 rowUpdated = BooleanUtils.toInteger(row1) + BooleanUtils.toInteger(row2);
		}
		return rowUpdated;*/
	}
	/*public static double depositsavings(double depositamount)
	{
		if(AccountInfo.balance >= -1)
		 {
			AccountInfo.balance = AccountInfo.balance + depositamount;
		 }
		return AccountInfo.balance;
	}
	public static double withdrawal(double withdrawamount)
	{
		if(AccountInfo.balance > 0)
		 {
			AccountInfo.balance = AccountInfo.balance - withdrawamount;
		 }
		else
		{
			System.out.println("Withdrawal not possible");
		}
		return AccountInfo.balance;
	}
	public void depositWithdrawal(Context ctx,int accountnumber, double amount)
	{
		String[] jsonObject = jsonParser(ctx.body());
		if(jsonObject[0].equals("deposit")) {
			depositsavings(amount);
			ctx.status(201);
		}
		else if(jsonObject[0].equals("withdraw"))
		{
			if(amount > 0)
				{
				ctx.status(422);
				}
		}
	}
	private String[] jsonParser(String json) {
		String[] finishedJSON = new String[2];
		String[] parser = json.split("\"");
		finishedJSON[0] = parser[1];
		parser = parser[2].split(":");
		parser = parser[1].split("}");
		finishedJSON[1] = parser[0];
		return finishedJSON;
	}
	public void deposit(AccountInfo accountinfo) throws SQLException
	{
		PreparedStatement pstatement = connection.prepareStatement(DEPOSIT_WITHDRAWAL);
		ResultSet rs = (ResultSet) pstatement;
		rs.next();
		double balance = rs.getDouble("balance");
		depositsavings(balance);
	}
	public void withdrawal(AccountInfo accountinfo) throws SQLException
	{
		PreparedStatement pstatement = connection.prepareStatement(DEPOSIT_WITHDRAWAL);
		ResultSet rs = (ResultSet) pstatement;
		rs.next();
		double balance = rs.getDouble("balance");
		withdrawal(balance);
	}
	public boolean depositmoney(AccountInfo accountinfo) throws SQLException
	{
		boolean rowUpdated;
		double balance = 0;
		try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);)
		{
			statement.setDouble(1, depositsavings(balance));
			statement.setDouble(2,withdrawal(balance));
			statement.setInt(3,accountinfo.getAccountnumber());
			rowUpdated = statement.executeUpdate()>0;
		}
		return rowUpdated;
	}
	public boolean withdraw(AccountInfo accountinfo) throws SQLException
	{
		boolean rowUpdated;
		double balance = 0;
		try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);)
		{
			statement.setInt(1,accountinfo.getAccountnumber());
			statement.setDouble(2,withdrawal(balance));
			rowUpdated = statement.executeUpdate()>0;
		}
		return rowUpdated;
	}*/
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
