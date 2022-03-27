package com.aman.springjwt.security.services;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.aman.springjwt.models.User;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
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
	public boolean addGoods(String item, String weight, String date, MultipartFile photo);
	public boolean addClient(String name,String number, MultipartFile adphoto, String adexp, MultipartFile panphoto, String panexp);
	public boolean addVehicle(String company, String model, String number,String state, MultipartFile Rc, String rcexp);
	public abstract List<?> showClient(String action);
	public abstract List<?> showVehicle(String action);
	public abstract List<?> showGoods(String action);
	public abstract ResultSetMetaData metaData(String string) throws SQLException;
	public boolean placeOrder(String clientid, String itemid, String quantity);






	// public abstract List<?> getRole();


}
