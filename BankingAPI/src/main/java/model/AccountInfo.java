package model;

import io.javalin.http.Context;

public class AccountInfo{
	public static double balance = 0;
	protected String firstname;
	protected String lastname;
	protected int accountnumber;
	public AccountInfo() {
		// TODO Auto-generated constructor stub
	}
	public AccountInfo(double balance)
	{
		super();
		AccountInfo.balance = balance;

	}
	public AccountInfo(String username, String password, int pin, double balance)
	{
		super();
		AccountInfo.balance = balance;
		// TODO Auto-generated constructor stub
	}
	public AccountInfo(String firstname, String lastname)
	{
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public AccountInfo(int accountnumber, String firstname, String lastname, double balance) {
		super();
		AccountInfo.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountnumber = accountnumber;
	}
	public AccountInfo(int accountnumber, double balance) {
		// TODO Auto-generated constructor stub
	}
	public double getBalance() 
	{
		return balance;
	}

	public void setBalance(double balance) 
	{
		AccountInfo.balance = balance;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	
	public void setAccountNumber(int accountnumber)
	{
		this.accountnumber = accountnumber;
		accountnumber = (int) Math.random();

	}
	public int getAccountnumber() {
		return accountnumber;
		
	}
	
	/*public void ShowInformation()
	{
		AccountInfo newaccountinfo = new AccountInfo();
		if(newaccountinfo != null)
		{
			System.out.print(newaccountinfo.getFirstname());
			System.out.print(newaccountinfo.getLastname());
			System.out.print(newaccountinfo.getBalance());
		}

	}*/

}
