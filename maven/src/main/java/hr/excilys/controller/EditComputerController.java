package hr.excilys.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hr.excilys.dto.DTOCompany;
import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Computer;
import hr.excilys.service.CommunService;
import hr.excilys.service.EditService;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputerController {

	private static final String UPDATEERROR = "-1";
	private static final String NOEXISTENCE = "-3";
	private static final String UPDATESUCCESS = "3";

	@Autowired
	private CommunService communService;
	@Autowired
	private EditService editService;

	@GetMapping
	public ModelAndView edit(@RequestParam(defaultValue = "0") String id, @RequestParam(defaultValue = "") String msg) {

		ModelAndView model = new ModelAndView("editComputer");
		model.addObject("companies", communService.getCompanies());
		Optional<Computer> computer = editService.getComputerById(id);
		setMessage(model, msg);

		if (computer.isPresent()) {
			model.addObject("computer", computer.get());
		} else {
			model.addObject("msg", NOEXISTENCE);
			model.setViewName("redirect:dashboard");
		}
		
		return model;
	}

	private void setMessage(ModelAndView model, String msg) {

		if (!msg.isEmpty()) {
			model.addObject("msg", msg);
		}
	}

	@PostMapping
	public ModelAndView updateComputer(DTOComputer computer, DTOCompany dtoCompany) {

		ModelAndView view = new ModelAndView("redirect:editComputer");
		computer.setCompany(dtoCompany);

		if (!editService.editComputer(computer)) {
			view.addObject("id", computer.getId());
			view.addObject("msg", UPDATEERROR);
			System.out.println(computer.getId());
		} else {
			view.addObject("msg", UPDATESUCCESS);
			view.setViewName("redirect:dashboard");
		}

		return view;
	}
}