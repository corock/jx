package com.corock.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	private static final String SAVE_PATH = "/uploads";
	private static final String URL = "/uploads/images";

	public String restore( MultipartFile multipartFile ) {
		String url = "";
		
		try {
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
			
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream( SAVE_PATH + "/" + saveFileName );
			os.write( fileData );
			os.close();
			
			url = URL + "/" + saveFileName;
			
		} catch ( IOException ex ) {
			new RuntimeException( "upload fail" );
		}
		
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
