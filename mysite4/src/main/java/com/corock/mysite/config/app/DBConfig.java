package com.corock.mysite.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

	@Bean
	public DataSource basicDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName( "com.mysql.jdbc.Driver" );
		basicDataSource.setUrl( "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC" );
		basicDataSource.setUsername( "webdb" );
		basicDataSource.setPassword( "webdb" );
		
		// DB connection pool tuning element
		basicDataSource.setInitialSize( 20 );
		basicDataSource.setMaxActive( 20 );
		
		return basicDataSource;
	}
	
	public PlatformTransactionManager transactionManager( DataSource dataSource ) {
		return new DataSourceTransactionManager( dataSource );
	}
	
}
