package com.enterprise.products;

/**
 * Wrote aa application which is run in MVC frame work. 
 * Controller is calling 3 micro services manufacturer, product and orderqty. 
 * All CRUD operation are doing at spring boot micro services. 
 * After that MVC controller will inserted record in mongodb and file using kafka producer and consumer. 
 * Now i want to deploye this applications in AWS environment. 
 * Need to setp by step procedure in aws environment.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableMongoRepositories
@EnableJpaRepositories
public class EnterpriseProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseProductsApplication.class, args);
	}

}
