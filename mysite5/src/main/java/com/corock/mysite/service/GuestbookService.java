package com.corock.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.GuestbookDAO;
import com.corock.mysite.vo.GuestbookVO;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDAO guestbookDao;

	public List<GuestbookVO> getMessageList() {
		return guestbookDao.selectList();
	}

	public List<GuestbookVO> getMessageList( Long startNo ) {
		return guestbookDao.selectList( startNo );
	}

	public void writeMessage( GuestbookVO vo ) {
		guestbookDao.insert( vo );
	}
	
	public boolean deleteMessage( GuestbookVO vo ) {
		return 1 == guestbookDao.delete( vo );
	}

}
