package com.napgroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.napgroup.models.UserAccount;
import com.napgroup.services.UserAccountService;
import com.napgroup.services.UserAccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountServiceImpl userAccountServiceImpl;
	
//	public UserAccountController(UserAccountServiceImpl userAccountService) {
//		this.userAccountService = userAccountService;
//	}
	
//	@PostMapping("/register")
//	  public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
//	    // UserAccount newUser = userAccountService.register(user);
//	    // return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//	  }

@GetMapping("/Register")
public String reg() {
	return "Register";
}

@PostMapping("/RegisterProcess")
public String register(@RequestParam("firstName") String firstName,
					   @RequestParam("lastName") String lastName,
					   @RequestParam("emailAddress") String emailAddress,
					   @RequestParam("username") String username, 
					   @RequestParam("password") String password,
					   @RequestParam("confirmPassword") String confirmPassword
					   ) {
	UserAccount ua = new UserAccount(username, emailAddress, password, firstName, lastName);
	try {
		if(!password.equals(confirmPassword)){
			return "/Register";
		}
		UserAccount savedInfo = userAccountServiceImpl.register(ua);
		return "/Login";
		
	}
	catch(DataIntegrityViolationException ex) {
		return "/Register";
	}
}

@GetMapping("/Login")
public String log() {
	return "Login";
}

@PostMapping("/LoginForm")
public String login(@RequestParam("emailAddress") String emailAddress,
					@RequestParam("password") String password,
					HttpServletRequest request,
					Model model) {
	UserAccount loggedInUser = this.userAccountServiceImpl.login(emailAddress, password);
	if(loggedInUser != null) {
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedInUser", loggedInUser);
		model.addAttribute("accountId", loggedInUser.getAccountId());
		model.addAttribute("username", loggedInUser.getUsername());
		model.addAttribute("emailAddress", loggedInUser.getEmailAddress());
		model.addAttribute("firstName", loggedInUser.getFirstName());
		model.addAttribute("lastName", loggedInUser.getLastName());
		
		return "redirect:/Dashboard";
	}
	else {
		return "Login";
	}
}

@GetMapping("/logout")
public String logout(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if(session != null){
        session.invalidate();
    }
    return "redirect:/index";
}

@GetMapping("/Account")
public String account(HttpServletRequest request, Model model) {
	HttpSession session = request.getSession(false);
	if (session != null && session.getAttribute("loggedInUser") != null) {
		UserAccount loggedInUser = (UserAccount) session.getAttribute("loggedInUser");
		model.addAttribute("accountId", loggedInUser.getAccountId());
		model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("emailAddress", loggedInUser.getEmailAddress());
        model.addAttribute("firstName", loggedInUser.getFirstName());
        model.addAttribute("lastName", loggedInUser.getLastName());
        return "/Account";
    } else {
        return "redirect:/Login";
    }
	}
}

	

