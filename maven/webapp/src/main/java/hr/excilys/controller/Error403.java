package hr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error403")
public class Error403 {

	@GetMapping
	public ModelAndView error403() {

		return new ModelAndView("403");
	}
}