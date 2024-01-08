package com.dataStructuresCourse.dataStructuresCourse;

import com.dataStructuresCourse.dataStructuresCourse.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DataStructuresCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataStructuresCourseApplication.class, args);
	}

}
