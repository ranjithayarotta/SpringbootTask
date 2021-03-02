package com.paypal.bfs.test.employeeserv.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.paypal.bfs.test.employeeserv.model.IdempotentValue;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean(name="cachemap")
	public Map<String, IdempotentValue> getIdempotentVal() {
		return new HashMap<String, IdempotentValue>();

	}

}
