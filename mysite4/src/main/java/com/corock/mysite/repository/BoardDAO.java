package com.corock.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.mysite.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVO> getList( String keyword, Integer page, Integer size ) {
		Map<String, Object> map = new HashMap<>();
		map.put( "keyword", keyword );
		map.put( "startIndex", (page - 1) * size );
		map.put( "size", size );

//		System.out.println("keyword in BoardDAO: " + keyword);
//		System.out.println("page in BoardDAO: " + page);

		return sqlSession.selectList( "board.getList", map );
	}
	
	public int getTotalCount( String keyword ) {
		return sqlSession.selectOne( "board.getTotalCount", keyword );
	}
	
	public int updateOrderNo( Integer groupNo, Integer orderNo ) {
		Map<String, Integer> map = new HashMap<>();
		map.put( "groupNo", groupNo );
		map.put( "orderNo", orderNo );
		
		return sqlSession.update( "board.updateOrderNo", map );
	}
	
	public int insert( BoardVO boardVo ) {
		return sqlSession.insert( "board.insert", boardVo );
	}

	public BoardVO get( Long no ) {
		return sqlSession.selectOne( "board.getByNo", no );
	}

	public int updateHit( Long no ) {
		return sqlSession.update( "board.updateHit", no );
	}

	public int delete( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.delete( "board.delete", map );
	}

	public BoardVO get( Long no, Long userNo ) {
		Map<String, Long> map = new HashMap<>();
		map.put( "no", no );
		map.put( "userNo", userNo );
		
		return sqlSession.selectOne( "board.getByNoAndUserNo", map );
	}

	public int update(BoardVO boardVo) {
		return sqlSession.update( "board.update", boardVo );
	}	
	
}
