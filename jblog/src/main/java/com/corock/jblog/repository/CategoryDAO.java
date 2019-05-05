package com.corock.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.jblog.service.UserService;
import com.corock.jblog.vo.CategoryVO;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;

	/**
	 * @see {@link UserService#join}
	 */
	public int insert( Long userNo ) {
		return sqlSession.insert( "category.insertDefault", userNo );
	}

	public List<CategoryVO> selectList( Long userNo ) {
		return sqlSession.selectList( "category.selectList", userNo );
	}

	public List<CategoryVO> getCategoryNameList( Long userNo ) {
		return sqlSession.selectList( "category.getCategoryNameList", userNo );
	}

	/**
	 * @see {@link }
	 */
	public int insert( CategoryVO categoryVo ) {
		return sqlSession.insert( "category.insert", categoryVo );
	}

	/**
	 * @see {@link com.corock.jblog.controller.AdminService#deleteCategory}
	 */
	public int delete( CategoryVO vo ) {
		return sqlSession.delete( "category.delete", vo );
	}

}
