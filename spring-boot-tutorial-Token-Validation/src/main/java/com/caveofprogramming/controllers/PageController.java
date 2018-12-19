package com.caveofprogramming.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.StatusUpdate;
import com.caveofprogramming.service.StatusUpdateService;

@Controller
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {
		
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("app.homepage");
		
		return modelAndView;
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}


	
}



































