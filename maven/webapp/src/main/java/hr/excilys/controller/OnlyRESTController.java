package hr.excilys.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.excilys.model.Computer;
import hr.excilys.model.Pagination;
import hr.excilys.service.DashboardService;
import hr.excilys.service.EditComputerService;

@RestController
@RequestMapping(value = "/api")
public class OnlyRESTController {

	private final EditComputerService editComputerService;

	private Pagination dashboard = new Pagination();
	private final DashboardService dashboardService;
	
	@Autowired
	public OnlyRESTController(EditComputerService editComputerService, DashboardService dashboardService) {

		this.editComputerService = editComputerService;
		this.dashboardService = dashboardService;
	}

	@GetMapping(value = "/computer")
	public String getComputer(@RequestParam(defaultValue = "0") String id) {
		
		Optional<Computer> computer = editComputerService.getComputerById(id);
		if (computer.isPresent()) {
			
			return computer.get().toString();
		}
		
		return "No computer found by ID = " + id;
	}
	
	@GetMapping(value = "/computers")
	public String getComputers(@RequestParam(defaultValue = "10") String lines, @RequestParam(defaultValue = "1") String page) {

		dashboard.setPage(Integer.valueOf(page));
		dashboard.setLines(Integer.valueOf(lines));
		List<Computer> list = dashboardService.getComputersRows(dashboard);
		
		return list.parallelStream().map(Object::toString).collect(Collectors.joining("</br>"));
	}
	
	@PostMapping(value = "/computers")
	public boolean deleteComputer(@RequestParam(defaultValue = "") String id) {

		return dashboardService.deleteComputer(id);
	}
}
