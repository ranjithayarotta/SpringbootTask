package com.paypal.bfs.test.employeeserv.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.api.model.Employee;


@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
