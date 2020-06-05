package com.genpact.assignment.backend;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.genpact.assignment.backend.pool.DBConnectionPool;

@SpringBootApplication
public class BackendApplication {

	@Value("${spring.datasource.url}")
    String database_url;
    @Value("${spring.datasource.username}")
    String database_user;
    @Value("${spring.datasource.password}")
    String database_password;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Bean
	public DBConnectionPool getConnectionPoolClass() throws SQLException {
		return new DBConnectionPool(database_url,database_user,database_password);
	}
}
