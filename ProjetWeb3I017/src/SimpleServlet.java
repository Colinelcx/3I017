import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Operation;

/**
 * Servlet implementation class Operation
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int counter = 0;
	private String mutex = ""; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
    
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException
    		{
    		resp.getWriter().println("<HTML><BODY>");
    		resp.getWriter().println(this + ": <br>");
    		for (int c = 0; c < 10; c++)
    		{
    		resp.getWriter().println("Counter = " + counter + "<BR>");
    		counter++;
    		}
    		resp.getWriter().println("</BODY></HTML>");
    		}
    
    
    /*public void doGet(HttpServletRequest req, HttpServletResponse resp)
			 throws ServletException, IOException
	 {
	 resp.getWriter().println("<HTML><BODY>");
	 resp.getWriter().println(this + ": <br>");
	 synchronized (mutex)
	 {
	 for (int c = 0; c < 10; c++)
	 {
	 resp.getWriter().println("Counter = " + counter + "<BR>");
	 counter++;
	 }
	 }
	 resp.getWriter().println("</BODY></HTML>");
	 }*/
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
