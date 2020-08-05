package hr.excilys.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/register")
public class RegisterRestController {

	UserService userService;
	
	@Autowired
	public RegisterRestController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<String> createUser(@Valid DTOUser user, BindingResult errors) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Allow", "GET, POST");
		if (errors.hasErrors()) {
			String message = errors.getFieldError().getDefaultMessage();

			return new ResponseEntity<String>(message, headers, HttpStatus.METHOD_NOT_ALLOWED);
		}
		DTORole dtoRole = new DTORole("1", "USER");
		user.setRole(dtoRole);
		try {
			System.out.println("REST" + user);
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), headers, HttpStatus.METHOD_NOT_ALLOWED);
		}

		return new ResponseEntity<String>("New user added", headers, HttpStatus.OK);
	}
}
