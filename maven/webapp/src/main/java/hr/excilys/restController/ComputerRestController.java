package hr.excilys.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.excilys.dto.DTOComputer;
import hr.excilys.model.Computer;
import hr.excilys.model.Pagination;
import hr.excilys.service.AddComputerService;
import hr.excilys.service.DashboardService;
import hr.excilys.service.EditComputerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/computers")
public class ComputerRestController {

	ObjectMapper obj = new ObjectMapper();
	private final EditComputerService editComputerService;
	private final AddComputerService addComputerService;
	private Pagination dashboard = new Pagination();
	private final DashboardService dashboardService;

	@Autowired
	public ComputerRestController(EditComputerService editComputerService, AddComputerService addComputerService,
			DashboardService dashboardService) {

		this.addComputerService = addComputerService;
		this.editComputerService = editComputerService;
		this.dashboardService = dashboardService;
	}

	@GetMapping
	public ResponseEntity<String> getComputers() {

		List<Computer> listComputers = dashboardService.getComputers();
		try {

			return ResponseEntity.ok(obj.writeValueAsString(listComputers));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get computers", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/page_computers") // modify
	public ResponseEntity<String> getComputersPage(@RequestParam(defaultValue = "10") String lines,
			@RequestParam(defaultValue = "1") String page) {

		dashboard.setPage(Integer.valueOf(page));
		dashboard.setLines(Integer.valueOf(lines));
		List<Computer> list = dashboardService.getComputersRows(dashboard);
		try {

			return ResponseEntity.ok(obj.writeValueAsString(list));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get computers", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getComputer(@PathVariable("id") String id) {

		Optional<Computer> computer = editComputerService.getComputerById(id);
		if (computer.isPresent()) {
			try {

				return ResponseEntity.ok(obj.writeValueAsString(computer.get().toString()));
			} catch (JsonProcessingException jsonExc) {
				jsonExc.printStackTrace();

				return new ResponseEntity<String>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<String>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/{id}")
	public boolean deleteComputer(@PathVariable("id") String id) {

		return dashboardService.deleteComputer(id);
	}

	@PutMapping
	public ResponseEntity<String> editComputer(@RequestBody DTOComputer dtoComputer) {

		System.out.println(dtoComputer.toString());
		if (editComputerService.editComputer(dtoComputer)) {

			return new ResponseEntity<String>("Computer edited", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Cannot edit computer", HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<String> addComputer(@RequestBody DTOComputer dtoComputer) {

		if (addComputerService.addComputer(dtoComputer)) {

			return new ResponseEntity<String>("Computer added", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Cannot add computer", HttpStatus.BAD_REQUEST);
	}
}
