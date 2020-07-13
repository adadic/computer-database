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
@RequestMapping(value = "/addUser")
public class AddUserController {

	private static final int ADDSUCCESS = 4;
	private static final int ADDERROR = -1;

	private final UserService userService;

	@Autowired
	public AddUserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping
	public ModelAndView addUser() {

		ModelAndView model = new ModelAndView("addUser");
		model.addObject("role", userService.getRoles());

		return model;
	}

	@PostMapping
	public ModelAndView addCreateUser(DTOUser dtoUser, DTORole dtoRole) {

		ModelAndView view = new ModelAndView();
		dtoUser.setRole(dtoRole);
		
		if (!userService.addUser(dtoUser)) {
			view.addObject("msg", ADDERROR);
			view.addObject("role", userService.getRoles());
		} else {
			view.addObject("msg", ADDSUCCESS);
			view.setViewName("redirect:dashboard");
		}

		return view;
	}
}
