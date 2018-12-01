package com.cg.employee_application.service;

import java.util.List;

import com.cg.employee_application.entity.Employee;

public interface EmployeeService {
	Employee addNewEmployee(Employee employee);

	Employee getEmployeeById(String empId);

	List<Employee> getAllEmployees();

	Employee deleteEmployee(String empId);

	Object getAllEmployeeSettings(String empId);

	String getEmployeeSetting(String empId, String key);

	String addEmployeeSetting(String empId, String key, String value);

	Employee updateEmployee(String empId,Employee employee);

}
