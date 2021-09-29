package user;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import javax.servlet.http.HttpSession;

public class user
{
    private static Javalin javalin;
    public static void main(String[] args)
    {
        Javalin app = Javalin.create().start();

       // app.post("/", ctx -> {
            //If user credentials are correct, grant an HttpSession:
           // ctx.req.getSession();
       //     ctx.redirect("/profile.html");
      //  });

        app.get("/logout", ctx -> {
            //If you pass in "false", an existing session is checked for.
            HttpSession session = ctx.req.getSession(false);
            if(session != null) session.invalidate();
        });

        app.after(ctx ->{
           ctx.res.addHeader("Access-Control-Allow-Origin", "null");
       });

        app.config.addStaticFiles("/web", Location.CLASSPATH);
       @SuppressWarnings("unused")
       	input user = new input(app);
    }
}
