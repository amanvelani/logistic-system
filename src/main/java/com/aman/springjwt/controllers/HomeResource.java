package com.aman.springjwt.controllers;

import java.io.IOException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("session")
public class HomeResource {

	@Autowired
	private UserService userservice;
	
	
    @GetMapping("/")
    public String home() {
        return ("home.jsp");
    }
	
	 @GetMapping("/home")
	    public String home1() {
	        return ("home.jsp");
	    }

    @RequestMapping("/user/welcome")
    public ModelAndView welcomePage() {

    	ModelAndView mv = new ModelAndView("welcome");

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println(currentPrincipalName);
    	
    	mv.addObject("session", currentPrincipalName);
    	mv.setViewName("/user/welcome.jsp");
    	
    	
    	return mv;
    	
    }
    @RequestMapping("/login")
    public String loginPage(Model model) {
//    	model.addAttribute("userBean", new UserBean());
    	return "login.jsp";
    }
////    @RequestMapping("/user/dologin")
////    public ModelAndView doLogin(@ModelAttribute @Valid UserBean userBean,BindingResult result) {
////		
////		return new ModelAndView("redirect:/user/welcome");
////	}
    @RequestMapping("/logout-success")
    public String logoutPage() {
    	return "home.jsp";
    }
    
    // @RequestMapping("/admin/register")
    // public String toRegister(Model model) {
	// 	model.addAttribute("userBean", new UserBean());
	// 	return "register.jsp";
	// }
	
//	
//	@RequestMapping("/manager/update-complete")
//	public ModelAndView updateUser(@ModelAttribute @Valid UserBean userBean,BindingResult result,RedirectAttributes redirectAttributes) {
//		ModelAndView view = new ModelAndView("update");
//		// If input bean does not have any validation error then proceed
//		if(!result.hasFieldErrors()) {
//			// If not a valid user then add error
//			// else proceed to user welcome page
//			if(!userservice.updateUser(userBean)) {
//				//view.remove("session");
//				//return new ModelAndView("redirect:/admin/register");
//				return new ModelAndView("redirect:/manager/showUser");
//			}
//		}
//		return view;
//	}
	// @RequestMapping("/admin/update")
	// public String update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	// 	String id = request.getParameter("user_id");
	// 	String username = request.getParameter("username");
	// 	String role = request.getParameter("role");
	// 	String enabled = request.getParameter("enabled");
	// 	response.setHeader(name, value);

	// 	//System.out.println(id + username + role + enabled);
		
		
	// 	return "/admin/showUser";
	// }
	
	@RequestMapping("/master")
	public String showmaster() {
		return "/master/select.jsp";
	}
	@RequestMapping("/master/goods")
	public void  showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showGoods("approved");
		ResultSetMetaData rs = userservice.metaData("goods");
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
		
		RequestDispatcher rd = request.getRequestDispatcher("goods.jsp");
		 rd.forward(request, response);
	}

	@RequestMapping("/master/addGoods")
	public ModelAndView addgoods(@RequestParam("itemname") String item, @RequestParam("weight") String weight, @RequestParam("date") String date, @RequestPart("photo") MultipartFile photo) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addGoods(item,weight,date,photo)) {
			mv.setViewName("/manager/");
		}
		else {
			mv.setViewName("/master/goods");
		}
		return mv;
		
	}
	@RequestMapping("/master/client")
	public void  addClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List<String[]> lt =  userservice.showClient("approved");
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
		
		RequestDispatcher rd = request.getRequestDispatcher("client.jsp");
		 rd.forward(request, response);
	}

	@RequestMapping("/master/addClient")
	public ModelAndView addClient(@RequestParam("name") String name, @RequestParam("number") String number, @RequestPart("adphoto") MultipartFile adphoto, @RequestParam("adexp") String adexp, @RequestPart("panphoto") MultipartFile panphoto,  @RequestParam("panexp") String panexp) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addClient(name, number,adphoto, adexp, panphoto, panexp)) {
			mv.setViewName("/manager/");
		}
		else {
			mv.setViewName("/master/client");
		}
		return mv;
		
	}


	@RequestMapping("/master/vehicle")
	public void  vehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showVehicle("approved");
		ResultSetMetaData rs = userservice.metaData("vehicle");
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
		
		RequestDispatcher rd = request.getRequestDispatcher("vehicle.jsp");
		 rd.forward(request, response);
	}
	
	@RequestMapping("/master/addVehicle")
	public ModelAndView addVehicle(@RequestParam("company") String company, @RequestParam("model") String model ,@RequestParam("number") String number, @RequestParam("regstate") String state, @RequestPart("photo") MultipartFile Rc,  @RequestParam("rcexp") String rcexp) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addVehicle(company, model, number, state , Rc, rcexp)) {
			mv.setViewName("/manager/");
		}
		else {
			mv.setViewName("/master/client");
		}
		return mv;
		
	}

}


