package com.picta.struts2;

import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HelloWorldAction {
	private String name;
	EmployeeDAO employee = new EmployeeDAO();
   private List<Employee> employees = employee.getEmployees();

	public String execute() throws Exception {
		return "success";
	}

   public List<Employee> getEmployees() {
      for (Employee employee: this.employees) {
         System.out.println("First name:"  + employee.getFirstName() + " | Last name:" + employee.getLastName());
      }

      return this.employees;
   }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
