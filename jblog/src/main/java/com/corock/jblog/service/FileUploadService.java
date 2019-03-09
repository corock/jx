package com.corock.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private static final String SAVE_PATH = "/uploads";
	private static final String URL = "uploads/images";

	public String restore( MultipartFile file ) {
		String url = "";
		
		if ( file.isEmpty() ) {
			return url;
		}
		
		String originalFileName = file.getOriginalFilename();
		
		// 확장자를 분리해주는 작업 필요
		String extensionName = originalFileName.substring( originalFileName.lastIndexOf('.') + 1 );
		String saveFileName = generateSaveFileName( extensionName );
		
		long fileSize = file.getSize();
		byte[] fileData;
		
		try {
			fileData = file.getBytes();
			OutputStream os = new FileOutputStream( SAVE_PATH + "/" + saveFileName );
			os.write( fileData );
			os.close();

		} catch (IOException e) {
			new RuntimeException( "upload fail " );
		}
		
		url = URL + "/" + saveFileName;
		
		return url;
	}

	private String generateSaveFileName( String extensionName ) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		
		fileName += calendar.get( Calendar.YEAR );
		fileName += calendar.get( Calendar.MONTH + 1 );
		fileName += calendar.get( Calendar.DATE );
		fileName += calendar.get( Calendar.HOUR );
		fileName += calendar.get( Calendar.MINUTE );
		fileName += calendar.get( Calendar.SECOND );
		fileName += calendar.get( Calendar.MILLISECOND );
		fileName += ( "." + extensionName );
		
		return fileName;
	}
	
}
