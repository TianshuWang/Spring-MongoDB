package com.tianshu;

import com.tianshu.dao.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableMongoRepositories
@EnableAuthorizationServer
public class SpringMongodbApplication {

	@Autowired
	ItemRepository groceryItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}

}
