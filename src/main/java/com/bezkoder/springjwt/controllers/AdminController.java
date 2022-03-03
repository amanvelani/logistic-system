package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bezkoder.springjwt.security.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AdminController {
    @Autowired
	private UserService userservice;


    @RequestMapping("/admin/")
	@PreAuthorize("hasRole('ADMIN')")
	public String greeting(){
		return "/admin/greeting.jsp";
	}
	
	
	@RequestMapping("/admin/addUser")
	@PreAuthorize("hasRole('ADMIN')")
	public String addUser(){
		return "/admin/addUser.jsp";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin/addRole")
	public String role(){
		return "addRole.jsp";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin/addUserRole")
	public String userrole(){
		return "addUserRole.jsp";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin/showUser")
	public void  showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin/doAddRole")
	public ModelAndView doAddRole(@RequestParam("role") String role) {
		ModelAndView view = new ModelAndView("register");
			if(!userservice.addRole(role)) {
				view.setViewName("/admin/showUser");
			} else {
					String err = "Role Exists";
					return new ModelAndView("redirect:/admin/showUser");
			}
		return view;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/admin/doAddUserRole")
	public ModelAndView doAddUserRole(@RequestParam("role") String role, @RequestParam("username") String username) {
		ModelAndView view = new ModelAndView("register");
			if(!userservice.addUserRole(username, role)) {
				view.setViewName("/admin/showUser");
			} else {
					String err = "Role Exists";
					return new ModelAndView("redirect:/admin/showUser");
			}
		return view;
	}
	
	@RequestMapping("/admin/update-complete")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView update(@RequestParam("id")String id, @RequestParam("email")String email, @RequestParam("username")String username) {
		ModelAndView view = new ModelAndView("/admin/showUser");
		//System.out.println(id);
		userservice.updateUser(id, email, username);
		return view;
	}
	
	@RequestMapping("/admin/delete-role")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView deleteRole(@RequestParam("id")String id, @RequestParam("roles") String role ) {
		ModelAndView view = new ModelAndView("/admin/showUser");
//		System.out.println(role);
		//userservice.updateUser(id, email, pass, username);
		
		userservice.deleteUserRole(id, role);
		
		return view;
	}
}
