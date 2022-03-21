package com.gsg.spring.api;

import org.springframework.stereotype.Component;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;


@Component
@Getter
public class ApiKey {

	@Value("${api.key}")
	private String apiKey;
	
}
