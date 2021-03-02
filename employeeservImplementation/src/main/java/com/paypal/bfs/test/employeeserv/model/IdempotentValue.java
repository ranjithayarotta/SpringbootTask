package com.paypal.bfs.test.employeeserv.model;

import java.time.LocalDateTime;

public class IdempotentValue {

	private LocalDateTime timeOfCreatetion;
	private String key;

	public LocalDateTime getTimeOfCreatetion() {
		return timeOfCreatetion;
	}

	public void setTimeOfCreatetion(LocalDateTime timeOfCreatetion) {
		this.timeOfCreatetion = timeOfCreatetion;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
