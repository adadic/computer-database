package hr.excilys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error500")
public class Error500 {

	@GetMapping
	public ModelAndView eror500() {

		return new ModelAndView("500");
	}
}