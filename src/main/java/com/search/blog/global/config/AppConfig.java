package com.search.blog.global.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.search.blog.domain.account.entity.Account;
import com.search.blog.domain.account.repository.AccountRepository;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            AccountRepository accountRepository;

            @Autowired
            PasswordEncoder passwordEncoder;

            @Override
            public void run(ApplicationArguments args) throws Exception {
        		accountRepository.save(Account.Insert()
					 	   					  .loginId("account1")
					 	   					  .password(passwordEncoder.encode("1234"))
					 	   					  .nickName("문성완")
					 	   					  .build());
            }
        };
    }
}