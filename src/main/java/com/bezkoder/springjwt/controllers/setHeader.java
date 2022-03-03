package com.bezkoder.springjwt.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter(urlPatterns = "/*")
public class setHeader extends OncePerRequestFilter {
	
	public String xyz;
	 
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	  		throws ServletException, IOException {
	  	//System.out.println(request.getAttribute("token"));
		 AuthController auth = new AuthController();
		 xyz = auth.print();
		 //System.out.println(xyz);
		 response.addHeader("auth", "Bearer " + xyz);
	  	 filterChain.doFilter(request, response);
	  }
	 
}
