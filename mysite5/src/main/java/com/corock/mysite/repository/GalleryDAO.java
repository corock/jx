package com.corock.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corock.mysite.vo.GalleryVO;

@Repository
public class GalleryDAO {

	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVO> getList() {
		return sqlSession.selectList( "gallery.getList" );
	}

	public int insert( GalleryVO galleryVo ) {
		return sqlSession.insert( "gallery.insert", galleryVo );
	}

	public int delete( Long no ) {
		return sqlSession.delete( "gallery.delete", no );
	}
	
	
	
}
