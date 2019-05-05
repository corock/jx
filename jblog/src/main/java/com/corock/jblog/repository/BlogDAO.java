package com.corock.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.jblog.service.BlogService;
import com.corock.jblog.vo.BlogVO;

@Repository
public class BlogDAO {

	@Autowired
	private SqlSession sqlSession;

	public int insert( Long userNo ) {
		return sqlSession.insert( "blog.insert", userNo );
	}

	/**
	 * @see {@link BlogService#getBlogInformation}
	 */
	public BlogVO get( String id ) {
		return sqlSession.selectOne( "blog.get", id );
	}

	/**
	 * @see {@link BlogService#modifyBasicSettings}
	 */
	public int update(BlogVO blogVo) {
		return sqlSession.update( "blog.update", blogVo );
	}	
	
}
