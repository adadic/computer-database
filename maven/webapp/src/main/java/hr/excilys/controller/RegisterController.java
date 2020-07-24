package hr.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.service.UserService;

@Controller 
@RequestMapping(value="/register")
public class RegisterController {

	@Autowired
	UserService userService;


	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;
	
		
		@PostMapping
		public ModelAndView createUser(@Valid DTOUser user) {
			System.out.println(user.toString());
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		DTORole dtoRole = new DTORole("1","USER");
		user.setRole(dtoRole);
		userService.addUser(user);
		
		
//		
//		
//
//		if (!userService.addUser(user)) {
//			modelAndView.addObject("msg", ADDERROR);
//			modelAndView.addObject("role", userService.getRoles());
//			System.out.println("false !");
//		} else {
//			modelAndView.addObject("msg", ADDSUCCESS);
//			modelAndView.setViewName("redirect:/dashboard");
//			System.out.println("true !");
//		}
//		
		
		return modelAndView;
		}


	}