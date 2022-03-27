package com.aman.springjwt.security.services;


import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.aman.springjwt.dao.UserInt;
import com.aman.springjwt.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UserService implements UserServiceInt {
	
	
	@Autowired 
	private UserInt userint;
	@Override
	public boolean addUser(User userBean) {
		return userint.addUser(userBean);
	}
	public boolean changePass(User userBean) {
		return userint.changePass(userBean);
	}
	@Override
	public List<String[]> getUsers() {
		return userint.getUsers();
	}
	public boolean updateUser(String id, String email, String username) {
		return userint.updateUser(id, email, username);
	}
	@Override
	public List<String[]> showUser() {
		return userint.showUser();
	}
    public boolean addRole(String role) {
        return userint.addRole(role);
    }
	@Override
	public boolean deleteUserRole(String id, String role) {
		// TODO Auto-generated method stub
		return  userint.deleteUserRole(id, role);
	}
	@Override
	public boolean addUserRole(String username, String role) {
		// TODO Auto-generated method stub
		return userint.addUserRole(username, role);
	}
	@Override
	public boolean addGoods(String item, String weight, String date, MultipartFile photo) {
		// TODO Auto-generated method stub
		return userint.addGoods(item,weight,date,photo);
	}
	@Override
	public boolean addClient(String name, String number, MultipartFile adphoto, String adexp, MultipartFile panphoto, String panexp) {
		// TODO Auto-generated method stub
		return userint.addClient(name, number, adphoto, adexp, panphoto, panexp);
	}
	@Override
	public boolean addVehicle(String company, String model, String number, String state, MultipartFile Rc, String rcexp) {
		// TODO Auto-generated method stub
		return userint.addVehicle(company, model, number, state, Rc, rcexp);
	}
	@Override
	public List<String[]> showClient(String action) {
		return userint.showClient(action);
	}
	@Override
	public List<String[]> showVehicle(String action) {
		// TODO Auto-generated method stub
		return userint.showVehicle(action);
	}

	@Override
	public List<String[]> showGoods(String action) {
		// TODO Auto-generated method stub
		return userint.showGoods(action);
	}
    public ResultSetMetaData metaData(String string) throws SQLException{
        return userint.metaData(string);
    }
	@Override
	public boolean placeOrder(String clientid, String itemid, String quantity) {
		return userint.placeOrder(clientid,itemid,quantity);
	}
	
}
