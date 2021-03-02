package com.paypal.bfs.test.employeeserv.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.IdempotentValue;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepo;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	@Autowired
	private EmployeeRepo repo;

	@Qualifier(value = "cachemap")
	private Map<String, IdempotentValue> idempotentVal;

	@Override

	public ResponseEntity<Employee> employeeGetById(String id) {
		Employee emp = repo.findById(Integer.valueOf(id)).get();
		if (emp == null) {
			return new ResponseEntity<>(emp, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> saveEmployee(Employee emp, String idempotentKey) {

		if (isIdempotent(idempotentKey)) {
			Employee emps = repo.save(emp);
			return new ResponseEntity<>(emps, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
		}

	}

	@Override
	public ResponseEntity<Iterable<Employee>> findAllEmployee() {

		Iterable<Employee> empList = repo.findAll();
		return new ResponseEntity<>(empList, HttpStatus.CREATED);

	}

	private boolean isIdempotent(String idempotentKey) {

		IdempotentValue idem = idempotentVal.get(idempotentKey);

		if (idem != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime timeExpiry = idem.getTimeOfCreatetion();
			Duration duration = Duration.between(timeExpiry, now);
			if (duration.toHours() >= 24) {
				return true;
			}
			return false;
		}
		return true;

	}
}
