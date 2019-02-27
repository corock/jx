package com.corock.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.SiteDAO;
import com.corock.mysite.vo.SiteVO;

@Service
public class SiteService {

	@Autowired
	private SiteDAO siteDao;
	
	public SiteVO getSite() {
		return siteDao.get();
	}

	public void update( SiteVO siteVo ) {
		siteDao.update( siteVo );
	}

}
