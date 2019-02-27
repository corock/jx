package com.corock.fileupload.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String restore( MultipartFile multipartFile ) {
		String url = "";
		
		if ( multipartFile.isEmpty() ) {
			return url;
		}
		
		String originalFileName = multipartFile.getOriginalFilename();
		
		// 확장자를 분리해주는 작업 필요
		String extensionName = originalFileName.substring( originalFileName.lastIndexOf('.') + 1 );
		String saveFileName = generateSaveFileName( extensionName );

		long fileSize = multipartFile.getSize();
		
		System.out.println( "originalFileName: " + originalFileName );
		System.out.println( "extensionName: " + extensionName );
		System.out.println( "saveFileName: " + saveFileName );
		System.out.println( "fileSize: " + fileSize );
		
		return url;
	}

	private String generateSaveFileName( String extensionName ) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get( Calendar.YEAR );
		fileName += calendar.get( Calendar.MONTH );
		fileName += calendar.get( Calendar.DATE );
		fileName += calendar.get( Calendar.HOUR );
		fileName += calendar.get( Calendar.MINUTE );
		fileName += calendar.get( Calendar.SECOND );
		fileName += calendar.get( Calendar.MILLISECOND );
		fileName += ( "." + extensionName );
				
		return fileName;
	}
	
}
