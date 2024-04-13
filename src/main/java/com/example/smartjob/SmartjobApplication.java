package com.example.smartjob;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.smartjob.adapter.output.db.entity")
@EnableJpaRepositories("com.example.smartjob.adapter.output.db.repository")
@SpringBootApplication(scanBasePackages = "com.example.smartjob")
public class SmartjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartjobApplication.class, args);
	}
	@Bean
	public OpenAPI customOpenAPI(
			@Value("${info.project.title}") String title,
			@Value("${info.project.description}") String appDescription,
			@Value("${info.project.version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title(title)
						.version(appVersion)
						.description(appDescription)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
