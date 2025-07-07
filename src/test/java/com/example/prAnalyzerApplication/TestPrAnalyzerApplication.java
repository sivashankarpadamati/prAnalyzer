package com.example.prAnalyzerApplication;

import org.springframework.boot.SpringApplication;

public class TestPrAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.from(PrAnalyzerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
