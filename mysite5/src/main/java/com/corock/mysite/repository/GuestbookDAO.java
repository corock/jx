package com.corock.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.mysite.vo.GuestbookVO;

@Repository
public class GuestbookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int delete( GuestbookVO vo ) {
		return sqlSession.delete( "guestbook.delete", vo );
	}

	public long insert( GuestbookVO vo ) {
		sqlSession.insert( "guestbook.insert", vo );
		long no = vo.getNo();
		return no;
	}
	
	public List<GuestbookVO> selectList() {
		List<GuestbookVO> list = sqlSession.selectList( "guestbook.getList" );
		return list;
	}

	public List<GuestbookVO> selectList(Long startNo) {
		List<GuestbookVO> list = sqlSession.selectList( "guestbook.getList2", startNo );
		return list;
	}
	
}
