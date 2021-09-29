package Servlet;
import java.sql.Connection;

import Connection.ConnectionFactory;
import io.javalin.Javalin;

public class user {

	public static void main(String[] args) 
	{
		Javalin app = Javalin.create().start(7000);
		Connection conn = ConnectionFactory.getConnection();
		nonstaticuserinput controller = new nonstaticuserinput(app,conn);
		userinput.init(app);
	}

}
