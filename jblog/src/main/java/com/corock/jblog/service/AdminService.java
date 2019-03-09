package com.corock.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.jblog.controller.AdminController;
import com.corock.jblog.repository.CategoryDAO;
import com.corock.jblog.repository.UserDAO;
import com.corock.jblog.vo.CategoryVO;

@Service
public class AdminService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	public List<CategoryVO> getCategoryList( String id ) {
		Long userNo = userDao.get( id );
		return categoryDao.selectList( userNo );
	}

	public List<CategoryVO> showCategoryNameList( String id ) {
		Long userNo = userDao.get( id );
		return categoryDao.getCategoryNameList( userNo );
	}

	/**
	 * @see {@link AdminController#add}
	 */
	public void writeCategory(CategoryVO categoryVo) {
		categoryDao.insert( categoryVo );
	}

	/**
	 * @see {@link com.corock.jblog.controller.api.AdminController#delete}
	 */
	public boolean deleteCategory( CategoryVO vo ) {
		return 1 == categoryDao.delete( vo );
	}

	
}
