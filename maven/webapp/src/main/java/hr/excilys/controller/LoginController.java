package hr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@GetMapping
	public ModelAndView login(@RequestParam(defaultValue = "") String error) {

		ModelAndView view = new ModelAndView("login");
		view.addObject("error", error);
		
		return view;
	}
}
