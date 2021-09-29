package model;

public class NewUser{
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String username;
	protected String password;
	protected int id;
	protected int accountnumber;
	
	public NewUser() {
		// TODO Auto-generated constructor stub
	}

	public NewUser(int id, int accountnumber, String username,String password, String email,String firstname, String lastname) {
		super();
		this.id=id;
		this.accountnumber = accountnumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public NewUser(int accountnumber)
	{
		this.accountnumber=accountnumber;
	}
	public NewUser(int accountnumber, String firstname, String lastname) 
	{
		this.accountnumber = accountnumber;
		this.firstname = firstname;
		this.lastname = lastname;
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


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	

}
