package com.corock.helloweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/table", "/t", "/tbl" })
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String row = request.getParameter("r");
		String col = request.getParameter("c");
		
		int nRow = (row == null) ? 1 : Integer.parseInt(row);
		int nCol = (col == null) ? 1 : Integer.parseInt(col);
		
		PrintWriter out = response.getWriter();
		out.println("<table border='1' cellspacing='0' cellpadding='5px'>");
		
		for (int i = 0; i < nRow; i++) {
			out.println("<tr>");
			
			for (int j = 0; j < nCol; j++) {
				out.println("<td>cell(" + nRow + "," + nCol + ")</td>");
			}
			
			out.println("</tr>");
		}
		
		out.println("</table>");		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
