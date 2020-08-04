package hr.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.service.UserService;

@Controller
@RequestMapping(value = "/register")
public class AddUserController {

	private final UserService userService;

	@Autowired
	public AddUserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping
	public ModelAndView addUser() {

		ModelAndView model = new ModelAndView("register");
		model.addObject("role", userService.getRoles());

		return model;
	}

	@PostMapping
	public ModelAndView addCreateUser(DTOUser dtoUser) {

		ModelAndView view = new ModelAndView();
		System.out.println(dtoUser);
		DTORole dtoRole = new DTORole("1","USER");
		dtoUser.setRole(dtoRole);

		return view;
		
		
		
	
		
	
		
	}
}