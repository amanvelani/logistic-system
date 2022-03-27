package com.aman.springjwt.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aman.springjwt.security.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {
    @Autowired
	private UserService userservice;



	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/")
	public String manager(){
		return "/manager/greeting.jsp";
	}

	@RequestMapping("/manager/addUser")
	@PreAuthorize("hasRole('MODERATOR')")
	public String add(){
		return "/manager/addUser.jsp";
	}

	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/showUser")
	public void  getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String[]> lt =  userservice.showUser();
		String[] array = new String[lt.size()];
		int i = 0;
		for(String[] l : lt) {
			array[i] = Arrays.toString(l);
			i++;
		}
		for(int b=0; b<lt.size(); b++) {
			System.out.println(array[b]);
		}
		request.setAttribute("data", array);
		
		RequestDispatcher rd = request.getRequestDispatcher("showUser.jsp");
		 rd.forward(request, response);
	}
	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/update-complete")
	public ModelAndView updateUser(@RequestParam("id")String id, @RequestParam("email")String email, @RequestParam("username")String username) {
		ModelAndView view = new ModelAndView("/manager/showUser");
		//System.out.println(id);
		userservice.updateUser(id, email, username);
		return view;
	}

	@RequestMapping("/manager/delete-role")
	@PreAuthorize("hasRole('MODERATOR')")
	public ModelAndView deleteRoleM(@RequestParam("id")String id, @RequestParam("roles") String role ) {
		ModelAndView view = new ModelAndView("/manager/showUser");
		userservice.deleteUserRole(id, role);
		
		return view;
	}

    @PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/addUserRole")
	public String userrole(){
		return "addUserRole.jsp";
	}

	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/doAddUserRole")
	public ModelAndView doAddUserRole(@RequestParam("role") String role, @RequestParam("username") String username) {
		ModelAndView view = new ModelAndView("register");
			if(!userservice.addUserRole(username, role)) {
				view.setViewName("/manager/showUser");
			} else {
					String err = "Role Exists";
					return new ModelAndView("redirect:/manager/showUser");
			}
		return view;
	}
	
	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/showClient")
	public void  getClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showClient("notapp");
		ResultSetMetaData rs = userservice.metaData("client");
		List<String> meta = new ArrayList<String>();
		int count = rs.getColumnCount();
			for(int i = 1; i<=count; i++) {
				String a = rs.getColumnName(i);
				meta.add(a);
			}
		String listString = String.join(", ", meta);

		System.out.println(listString);
		String[] array = new String[lt.size()];
		int i = 0;
		for(String[] l : lt) {
			array[i] = Arrays.toString(l);
			i++;
		}
		for(int b=0; b<lt.size(); b++) {
			//System.out.println(array[b]);
		}
		request.setAttribute("metaData", listString);
		request.setAttribute("data", array);
		
		RequestDispatcher rd = request.getRequestDispatcher("masterClient.jsp");
		 rd.forward(request, response);
	}

	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/showVehicle")
	public void  getVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showVehicle("notapp");
		String[] array = new String[lt.size()];
		ResultSetMetaData rs = userservice.metaData("vehicle");
		List<String> meta = new ArrayList<String>();
		int count = rs.getColumnCount();
			for(int i = 1; i<=count; i++) {
				String a = rs.getColumnName(i);
				meta.add(a);
			}
		String listString = String.join(", ", meta);
		int i = 0;
		for(String[] l : lt) {
			array[i] = Arrays.toString(l);
			i++;
		}
		request.setAttribute("metaData", listString);
		request.setAttribute("data", array);
		
		RequestDispatcher rd = request.getRequestDispatcher("masterVehicle.jsp");
		 rd.forward(request, response);
	}
	@PreAuthorize("hasRole('MODERATOR')")
	@RequestMapping("/manager/showGoods")
	public void  getGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showGoods("notapp");
		String[] array = new String[lt.size()];
		ResultSetMetaData rs = userservice.metaData("goods");
		List<String> meta = new ArrayList<String>();
		int count = rs.getColumnCount();
			for(int i = 1; i<=count; i++) {
				String a = rs.getColumnName(i);
				meta.add(a);
			}
		String listString = String.join(", ", meta);
		int i = 0;
		for(String[] l : lt) {
			array[i] = Arrays.toString(l);
			i++;
		}
		for(int b=0; b<lt.size(); b++) {
			System.out.println(array[b]);
		}
		request.setAttribute("metaData", listString);
		request.setAttribute("data", array);
		
		RequestDispatcher rd = request.getRequestDispatcher("masterGoods.jsp");
		 rd.forward(request, response);
	}

    

	@PreAuthorize("hasRole('MODERATOR')")
	@GetMapping("/manager/approve-client")
	public void approveClient(@RequestParam String id, HttpServletResponse response) throws SQLException, IOException{
		Connection con = connection();
		String accept = "1";
		PreparedStatement stmt=con.prepareStatement("update client set `Client Approved`='"+accept+"' where `Client Id`='"+id+"'");  
		stmt.executeUpdate();
		System.out.println(con);

		response.sendRedirect("/manager/showClient");

	}

	@PreAuthorize("hasRole('MODERATOR')")
	@GetMapping("/manager/approve-vehicle")
	public void approveVehicle(@RequestParam String id, HttpServletResponse response) throws SQLException, IOException{
		Connection con = connection();
		String accept = "1";
		PreparedStatement stmt=con.prepareStatement("update vehicle set `Vehicle Approval`='"+accept+"' where `Vehicle Id`='"+id+"'");  
		stmt.executeUpdate();
		System.out.println(con);

		response.sendRedirect("/manager/showVehicle");

	}

	@PreAuthorize("hasRole('MODERATOR')")
	@GetMapping("/manager/approve-goods")
	public void approveGoods(@RequestParam String id, HttpServletResponse response) throws SQLException, IOException{
		Connection con = connection();
		String accept = "1";
		PreparedStatement stmt=con.prepareStatement("update goods set `Approved`='"+accept+"' where `Item Id`='"+id+"'");  
		stmt.executeUpdate();
		System.out.println(con);

		response.sendRedirect("/manager/showGoods");

	}


	private Connection connection() throws SQLException{
		Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/new_db";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pass = "12345678";

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            if (conn == null) {
                System.out.println("Connection cannot be established");
            }       
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
      }  

}
