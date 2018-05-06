package com.lanxuewei.code_on_line;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CodeOnLineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeOnLineApplication.class, args);
	}

	@RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
	String index(){
		return "Hello Code_on_line!";
	}
}
