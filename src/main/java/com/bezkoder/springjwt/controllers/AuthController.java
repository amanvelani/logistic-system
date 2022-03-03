package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Component
public class AuthController{
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  
  static String jwt;
  
  @SuppressWarnings("rawtypes")
  @PostMapping("/signin")
  public ResponseEntity authenticateUser(@RequestParam("username") String user,@RequestParam("password") String pass, HttpServletRequest request, HttpServletResponse response) throws IOException{

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user, pass));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    System.out.println(jwt);
//    HttpSession s = request.getSession();
//    s.setAttribute("abcd", jwt);
    HttpHeaders header = new HttpHeaders();
    header.setBearerAuth(jwt);
//    response.addHeader("Authorization" , "Bearer "+jwt);
//    filterChain.doFilter(request, response);
    if(roles.contains("ROLE_ADMIN")){
      response.sendRedirect("/admin/");
    }
    else if(roles.contains("ROLE_MODERATOR")){
      response.sendRedirect("/manager/");
    }
    else if(roles.contains("ROLE_USER")){
      response.sendRedirect("/user");
    }
    else{
      response.sendRedirect("/home");
    }
    return ResponseEntity.ok().headers(header)
    					 .body("");
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestParam("username") String username, @RequestParam("auth") String added_role, @RequestParam("password") String pass, @RequestParam("roles") Set<String> ro, @RequestParam("email") String email, HttpServletResponse response, HttpServletRequest request) throws IOException {
    if (userRepository.existsByUsername(username)) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }
    
    if (userRepository.existsByEmail(email)) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    // Create new user's account
    User user = new User(username, 
               email,
               encoder.encode(pass));

    Set<String> strRoles = ro;
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }
    user.setRoles(roles);
    userRepository.save(user);
    if(added_role=="admin"){
      response.sendRedirect("/admin/showUser");
    }
    else{
      response.sendRedirect("/manager/showUser");
    }
    
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @GetMapping("/print")
  public static String print() {
	 //System.out.println(jwt);
	  return jwt;
  }
  
 
}


