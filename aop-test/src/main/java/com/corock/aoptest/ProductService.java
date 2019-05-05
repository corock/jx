package com.corock.aoptest;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	public ProductVO find( String name ) {
		System.out.println( "finding.." );
		
//		if ( true ) {			
//			throw new RuntimeException( "my exception" );
//		}
		
		return new ProductVO( name );
	}
	
}
