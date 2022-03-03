package com.bezkoder.springjwt.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Blob;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {
    
    
    @GetMapping(value="/manager/showVehicleImage")
	public void getMethodName(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/new_db","root","12345678");
		String query = "select vehicle_rc from vehicle " +
		"where idvehicle = '"+id+"'";
		PreparedStatement preparedStatement = null;
 
			//create preparedStatement
			preparedStatement = 
				conn.prepareStatement(query);
 
			//execute query
			ResultSet resultSet = 
				preparedStatement.executeQuery();
			resultSet.next();  
 
			Blob clob = (Blob) resultSet.getBlob(1);
			byte[] byteArr =  
				clob.getBytes(1,(int)clob.length());
 
			FileOutputStream fileOutputStream = 
			   new FileOutputStream("/Users/AV/Documents/spring-boot-spring-security-jwt-authentication-master/src/main/webapp/images/vehicle/"+id+".jpg");
			fileOutputStream.write(byteArr);  
 
		     System.out.println("Image retrieved successfully.");
 
			//close connection
			fileOutputStream.close();
			preparedStatement.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewVehicleImage.jsp");
		rd.forward(request, response);
	}

    @GetMapping(value="/manager/showGoodsImage")
	public void getGoodsImage(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection conn=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/new_db","root","12345678");
		String query = "select item_photo from goods " +
		"where id = '"+id+"'";
		PreparedStatement preparedStatement = null;
 
			//create preparedStatement
			preparedStatement = 
				conn.prepareStatement(query);
 
			//execute query
			ResultSet resultSet = 
				preparedStatement.executeQuery();
			resultSet.next();  
 
			Blob clob = (Blob) resultSet.getBlob(1);
			byte[] byteArr =  
				clob.getBytes(1,(int)clob.length());
 
			FileOutputStream fileOutputStream = 
			   new FileOutputStream("/Users/AV/Documents/spring-boot-spring-security-jwt-authentication-master/src/main/webapp/images/goods/"+id+".jpg");
			fileOutputStream.write(byteArr);  
 
		     System.out.println("Image retrieved successfully.");
 
			//close connection
			fileOutputStream.close();
			preparedStatement.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("viewGoodsImage.jsp");
		rd.forward(request, response);
	}
}
