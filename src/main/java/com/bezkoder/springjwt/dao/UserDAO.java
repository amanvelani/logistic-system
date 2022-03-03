package com.bezkoder.springjwt.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
 
public class UserDAO implements UserInt {
	
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
    DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean addUser(User userBean) {
		boolean userExists = false;
		String uname = userBean.getUsername();
		String pass = userBean.getPassword();
		Set<Role> ro = userBean.getRoles();
		String en = userBean.getEmail();
		//String ph = userBean.getPhone();
		int rowcount =jdbcTemplate.queryForObject("select count(*) from users " +
				" where username = ?",new Object[] { userBean.getUsername()}, Integer.class
				);
		System.out.println(rowcount + uname +pass +ro+en);
		if(rowcount>=1 || uname == null || pass.length() < 6 || en == null || ro == null){
			userExists = true;
			return userExists;
		}
		else {
			
				jdbcTemplate.execute("INSERT INTO users(username, password, role, enabled ) " + "VALUES ('"+uname+"','"+pass+"','"+ro+"','"+en+"')");
				userExists = false;
		}
		
		return userExists;
	}

	
	@Override
	public boolean changePass(User userBean) {
		boolean userExists = false;
		String uname = userBean.getUsername();
		String pass = userBean.getPassword();
		int rowcount = jdbcTemplate.queryForObject("select count(*) from users " +
				" where username = ? and password = ?",new Object[] { userBean.getUsername() }, Integer.class
				);
		
		if(rowcount == 1) {
			jdbcTemplate.execute("UPDATE users SET password = '"+pass+"' WHERE username = '"+uname+"'");
			userExists = false;
		}
		else {
			userExists = true;
		}
		
		return userExists;
	}
	
	public List<String[]> getUsers() {
		String r1 = "ROLE_USER";
		String r2 = "ROLE_MANAGER";
		List<String[]> data = jdbcTemplate.query(
			    "select id, email, password, username from users", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
		
//		for(String[] l : data) {
//			System.out.println(Arrays.toString(l));
//		}
		//System.out.println(data);
		
		return data;
		
	}

	

	@Override
	public boolean updateUser(String id, String email, String username) {
		boolean userExists = false;
		String uid = id;
		String user = username.trim();
		String uemail = email.trim();;
//		String pass = password;

		//		String ro = userBean.getRole().trim();
//		String en = userBean.getEnabled().trim();
		//String ph = userBean.getPhone();
		
//			
		jdbcTemplate.execute("UPDATE users SET username='"+user+"', email='"+uemail+"' where id = '"+uid+"'");
		userExists = false;
		
		return userExists;
	}

	@Override
	public List<String[]> showUser() {
		List<String[]> data = jdbcTemplate.query(
			    "SELECT users.id, users.email, users.username, user_roles.role_id " + 
			    "FROM users " + 
			    "LEFT JOIN user_roles " + 
			    "ON users.id = user_roles.user_id;  ", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
		
		return data;
	}

	@Override
	public boolean addRole(String role) {
		boolean userExists = false;
		String rname = role.trim();
		@SuppressWarnings("deprecation")
		int rowcount =jdbcTemplate.queryForObject("select count(*) from roles " +
				" where name = ?",new Object[] {role}, Integer.class
				);
		System.out.println(rowcount + rname);
		if(rowcount>=1 || rname == null){
			userExists = true;
			return userExists;
		}
		else {
			
				jdbcTemplate.execute("INSERT INTO roles(name) " + "VALUES ('"+rname+"')");
				userExists = false;
		}
		
		return userExists;
	}

	@Override
	public boolean deleteUserRole(String id, String role) {
		
		
		
		jdbcTemplate.execute("DELETE FROM user_roles WHERE user_roles.user_id = '"+id+"' AND user_roles.role_id = '"+role+"' ");
		
		return false;
	}

	@Override
	public boolean addUserRole(String username, String role) {
		boolean userExists = false;
		
		@SuppressWarnings("deprecation")
		int rowcount =jdbcTemplate.queryForObject("select count(*) from user_roles " +
				" where user_id = ? and role_id = ?",new Object[] {username, role}, Integer.class
				);
		
		if(rowcount>=1){
			userExists = true;
			return userExists;
		}
		else {
			
			jdbcTemplate.execute("INSERT INTO user_roles(user_id, role_id) " + "VALUES ('"+username+"', '"+role+"')");
			userExists = false;
		}	
		
		return false;
	}

	@Override
	public boolean addGoods(String item, MultipartFile photo) {
		try {
			byte[] photoBytes = photo.getBytes();
			Date currentDate = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
			String dateOnly = dateFormat.format(currentDate);
			String sql = "INSERT INTO goods(item_name,item_date,item_photo,accepted) VALUES (?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { item, dateOnly, photoBytes, 0});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean addClient(String name, String number, String city, MultipartFile photo) {
		try {
			byte[] photoBytes = photo.getBytes();
			
			String sql = "INSERT INTO client(client_name,client_number, client_identification) VALUES (?,?,?)";
			jdbcTemplate.update(sql, new Object[] { name, number, photoBytes});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean addVehicle(String company, String model, String number, String state, MultipartFile rc) {
		try {
			byte[] photoBytes = rc.getBytes();
			
			String sql = "INSERT INTO vehicle(vehicle_company, vehicle_model, vehicle_number, reg_state, vehicle_rc) VALUES (?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { company, model, number, state, photoBytes});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<String[]> showClient() {
		List<String[]> data = jdbcTemplate.query(
			    "SELECT id_client, client_name, client_number, client_activated  " + 
			    "FROM client", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
		
		return data;
	}

	@Override
	public List<String[]> showVehicle() {
		List<String[]> data = jdbcTemplate.query(
			    "SELECT idvehicle, vehicle_company, vehicle_model, vehicle_number, reg_state, vehicle_approved  " + 
			    "FROM vehicle", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)});
		
		return data;
	}

	@Override
	public List<String[]> showGoods() {
		List<String[]> data = jdbcTemplate.query(
			    "SELECT id, item_name, item_date, accepted  " + 
			    "FROM goods", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
		
		return data;
	}
	
}