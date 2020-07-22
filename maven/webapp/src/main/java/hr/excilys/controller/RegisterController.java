package hr.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.service.UserService;

@Controller 
@RequestMapping(value="/register")
public class RegisterController {

	@Autowired
	UserService userService;

	

		@GetMapping
		public ModelAndView registerPage() {
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
		}
		@PostMapping
		public ModelAndView createUser(DTOUser user) {
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		DTORole dtoRole = new DTORole("1","USER");
		user.setRole(dtoRole);
		userService.addUser(user);
		return modelAndView;
		}


	}
