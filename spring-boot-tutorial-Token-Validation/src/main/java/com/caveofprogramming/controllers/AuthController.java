package com.caveofprogramming.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.SiteUser;
import com.caveofprogramming.service.EmailService;
import com.caveofprogramming.service.SiteUserService;

@Controller
public class AuthController {
	
	@Autowired
	private SiteUserService siteUserService;
	
	@Autowired
	private EmailService emailService;

//	@RequestMapping("/admin")
//	String admin() {
//		return "admin";
//	}
	
	@RequestMapping("/login")
	String login() {
		return "app.login";
	}

	@RequestMapping("/verifyemail")
	String verifyemail() {
		return "app.verifyemail";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView) {
		
		SiteUser user = new SiteUser();
		
		modelAndView.getModel().put("user", user);
		modelAndView.setViewName("app.register");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, @ModelAttribute(value="user") @Valid SiteUser user, BindingResult result) {
		
		modelAndView.setViewName("app.register");
		
		if(!result.hasErrors()) {
			siteUserService.register(user);
			emailService.sendVerificationEmail(user.getEmail());
			modelAndView.setViewName("redirect:/verifyemail");
		}
		return modelAndView;
	}
	
}
































