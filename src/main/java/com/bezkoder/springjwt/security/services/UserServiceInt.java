package com.bezkoder.springjwt.security.services;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.User;
@Configuration
public interface UserServiceInt {
	public abstract boolean addUser(User userBean);
	public abstract boolean changePass(User userBean);
	public abstract List<?> getUsers();
	public boolean updateUser(String id, String email, String username);
	public abstract List<?> showUser();
	public abstract boolean addRole(String role);
	public boolean deleteUserRole(String id, String role);
	public boolean addUserRole(String username, String role);
	public boolean addGoods(String item, MultipartFile photo);
	public boolean addClient(String name,String number,String city, MultipartFile photo);
	public boolean addVehicle(String company, String model, String number,String state, MultipartFile Rc);
	public abstract List<?> showClient();
	public abstract List<?> showVehicle();
	public abstract List<?> showGoods();





//	public abstract List<?> getRole();


}
