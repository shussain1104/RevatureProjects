package user;

import Models.employee;
import Models.reimbursement;
import Services.employeeService;
import Services.reimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import static io.javalin.apibuilder.ApiBuilder.get;

public class input {

    private employeeService employeeinput;
    private reimbursementService reimbursements;

    public input(Javalin app)
    {
        employeeinput = new employeeService();
       app.post("/",LoginHandler);
       // app.get("/reimbursementform/pastandpending", findAllReimbursements);
       app.routes(() ->
        {
           path("/", ()->
           {
               path("/allreimbursements",()->
               {
                 get(findAllReimbursements);
               });
               path("/submit", ()->
               {
                   post(submitRequest);
               });
           });
        });
    }

    public static void init(Javalin app)
    {
        Javalin javalin = app;
    }
    public Handler LoginHandler = ctx -> {
    	String employee_username = ctx.req.getParameter("employee_username");
    	String employee_password = ctx.req.getParameter("employee_password");	
    	HttpSession session = ctx.req.getSession();
    	employee user = new employee(employee_username,employee_password);
    	session.setAttribute("employee_username", employee_username);
    	session.setAttribute("employee_password", employee_password);
		user = this.employeeinput.findbyusername(employee_username);
    	if(user == null)
    	{
    		ctx.req.getRequestDispatcher("/index.html");
    		ctx.redirect("/index.html", 404);
    	}
    	else
    	{
    		ctx.req.getRequestDispatcher("/profile.html");
    		ctx.res.sendRedirect("/profile.html");
    	}
    };
	private Handler findAllReimbursements = ctx -> {
		System.out.println("Controller");

		HttpSession session = ctx.req.getSession(false);

		if(session != null)
			ctx.json(this.reimbursements.findAll());
		else
			ctx.res.getWriter().write("You do not have a session");
	};

    private Handler submitRequest = ctx -> {
    	//Date reimbursement_date = new Date();
    	HttpSession session = ctx.req.getSession(false);
    	if(session!=null)
    	{
    		reimbursement rerequest = new reimbursement(1234,
    			ctx.req.getParameter("reimbursement_purpose"),
    			Double.valueOf(ctx.req.getParameter("reimbursement_amount")),
    			ctx.req.getParameter("reimbursement_type"),
    			ctx.req.getParameter("reimbursement_comments"));
    		
    		
    		this.reimbursements.persist(rerequest);
    		ctx.status(201);
    		ctx.redirect("/reimbursementform.html");
    	}
    	else
    	{
    		ctx.res.getWriter().write("You do not have a session");
    	}
    	
    };
}
