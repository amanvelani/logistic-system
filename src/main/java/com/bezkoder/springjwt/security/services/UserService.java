package com.bezkoder.springjwt.security.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.dao.UserInt;
import com.bezkoder.springjwt.models.User;

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
	public boolean addGoods(String item, MultipartFile photo) {
		// TODO Auto-generated method stub
		return userint.addGoods(item,photo);
	}
	@Override
	public boolean addClient(String name, String number, String city, MultipartFile photo) {
		// TODO Auto-generated method stub
		return userint.addClient(name, number, city, photo);
	}
	@Override
	public boolean addVehicle(String company, String model, String number, String state, MultipartFile Rc) {
		// TODO Auto-generated method stub
		return userint.addVehicle(company, model, number, state, Rc);
	}
	@Override
	public List<String[]> showClient() {
		return userint.showClient();
	}
	@Override
	public List<String[]> showVehicle() {
		// TODO Auto-generated method stub
		return userint.showVehicle();
	}

	@Override
	public List<String[]> showGoods() {
		// TODO Auto-generated method stub
		return userint.showGoods();
	}
	
}
