package Servlet;

import java.sql.Connection;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class nonstaticuserinput {
	 private Javalin app;
	 private Connection conn;
	public nonstaticuserinput(Javalin app, Connection conn) {
		this.app = app;
		this.conn = conn;
	}
	public void test(Context ctx) {
        ctx.status(200);
        ctx.result("Test Received!");
    }

}
