package com.bezkoder.springjwt.dao;

 
import java.util.List;

import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
 

public interface UserInt {
	public abstract void setDataSource(DataSource dataSource);
	public abstract boolean addUser(User userBean);
	public abstract boolean changePass(User userBean);
	public abstract List<String[]> getUsers();
	public boolean updateUser(String id, String email, String username);
	public abstract List<String[]> showUser();
	public abstract boolean addRole(String role);
	public boolean deleteUserRole(String id, String role);
	public abstract boolean addUserRole(String username, String role);
	public abstract boolean addGoods(String item, MultipartFile photo);
	public abstract boolean addClient(String name, String number, String city, MultipartFile photo);
	public abstract boolean addVehicle(String company, String model, String number, String state, MultipartFile rc);
	public abstract List<String[]> showClient();
	public abstract List<String[]> showVehicle();
	public abstract List<String[]> showGoods();



}