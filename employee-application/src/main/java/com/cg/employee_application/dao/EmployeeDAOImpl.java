package com.cg.employee_application.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cg.employee_application.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Employee addNewEmployee(Employee employee) {
		/*
		 * if(employee.getJoiningDate()==null){ Date date=new Date();
		 * employee.setJoiningDate(date); }
		 */
		mongoTemplate.save(employee);

		return employee;
	}

	@Override
	public Employee getEmployeeById(String empId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		return mongoTemplate.findOne(query, Employee.class);

	}

	@Override
	public List<Employee> getAllEmployees() {

		return mongoTemplate.findAll(Employee.class);
	}

	@Override
	public Employee deleteEmployee(String empId) {
		Employee employee = getEmployeeById(empId);
		if (employee != null) {
			mongoTemplate.remove(employee);
		}

		return employee;
	}

	@Override
	public Object getAllEmployeeSettings(String empId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		Employee employee = mongoTemplate.findOne(query, Employee.class);
		return employee != null ? employee.getEmployeeSettings()
				: "Employee not found.";

	}

	@Override
	public String getEmployeeSetting(String empId, String key) {

		Query query = new Query();
		query.fields().include("EmployeeSettings");
		query.addCriteria(Criteria
				.where("empId")
				.is(empId)
				.andOperator(
						Criteria.where("employeeSettings." + key).exists(true)));
		Employee employee = mongoTemplate.findOne(query, Employee.class);
		return employee != null ? employee.getEmployeeSettings().get(key)
				: "Not found.";
	}

	@Override
	public String addEmployeeSetting(String empId, String key, String value) {

		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		Employee employee = mongoTemplate.findOne(query, Employee.class);
		if (employee != null) {
			employee.getEmployeeSettings().put(key, value);
			mongoTemplate.save(employee);
			return "Key added.";
		} else {
			return "Employee not found.";
		}
	}

	@Override
	public Employee updateEmployee(String empId, Employee employee) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		employee.setEmpId(empId);
		mongoTemplate.findOne(query, Employee.class);
		mongoTemplate.save(employee);
		return employee;

	}

}
