package com.bezkoder.springjwt.security.jwt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bezkoder.springjwt.controllers.AuthController;
import com.bezkoder.springjwt.controllers.setHeader;
import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;

@WebFilter(urlPatterns = "/api/test/*")
public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  
//  @Autowired
//  private HttpServletRequest request;
  
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
//	  
//	  	 AuthController auth = new AuthController();
//	  	 String xyz = auth.print();
//		 System.out.println(xyz);
//		 response.setHeader("authorization", "Bearer " + xyz);
//	  	 filterChain.doFilter(request, response);
	  
	  try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	        String username = jwtUtils.getUserNameFromJwtToken(jwt);
	
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken authentication =
	            new UsernamePasswordAuthenticationToken(
	                userDetails,
	                null,
	                userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	
	        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) throws IOException {  
	
	//System.out.println(headerAuth);
	
	if(request.getRequestURI().equals("/auth/signin")) {
		return null;
	}
	else {
		AuthController auth = new AuthController();
		String headerAuth = "Bearer "+auth.print();
	    //System.out.println("AuthTokenFilter: " + headerAuth);
	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {	
	      return headerAuth.substring(7, headerAuth.length());
	    }
	}
	return null;
  }
//  private String getAllHeaders(HttpServletRequest req) throws IOException {
////	  URL main_url = new URL(req.getRequestURL().toString());
////	  HttpURLConnection conn = (HttpURLConnection) main_url.openConnection();
////	  
////	  conn.connect();
////	  System.out.println(conn);
////      Map<String, List<String>> headerFields = conn.getHeaderFields();
////
////      Set<String> headerFieldsSet = headerFields.keySet();
////      Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
////       
////      while (hearerFieldsIter.hasNext()) {
////           
////           String headerFieldKey = hearerFieldsIter.next();
////           List<String> headerFieldValue = headerFields.get(headerFieldKey);
////            
////           StringBuilder sb = new StringBuilder();
////           for (String value : headerFieldValue) {
////               sb.append(value);
////               sb.append("");
////          }
////            
////           System.out.println(headerFieldKey + "=" + sb.toString());
////           
////           
////      }
////      String token = conn.getHeaderField("auth");
////      conn.disconnect();
////      if(token==null) {
////    	  return "error";
////      }
////      else return token;
//	  
//	  try {
//			URL obj = new URL("http://localhost:8080/auth/signin");
//			URLConnection conn = obj.openConnection();
////		    conn.setRequestMethod("POST");
////		    conn.setRequestProperty( "Content-type", "application/x-www-form-urlencoded");
////		    conn.setRequestProperty( "Accept", "*/*" );
//			Map<String, List<String>> map = conn.getHeaderFields();
//
//			System.out.println("Printing All Response Header for URL: " + obj.toString() + "\n");
//			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
//			}
//			
//			System.out.println("\nGet Response Header By Key ...\n");
//			List<String> contentLength = map.get("Content-Length");
//			if (contentLength == null) {
//				System.out.println("'Content-Length' doesn't present in Header!");
//				return "error";
//			} else {
//				for (String header : contentLength) {
//					System.out.println("Content-Lenght: " + header);
//				}
//				return "true";
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	  
//	  return null;
//	  
//	  
//  }
}
