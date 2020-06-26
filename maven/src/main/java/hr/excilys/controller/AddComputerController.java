package hr.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.service.AddService;
import hr.excilys.service.CompanyService;

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputerController {

	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;

	private final CompanyService companyService;
	private final AddService addService;

	
	@Autowired
	public AddComputerController(CompanyService companyService, AddService addService) {

		this.companyService = companyService;
		this.addService = addService;
	}

	@GetMapping
	public ModelAndView add() {

		ModelAndView model = new ModelAndView("addComputer");
		model.addObject("companies", companyService.getCompanies());

		return model;
	}

	@PostMapping
	public ModelAndView addComputer(DTOComputer computer, DTOCompany dtoCompany) {

		ModelAndView view = new ModelAndView();
		computer.setCompany(dtoCompany);
		
		if (!addService.addComputer(computer)) {
			view.addObject("msg", ADDERROR);
		} else {
			view.addObject("msg", ADDSUCCESS);
			view.setViewName("redirect:dashboard");
		}

		return view;
	}
}
