package com.lister.Project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lister.Project.domain.Employee;

@SuppressWarnings({ "deprecation" })
public class EmployeeDaoReadTest {
	BasicDataSource dataSource;
	private Resource rtest;
	private BeanFactory factorytest;
	Connection conn;
	
	public  EmployeeDaoReadTest(){
		rtest=new ClassPathResource("testconfig.xml");  	
	    factorytest=new XmlBeanFactory(rtest);
	    dataSource=(BasicDataSource)factorytest.getBean("dataSource");
	    try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveEmployee(Employee e){
		try {
			PreparedStatement ps=conn.prepareStatement("Insert into employee_kroger(id,name,salary) values(?,?,?)");
			ps.setInt(1, 22000);
			ps.setString(2, e.getName());
			ps.setFloat(3, e.getSalary());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Employee> getEmployees(){
		List<Employee> le=new ArrayList<Employee>();
		try {
			PreparedStatement ps=conn.prepareStatement("Select * from employee_kroger");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Employee e=new Employee();
				e.setId(Integer.parseInt(rs.getString(1)));
				e.setName(rs.getString(2));
				e.setSalary(Float.parseFloat(rs.getString(3)));
				le.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return le;
	}
	
	public void rollback() throws SQLException{
		conn.rollback();
		conn.close();
	}
}
