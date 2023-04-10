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
import com.napgroup.services.ExternalBankingService;
import com.napgroup.services.UserAccountService;
import com.napgroup.services.UserAccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountServiceImpl;
	@Autowired
	private ExternalBankingService externalBankingService;

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
	public String register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("emailAddress") String emailAddress, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
		UserAccount ua = new UserAccount(username, emailAddress, password, firstName, lastName);
		try {
			if (!password.equals(confirmPassword)) {
				return "/Register";
			}
			UserAccount savedInfo = userAccountServiceImpl.register(ua);
			return "/Login";

		} catch (DataIntegrityViolationException ex) {
			return "/Register";
		}
	}

	@GetMapping("/Login")
	public String log() {
		return "Login";
	}

	@PostMapping("/LoginForm")
	public String login(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password,
			HttpServletRequest request, Model model) {
		UserAccount loggedInUser = this.userAccountServiceImpl.login(emailAddress, password);
		if (loggedInUser != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedInUser", loggedInUser);
			model.addAttribute("accountId", loggedInUser.getAccountId());
			model.addAttribute("username", loggedInUser.getUsername());
			model.addAttribute("emailAddress", loggedInUser.getEmailAddress());
			model.addAttribute("firstName", loggedInUser.getFirstName());
			model.addAttribute("lastName", loggedInUser.getLastName());
			model.addAttribute("balance", loggedInUser.getBalance());
			return "redirect:/Dashboard";
		} else {
			return "Login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}

	@GetMapping("/Account")
	public String account(HttpServletRequest request, HttpSession session, Model model) {
		if (session.getAttribute("loggedInUser") != null) {
			UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
			UserAccount loggedInUser = userAccountServiceImpl.findUserByUserId(user.getAccountId()).get();
			model.addAttribute("accountId", loggedInUser.getAccountId());
			model.addAttribute("username", loggedInUser.getUsername());
			model.addAttribute("emailAddress", loggedInUser.getEmailAddress());
			model.addAttribute("firstName", loggedInUser.getFirstName());
			model.addAttribute("lastName", loggedInUser.getLastName());
			model.addAttribute("balance", loggedInUser.getBalance());
			return "/Account";
		} else {
			return "redirect:/Login";
		}
	}
	
	@GetMapping("/AddMoney")
	public String viewAddMoney(Model model) {
		return "AddMoney";
	}
	
	@PostMapping("/AddMoney")
	public String addMoney(@RequestParam("phone-no") String phoneNo, @RequestParam("pass") String pass, @RequestParam("account-no") long accountNo, 
			@RequestParam("amount") double amount, Model model, HttpSession session) {
		
		UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
		// UserAccount user = userAccountServiceImpl.login("nk@gmail.com", "pass");
		HttpStatus login = externalBankingService.login(phoneNo, pass, accountNo);
		if(login == HttpStatus.OK) {
			HttpStatus withdraw = externalBankingService.withdraw(phoneNo, pass, accountNo, amount);
			if(withdraw == HttpStatus.OK) {
				double currentBalance = user.getBalance();
				userAccountServiceImpl.updateUserBalance(currentBalance + amount, user.getAccountId());
				// user.setBalance(amount + currentBalance);
				// session.setAttribute("loggedInUser", user);
				return "redirect:/Account";
			}
		}
		String status = "Unable to withdraw money from account- please check your details and that you have enough funds available";
		model.addAttribute("updateStatus", status);
		return viewAddMoney(model);
	}
	
}
