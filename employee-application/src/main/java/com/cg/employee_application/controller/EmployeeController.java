package com.cg.employee_application.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employee_application.entity.Employee;
import com.cg.employee_application.exception.EmployeeNotFoundException;
import com.cg.employee_application.service.EmployeeService;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Employee addNewEmployee(@Valid @RequestBody Employee employee) {
		LOG.info("Saving Employee Details .");
		return employeeService.addNewEmployee(employee);
	}

	@RequestMapping(value = "/{empId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String empId) {
		LOG.info("Getting Employee with ID: {}.", empId);
		Employee employee = employeeService.getEmployeeById(empId);
		if (employee == null) {
			throw new EmployeeNotFoundException(" EMployee Not Found ");
		}

		return employee;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		LOG.info("Getting all Employees.");
		return employeeService.getAllEmployees();
	}

	@RequestMapping(value = "/delete/{empId}", method = RequestMethod.DELETE)
	public Employee deleteEmployee(@PathVariable String empId) {
		LOG.info("Deleting Employee.");
		return employeeService.deleteEmployee(empId);
	}
	@RequestMapping(value = "/update/{empId}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable String empId,@RequestBody Employee employee) {
		LOG.info("Updating Employee.");
		return employeeService.updateEmployee(empId, employee);
	}

	@RequestMapping(value = "/settings/{empId}", method = RequestMethod.GET)
	public Object getAllEmployeeSettings(@PathVariable String empId) {
		Employee employee = employeeService.getEmployeeById(empId);
		if (employee != null) {
			return employeeService.getAllEmployeeSettings(empId);
		} else {
			return "Employee not found.";
		}
	}

	@RequestMapping(value = "/settings/{empId}/{key}", method = RequestMethod.GET)
	public String getEmployeeSetting(@PathVariable String empId,
			@PathVariable String key) {
		return employeeService.getEmployeeSetting(empId, key);
	}

	@RequestMapping(value = "/settings/{empId}/{key}/{value}", method = RequestMethod.PUT)
	public String addEmployeeSetting(@PathVariable String empId,
			@PathVariable String key, @PathVariable String value) {
		Employee employee = employeeService.getEmployeeById(empId);
		if (employee != null) {
			employee.getEmployeeSettings().put(key, value);
			employeeService.addNewEmployee(employee);
			return "Key added";
		} else {
			return "Employee not found.";
		}
	}

}
