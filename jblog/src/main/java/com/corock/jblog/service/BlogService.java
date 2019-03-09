package com.corock.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.jblog.controller.AdminController;
import com.corock.jblog.repository.BlogDAO;
import com.corock.jblog.vo.BlogVO;

@Service
public class BlogService {

	@Autowired
	private BlogDAO blogDao;

	/**
	 * @see {@link BlogController#basic}
	 */
	public BlogVO getBlogInformation( String id ) {
		return blogDao.get( id );
	}

	/**
	 * @see {@link BlogController#upload}
	 */
	public void modifyBasicSettings(BlogVO blogVo) {
		blogDao.update( blogVo );
	}
	
}
