package com.search.blog;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskRunner implements ApplicationRunner {

	private final DataSource dataSource;
	
	private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Connection connection = dataSource.getConnection();
		
		log.info("DBCP: {}", dataSource.getClass());
		log.info("Url : {}", connection.getMetaData().getURL());
		log.info("username : {}", connection.getMetaData().getUserName());

		LocalDateTime currentDateTime = LocalDateTime.now();
		String now = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS"));
		String encodePassword = passwordEncoder.encode("1234");
		
		jdbcTemplate.execute("INSERT INTO ACCOUNT SET ID='1', LOGIN_ID='accountA', PASSWORD='" + encodePassword + "', NICK_NAME='사용자A', CREATED_AT='" + now + "', CREATED_BY='ADMIN', UPDATED_AT='" + now + "', UPDATED_BY='ADMIN'");
		jdbcTemplate.execute("INSERT INTO ACCOUNT SET ID='2', LOGIN_ID='accountB', PASSWORD='" + encodePassword + "', NICK_NAME='사용자B', CREATED_AT='" + now + "', CREATED_BY='ADMIN', UPDATED_AT='" + now + "', UPDATED_BY='ADMIN'");
		jdbcTemplate.execute("INSERT INTO ACCOUNT SET ID='3', LOGIN_ID='accountC', PASSWORD='" + encodePassword + "', NICK_NAME='사용자C', CREATED_AT='" + now + "', CREATED_BY='ADMIN', UPDATED_AT='" + now + "', UPDATED_BY='ADMIN'");
	}
}