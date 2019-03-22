package com.corock.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corock.mysite.repository.GalleryDAO;
import com.corock.mysite.vo.GalleryVO;

@Service
public class GalleryService {

	@Autowired
	private GalleryDAO galleryDao;
	
	public List<GalleryVO> getGalleryList() {
		return galleryDao.getList();
	}

	public boolean saveImageInformation( GalleryVO galleryVo ) {
		return 1 == galleryDao.insert( galleryVo );
	}

	public boolean deleteImageInformation( Long no ) {
		return 1 == galleryDao.delete( no );
	}

}
