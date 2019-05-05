package com.corock.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mysite.vo.UserVO;

import net.sf.json.JSONObject;

@WebServlet("/ajax2")
public class AjaxServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");

		// java object -> json string
		UserVO vo = new UserVO();
		vo.setNo(10);
		vo.setName("둘리");
		vo.setEmail("dooly@gmail.com");
		vo.setGender("male");
		
		JSONObject jsonObject = JSONObject.fromObject(vo);
		String jsonString = jsonObject.toString();
		
		response.setContentType("application/json; charset='utf-8'");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
