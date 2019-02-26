package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.FriendService;
import services.OperationService;
import services.UserService;
import tools.FriendTools;
import tools.MessageTools;
import tools.ServiceTools;

/**
 * Servlet implementation class Operation
 */

public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_id = request.getParameter("user_id");
		String user_id2 = request.getParameter("user_id2");
		PrintWriter out = response.getWriter();
		try {
			FriendService.addFriend(Integer.parseInt(user_id), Integer.parseInt(user_id2));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			 JSONObject res =  ServiceTools.ServiceRefused("probleme sql", 100);
			 out.println(res);
		};
		JSONObject res = ServiceTools.ServiceAccepted("ami ajouté");
		out.println(res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}