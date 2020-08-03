package hr.excilys.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.excilys.dto.DTOCompany;
import hr.excilys.model.Company;
import hr.excilys.service.CompanyService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/companies")
public class CompanyRestController {

	ObjectMapper obj = new ObjectMapper();
	private final CompanyService companyService;
	
	public CompanyRestController(CompanyService companyService) {
		
		this.companyService = companyService;
	}

	@GetMapping
	public ResponseEntity<String> getCompanies() {
		List<Company> listCompanies = companyService.getCompanies();
		try {

			return ResponseEntity.ok(obj.writeValueAsString(listCompanies));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get companies", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<String> getCompany(@PathVariable("id") String id) {
		
		Optional<Company> company = companyService.getCompany(id);
		if (company.isPresent()) {
			try {

				return ResponseEntity.ok(obj.writeValueAsString(company.get().toString()));
			} catch (JsonProcessingException jsonExc) {
				jsonExc.printStackTrace();

				return new ResponseEntity<String>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<String>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/{id}")
	public boolean deleteCompany(@PathVariable("id") String id) {
		
		return companyService.deleteCompany(id);
	}
	
	@PutMapping
	public ResponseEntity<String> editCompany(@RequestBody DTOCompany dtoCompany) {

		if (companyService.editCompany(dtoCompany)) {

			return new ResponseEntity<String>("Company edited", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Cannot edit company", HttpStatus.BAD_REQUEST);
	}
}
