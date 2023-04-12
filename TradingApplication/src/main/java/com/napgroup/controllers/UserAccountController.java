package com.napgroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.napgroup.models.MyUserDetails;
import com.napgroup.models.UserAccount;
import com.napgroup.models.UserData;
import com.napgroup.services.ExternalBankingService;
import com.napgroup.services.MyUserDetailsService;
import com.napgroup.services.UserAccountService;
import com.napgroup.services.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountServiceImpl;
	@Autowired
	private ExternalBankingService externalBankingService;
	@Autowired
	private MyUserDetailsService userDetailsService;

//	public UserAccountController(UserAccountServiceImpl userAccountService) {
//		this.userAccountService = userAccountService;
//	}

//	@PostMapping("/register")
//	  public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
//	    // UserAccount newUser = userAccountService.register(user);
//	    // return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//	  }

//	@GetMapping("/Register")
//	public String reg() {
//		return "Register";
//	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("userData", new UserData());
		return "Register";
	}

	@PostMapping("/register")
	public String userRegistration(UserData userData, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userData", userData);
			// model.addAttribute()
			return "Register";
		}
		try {
			userDetailsService.register(userData);
		} catch (UserAlreadyExistsException e) {
			bindingResult.rejectValue("emailAddress", "userData.emailAddress", "An account already exists for this email.");
			model.addAttribute("userData", userData);
			return "Register";
		}
		return "redirect:/login";
	}

//	@PostMapping("/RegisterProcess")
//	public String register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
//			@RequestParam("emailAddress") String emailAddress, @RequestParam("username") String username,
//			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
//		UserAccount ua = new UserAccount(username, emailAddress, password, firstName, lastName);
//		try {
//			if (!password.equals(confirmPassword)) {
//				return "/Register";
//			}
//			UserAccount savedInfo = userAccountServiceImpl.register(ua);
//			return "/Login";
//
//		} catch (DataIntegrityViolationException ex) {
//			return "/Register";
//		}
//	}

//	@GetMapping("/Login")
//	public String log() {
//		return "Login";
//	}

	@GetMapping("/login")
	public String login() {
		return "Login";
	}

//	@PostMapping("/LoginForm")
//	public String login(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password,
//			HttpServletRequest request, Model model) {
//		UserAccount loggedInUser = this.userAccountServiceImpl.login(emailAddress, password);
//		if (loggedInUser != null) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("loggedInUser", loggedInUser);
//			model.addAttribute("accountId", loggedInUser.getAccountId());
//			model.addAttribute("username", loggedInUser.getUsername());
//			model.addAttribute("emailAddress", loggedInUser.getEmailAddress());
//			model.addAttribute("firstName", loggedInUser.getFirstName());
//			model.addAttribute("lastName", loggedInUser.getLastName());
//			model.addAttribute("balance", loggedInUser.getBalance());
//			return "redirect:/Dashboard";
//		} else {
//			return "Login";
//		}
//	}

	@GetMapping("/Logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/login";
	}

	@GetMapping("/Account")
	public String account(Model model, @AuthenticationPrincipal MyUserDetails user) {

		// UserAccount loggedInUser =
		// userAccountServiceImpl.findUserByUserId(user.getAccountId()).get();
		model.addAttribute("accountId", user.getAccountId());
		model.addAttribute("username", user.getUsername());
		model.addAttribute("emailAddress", user.getEmailAddress());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("balance", user.getBalance());
		return "/Account";

	}

	@GetMapping("/AddMoney")
	public String viewAddMoney(Model model) {
		return "AddMoney";
	}

	@PostMapping("/AddMoney")
	public String addMoney(@RequestParam("phone-no") String phoneNo, @RequestParam("pass") String pass,
			@RequestParam("account-no") long accountNo, @RequestParam("amount") double amount, Model model,
			@AuthenticationPrincipal MyUserDetails user) {

		// UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
		// UserAccount user = userAccountServiceImpl.login("nk@gmail.com", "pass");
		HttpStatus login = externalBankingService.login(phoneNo, pass, accountNo);
		if (login == HttpStatus.OK) {
			HttpStatus withdraw = externalBankingService.withdraw(phoneNo, pass, accountNo, amount);
			if (withdraw == HttpStatus.OK) {
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
