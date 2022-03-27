package com.aman.springjwt.dao;

 
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.aman.springjwt.models.User;

import org.springframework.web.multipart.MultipartFile;
 

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
	public abstract boolean addGoods(String item,String weight, String date, MultipartFile photo);
	public abstract boolean addClient(String name, String number, MultipartFile adphoto,String adexp, MultipartFile panphoto, String panexp);
	public abstract boolean addVehicle(String company, String model, String number, String state, MultipartFile rc, String rcexp);
	public abstract List<String[]> showClient(String action);
	public abstract List<String[]> showVehicle(String action);
	public abstract List<String[]> showGoods(String action);
    public abstract ResultSetMetaData metaData(String string) throws SQLException;
	public abstract boolean placeOrder(String clientid, String itemid, String quantity);



}