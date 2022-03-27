package com.aman.springjwt.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import com.aman.springjwt.models.Role;
import com.aman.springjwt.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;
 
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
	public boolean addGoods(String item, String weight, String date, MultipartFile photo) {
		try {
			byte[] photoBytes = photo.getBytes();
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
			String sql = "INSERT INTO goods(`Item Name`, `Item Weight`, `Item Expiry`, `Item Photo`, `Approved`) VALUES (?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { item, weight, date, photoBytes, 0});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean addClient(String name, String number, MultipartFile adphoto,String adexp, MultipartFile panphoto, String panexp) {
		try {
			byte[] adphotoBytes = adphoto.getBytes();
			byte[] panphotoBytes = panphoto.getBytes();
			
			String sql = "INSERT INTO client(  `Client Name`, `Client Number`,`Aadhar Verification`,`Aadhar Expiry`,`Pan Verification`,`Pan Expiry`) VALUES (?,?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { name, number, adphotoBytes, adexp, panphotoBytes, panexp});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean addVehicle(String company, String model, String number, String state, MultipartFile rc, String rcexp) {
		try {
			byte[] photoBytes = rc.getBytes();
			
			String sql = "INSERT INTO vehicle(`Vehicle Company`, `Vehicle Model`, `Vehicle Number`, `Vehicle Registration State`, `Vehicle RC Book`, `RC Expiry`) VALUES (?,?,?,?,?,?)";
			jdbcTemplate.update(sql, new Object[] { company, model, number, state, photoBytes,rcexp});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<String[]> showClient(String actionString) {
		
		if(actionString.equals("notapp")){
		List<String[]> data = jdbcTemplate.query(
			    "SELECT `Client Id`, `Client Name`, `Client Number`, `Aadhar Expiry`, `Pan Expiry`, `Client Approved`  " + 
			    "FROM client where `Client Approved` = 0", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});

				return data;
		}
		else{
			List<String[]> data = jdbcTemplate.query(
			    "SELECT `Client Id`, `Client Name`, `Client Number`,`Aadhar Expiry`, `Pan Expiry`, `Client Approved`  " + 
			    "FROM client where `Client Approved` = 1", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});

				return data;
		}
	}

	@Override
	public List<String[]> showVehicle(String action) {
		
		if(action.equals("notapp")){

		List<String[]> data = jdbcTemplate.query(
			    "SELECT `Vehicle Id`, `Vehicle Company`, `Vehicle Model`, `Vehicle Number`, `Vehicle Registration State`, `Vehicle Approval`, `RC Expiry`, `Vehicle Capacity`  " + 
			    "FROM vehicle where `Vehicle Approval` = 0", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
				return data;
		}
		else{
			List<String[]> data = jdbcTemplate.query(
			    "SELECT `Vehicle Id`, `Vehicle Company`, `Vehicle Model`, `Vehicle Number`, `Vehicle Registration State`, `Vehicle Approval`, `RC Expiry`, `Vehicle Capacity`  " + 
			    "FROM vehicle where `Vehicle Approval` = 1", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8)});
			return data;
		}	

	}

	@Override
	public List<String[]> showGoods(String action) {
		Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format(date);
		System.out.println(str);
		if(action.equals("notapp")){
		List<String[]> data = jdbcTemplate.query(
			    "SELECT `Item Id`, `Item Name`, `Item Weight`, `Item Expiry`, `Approved`,  `Item Quantity`  " + 
			    "FROM goods where `Approved` = 0", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
		
		return data;
		}
		else if(action.equals("approved_dt")){
			List<String[]> data = jdbcTemplate.query(
			    "SELECT `Item Id`, `Item Name`, `Item Weight`, `Item Expiry`, `Item Quantity`  " + 
			    "FROM goods where `Approved`= 1 and `Item Expiry`>'"+str+"'", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
		
		return data;
		}
		else{
			List<String[]> data = jdbcTemplate.query(
			    "SELECT `Item Id`, `Item Name`, `Item Weight`, `Item Expiry`, `Approved`, `Item Quantity`  " + 
			    "FROM goods where `Approved`= 1", 
			    (rs, rowNum) -> new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
		
		return data;
		}
	}
	
	@Override
	public ResultSetMetaData metaData(String table) throws SQLException{
			System.out.println(table);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/new_db","root","12345678");
				System.out.println("Connection established......");
				Statement stmt = con.createStatement();
				String query =  "select * from " + table;
				ResultSet rs = stmt.executeQuery(query);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				System.out.println("List of column names in the current table: ");
				return rsMetaData;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
		
		return null;
	
		
	}

	@Override
	public boolean placeOrder(String clientid, String itemid, String quantity) {
		// select Item Quantity from goods where Item Id = itemid
		// if(quantity<above)
		// Insert into client_order
		// update goods
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/new_db","root","12345678");
			System.out.println("Connection established......");
			String query = "select * from goods where `Item Id` = '"+itemid+"'";
			String query1 = "Insert into client_order(`Client Id`,`Item Id`, `Item Quantity`) values ('"+clientid+"','"+itemid+"','"+quantity+"')";
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int table_quantity = Integer.parseInt(rs.getString("Item Quantity"));
				int required_quantity = Integer.parseInt(quantity);
				if(required_quantity<table_quantity){
					String update = Integer.toString(table_quantity-required_quantity);
					rs.updateString("Item Quantity", update);
					rs.updateRow();
					stmt.executeUpdate(query1);
					return false;
				}
				else{
					return true;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void connection() throws SQLException, ClassNotFoundException{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/new_db","root","12345678");
				System.out.println("Connection established......");
	}
}