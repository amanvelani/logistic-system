package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.security.services.UserService;

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
        return ("index.html");
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
    	return "index.html";
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
	public String showGoods() {
		return "goods.jsp";
	}
	@RequestMapping("/master/addGoods")
	public ModelAndView addgoods(@RequestParam("itemname") String item, @RequestPart("photo") MultipartFile photo) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addGoods(item,photo)) {
			mv.setViewName("/master/");
		}
		else {
			mv.setViewName("/master/goods");
		}
		return mv;
		
	}
	
	@RequestMapping("/master/client")
	public String addClient() {
		return "client.jsp";
	}
	@RequestMapping("/master/addClient")
	public ModelAndView addClient(@RequestParam("name") String name, @RequestParam("number") String number,@RequestParam("city") String city, @RequestPart("photo") MultipartFile photo) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addClient(name, number, city ,photo)) {
			mv.setViewName("/master/");
		}
		else {
			mv.setViewName("/master/client");
		}
		return mv;
		
	}
	
	@RequestMapping("/master/vehicle")
	public String vehicle() {
		return "vehicle.jsp";
	}
	
	@RequestMapping("/master/addVehicle")
	public ModelAndView addVehicle(@RequestParam("company") String company, @RequestParam("model") String model ,@RequestParam("number") String number, @RequestParam("regstate") String state, @RequestPart("photo") MultipartFile Rc) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.addVehicle(company, model, number, state , Rc)) {
			mv.setViewName("/master/");
		}
		else {
			mv.setViewName("/master/client");
		}
		return mv;
		
	}

}


