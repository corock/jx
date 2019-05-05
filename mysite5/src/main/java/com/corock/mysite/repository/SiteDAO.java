package com.corock.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.mysite.vo.SiteVO;

@Repository
public class SiteDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public SiteVO get() {
		return sqlSession.selectOne( "site.get" );
	}

	public int update( SiteVO siteVo ) {
		return sqlSession.update( "site.update", siteVo );
	}
	
}
