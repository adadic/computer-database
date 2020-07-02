package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error404")
public class Error404 {

	@GetMapping
	public ModelAndView error404() {

		return new ModelAndView("404");
	}
}