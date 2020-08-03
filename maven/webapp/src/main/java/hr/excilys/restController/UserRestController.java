package hr.excilys.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.excilys.model.Computer;
import hr.excilys.model.CustomUserDetails;
import hr.excilys.model.User;
import hr.excilys.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/users")
public class UserRestController {
	UserService userService;
	ObjectMapper obj = new ObjectMapper();

	@Autowired
	public UserRestController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<String> getAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		
		
		List<User> listComputers = userService.getAllUsers();
		try {
			System.out.println(listComputers.get(0).toString());
			headers.add("CustomHeader", "All users fetched");
			return new ResponseEntity<String>(obj.writeValueAsString(listComputers), headers, HttpStatus.OK);
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();
			headers.add("Custom", "All users fetched");
			return new ResponseEntity<String>("Cannot get users", headers, HttpStatus.BAD_REQUEST);
		}
	}

}
