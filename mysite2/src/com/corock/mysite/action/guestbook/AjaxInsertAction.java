package com.corock.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

import net.sf.json.JSONObject;

public class AjaxInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		String message = "";
		String password = "";
		
		GuestbookVO vo = new GuestbookVO();
		vo.setName(name);
		vo.setMessage(message);
		vo.setPassword(password);
		
		GuestbookDAO dao = new GuestbookDAO();
//		long no = dao.insert(vo);
//		GuestbookVO newVo = dao.get(no);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
//		map.put("data", newVo);
		
		response.setContentType("application/json; charset=UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().print(jsonObject.toString());
	}

}
