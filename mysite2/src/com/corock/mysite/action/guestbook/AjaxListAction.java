package com.corock.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPage = request.getParameter("p");
		if ("".equals(sPage)) {
			sPage = "1";
		}
		// #so. check if a string is numeric
		if (sPage.matches("\\d*") == false) {
			sPage = "1";			
		}
		
		int page = Integer.parseInt(sPage);
		
		GuestbookDAO dao = new GuestbookDAO();
//		List<GuestbookVO> list = dao.getList(page);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
//		map.put("data", list);
		
		response.setContentType("application/json; charset=UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().print(jsonObject.toString());
	}

}
