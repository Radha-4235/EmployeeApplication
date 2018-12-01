package com.cg.employee_application.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Document
public class Employee {
	@Id
	private String empId;
	@NotNull
	@Size(min = 4, message = "Employee name should contain atleast 4 characters")
	private String empName;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date joiningDate=new Date();
	
	private Map<String, String> employeeSettings = new HashMap<>();

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Map<String, String> getEmployeeSettings() {
		return employeeSettings;
	}

	public void setEmployeeSettings(Map<String, String> employeeSettings) {
		this.employeeSettings = employeeSettings;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName
				+ ", joiningDate=" + joiningDate + ", employeeSettings="
				+ employeeSettings + "]";
	}

}
