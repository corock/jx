package com.corock.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mysite.vo.UserVO;

import net.sf.json.JSONArray;

@WebServlet("/ajax3")
public class AjaxServlet3 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Java Collection(List) -> JSON
		List<UserVO> list = new ArrayList<>();
		
		UserVO vo1 = new UserVO();
		vo1.setNo(10);
		vo1.setName("둘리");
		vo1.setEmail("dooly@gmail.com");
		vo1.setGender("male");
		list.add(vo1);

		UserVO vo2 = new UserVO();
		vo2.setNo(20);
		vo2.setName("마이콜");
		vo2.setEmail("michael@gmail.com");
		vo2.setGender("male");
		list.add(vo2);
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		String jsonString = jsonArray.toString();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
