package com.cg.employee_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.employee_application.dao.EmployeeDAO;
import com.cg.employee_application.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDao;

	@Override
	public Employee addNewEmployee(Employee employee) {

		return employeeDao.addNewEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(String empId) {

		return employeeDao.getEmployeeById(empId);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee deleteEmployee(String empId) {

		return employeeDao.deleteEmployee(empId);
	}

	@Override
	public Object getAllEmployeeSettings(String empId) {

		return employeeDao.getAllEmployeeSettings(empId);
	}

	@Override
	public String getEmployeeSetting(String empId, String key) {

		return employeeDao.getEmployeeSetting(empId, key);
	}

	@Override
	public String addEmployeeSetting(String empId, String key, String value) {

		return employeeDao.addEmployeeSetting(empId, key, value);
	}

	@Override
	public Employee updateEmployee(String empId,Employee employee) {
	
		return employeeDao.updateEmployee(empId,employee);
	}

}
