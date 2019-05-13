package jstlel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02")
public class _02Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO vo2 = new UserVO();
		vo2.setNo(2);
		
		UserVO vo3 = new UserVO();
		vo3.setNo(3);

		UserVO vo4 = new UserVO();
		vo4.setNo(4);

		// save to request scope
		request.setAttribute("vo2", vo2);
		
		// save to session scope
		request.getSession(true).setAttribute("vo3", vo3);
		
		// save to application scope
		request.getServletContext().setAttribute("vo4", vo4);
		
		request.getRequestDispatcher("/WEB-INF/views/02.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
