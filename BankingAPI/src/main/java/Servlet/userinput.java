package Servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Connection.ConnectionFactory;
import DAO.AccountInfoDAO;
import DAO.NewUserDAO;
import Exceptions.printSQLException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import model.AccountInfo;
import model.NewUser;
import org.apache.commons.lang3.BooleanUtils;

public class userinput {

	private static AccountInfoDAO accountDAO;
	private static NewUserDAO userDAO;
	private static Javalin javalin;
	
	public void DAO()
	{
		accountDAO = new AccountInfoDAO();
		userDAO = new NewUserDAO();
	}
	public static void init(Javalin app)
	{
		javalin = app;
		//New User Input
		app.get("/newUser", userinput::connectiontest);
		app.get("/clients/newUser/", userinput::getAllUsers);
		app.get("/clients/newUser/id", userinput::getUserByID);
		app.get("/clients/newUser/:accountnumber",userinput::getUserAccountNum);	
		app.post("/clients/newUser/newUser", userinput::insertNewUser);
		app.put("/client/clients/newUser/newUser/:id", userinput::updateUser);
		app.delete("/newUser/:id",userinput::deleteUser);
		//Account Information Input
		app.get("/accountinfo", userinput::connectiontest);
		app.get("/clients/accountinfo/", userinput::getAccountInfo);
		app.get("/client/accountinfo/getbetween", userinput::getBetween);
		app.get("/clients/accountinfo/:accountid", userinput::getAccountID);	
		app.post("/clients/accountinfo/accountinfo", userinput::insertAccount);
		app.put("/client/clients/accountinfo/accountinfo/:accountid", userinput::updateAccount);
		app.delete("/accountinfo/:accountid", userinput::deleteAccountInfo);
		//Deposits and Withdrawal		
		app.patch("/client/accountinfo/deposit/:accountid", userinput::deposit);
		app.patch("/client/accountinfo/withdraw/:accountid", userinput::withdraw);
		app.patch("/client/accountinfo/transfer/", userinput::transfer);
	}
	public static void connectiontest(Context ctx)
	{
		ctx.status(200);
		ctx.result("Connection established!");
	}
	////////////////////////////////////////////////////////////////
	public static void insertNewUser(Context ctx) throws SQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		NewUser row = ctx.bodyAsClass(NewUser.class);
		userDAO.insertNewUser(row);
		ctx.json(row).toString();
		if(row.getId()!=0)
		{
			ctx.status(201);
			return;
		}
		ctx.status(409);
	}
	///////////////////////////////////////////////////////////////////////
	public static void getAllUsers(Context ctx) throws SQLException, printSQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		ctx.json(userDAO.selectAllUsers());
		
	}
	//////////////////////////////////////////////////////////////
	public static void getUserByID(Context ctx) throws SQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		ctx.json(userDAO.selectUser(Integer.parseInt(ctx.pathParam("id"))));
	}
	//////////////////////////////////////////////////////////////////
	public static void getUserAccountNum(Context ctx) throws SQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		List<NewUser> row = userDAO.selectAccountUser(Integer.parseInt(ctx.pathParam("accountnumber")));
		ctx.json(row);
		if(row == null)
		{
			ctx.status(404);
			ctx.result("No user exists");
		}
	}
	//////////////////////////////////////////////////////////////////////
	public static void deleteUser(Context ctx) throws SQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		boolean row = userDAO.deleteUser(Integer.parseInt(ctx.pathParam("id")));
		ctx.json(row);
		if(row == false)
		{
			ctx.status(404);
			ctx.result("No user exists");
		}
	}
	/////////////////////////////////////////////////////////////////
	public static void updateUser (Context ctx) throws SQLException
	{
		userDAO = new NewUserDAO(ConnectionFactory.getConnection());
		NewUser user = ctx.bodyAsClass(NewUser.class);
		boolean rows = userDAO.updateUser(user);
		ctx.json(rows);
		if(rows == true)
		{
			ctx.status(200);
			return;
		}
		else
			{
				ctx.status(404);
			}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void insertAccount(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		AccountInfo row = ctx.bodyAsClass(AccountInfo.class);
		accountDAO.insertAccount(row);
		ctx.json(row).toString();
		if(row.getAccountnumber()!=0)
		{
			ctx.status(201);
			return;
		}
		ctx.status(409);
	}
	//////////////////////////////////////////////////////////////////////
	public static void getAccountInfo(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		ctx.json(accountDAO.selectAllAccounts());
	}
	////////////////////////////////////////////////////////////////////
	public static void getAccountID(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		ctx.json(accountDAO.SelectAccountInfo(Integer.parseInt(ctx.pathParam("accountid"))));
	}
	//////////////////////////////////////////////////////////////////////
	public static void getBetween(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		String lessthan = ctx.queryParam("less");
		String greaterthan = ctx.queryParam("greater");
		double less = Double.parseDouble(lessthan);
		double greater = Double.parseDouble(greaterthan);
		ctx.json(accountDAO.getBetween(less, greater));
	}
	////////////////////////////////////////////////////////////////////////
	public static void updateAccount(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		AccountInfo account = ctx.bodyAsClass(AccountInfo.class);
		boolean rows = accountDAO.updateAccount(account);
		ctx.json(rows);
		if(rows == false)
			{
				ctx.status(404);
			}
	}
	///////////////////////////////////////////////////////////////////////
	public static void deleteAccountInfo(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		boolean row = accountDAO.deleteAccount(Integer.parseInt(ctx.pathParam("accountid")));
		ctx.json(row);
		if(row == false)
		{
			ctx.status(404);
			ctx.result("No user exists");
		}
	}
	/////////////////////////////////////////////////////////////////////////
	public static void deposit(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		boolean rows = accountDAO.deposit(ctx.bodyAsClass(AccountInfo.class));
		ctx.json(rows);
		if(rows == false)
			{
				ctx.status(404);
			}
		return;
	}
	//////////////////////////////////////////////////////////////////////////
	public static void withdraw(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		boolean rows = accountDAO.withdraw(ctx.bodyAsClass(AccountInfo.class));
		int value = BooleanUtils.toInteger(rows);
		ctx.json(rows);
		if(rows == false)
			{
				ctx.status(404);
			}
		else if(value <= 0)
		{
			ctx.status(422);
		}
	}
//////////////////////////////////////////////////////////////////////////
	public static void transfer(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		boolean[] rows = accountDAO.transfer(ctx.bodyAsClass(AccountInfo.class), ctx.bodyAsClass(AccountInfo.class));
		ctx.json(rows);
	}
	/*public static void updateAccountBalance(Context ctx) throws SQLException
	{
		accountDAO = new AccountInfoDAO(ConnectionFactory.getConnection());
		AccountInfo accountinfo = accountDAO.updateBalance(ctx.toString()));
	}
	public static void UpdateDepositandWithdrawal(Context ctx) throws SQLException
	{
		functionDAO = new BankFunctionsDAO(ConnectionFactory.getConnection());
		boolean row = functionDAO.deleteDepositAndWithdrawal(Integer.parseInt(ctx.pathParam("accountid")));
		ctx.json(row);
	}*/
}

