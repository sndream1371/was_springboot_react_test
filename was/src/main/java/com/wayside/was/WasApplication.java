package com.wayside.was;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wayside.was.mapper") // MyBatis 매퍼가 있는 패키지 지정
public class WasApplication {
	public static void main(String[] args) {
		SpringApplication.run(WasApplication.class, args);
	}
}
