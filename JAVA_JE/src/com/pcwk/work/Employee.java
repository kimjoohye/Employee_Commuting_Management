package com.pcwk.work;

public class Employee {
	private String employeeNum;
	private String employeeName;
	private String checkStatus;

	public Employee() {};
	
	public Employee(String employeeNum, String employeeName, String checkStatus) {
		super();
		this.employeeNum = employeeNum;
		this.employeeName = employeeName;
		this.checkStatus = checkStatus;
	}

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return "Employee [employeeNum=" + employeeNum + ", employeeName=" + employeeName + ", checkStatus="
				+ checkStatus + "]";
	}
	
	
}
