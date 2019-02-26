package com.corock.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.corock.mysite.exception.UserDAOException;
import com.corock.mysite.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int update( UserVO vo ) {
		return sqlSession.update( "user.update", vo );
	}

	public UserVO get( long no ) {
		return sqlSession.selectOne( "user.getByNo", no );
	}
	
	public UserVO get( String email ) {		
		return sqlSession.selectOne( "user.getByEmail", email );
	}
	
	public UserVO get( String email, String password ) throws UserDAOException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Map<String, String> map = new HashMap<>();
		map.put( "email", email );
		map.put( "password", password );

		UserVO userVo = sqlSession.selectOne( "user.getByEmailAndPassword", map ); 
		
		stopWatch.stop();
		Long totalTime = stopWatch.getTotalTimeMillis();
		
		// Working with logging
		System.out.println("totalTime: " + totalTime);

		return userVo;
	}
	
	public int insert( UserVO vo ) {
		return sqlSession.insert( "user.insert", vo );
	}
	
}
