package hr.excilys.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.excilys.model.Computer;
import hr.excilys.model.CustomUserDetails;
import hr.excilys.model.User;
import hr.excilys.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/users")
public class UserRestController {

	ObjectMapper obj = new ObjectMapper();
	UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/{username}")
	public ResponseEntity<String> getUsers(@PathVariable("username") String username) {
		
		try {
			CustomUserDetails user = userService.loadUserByUsername(username);
			return ResponseEntity.ok(obj.writeValueAsString(user));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get user", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<String> getAllUsers(){

		List<User> listUsers = userService.getAllUsers();
		try {

			return ResponseEntity.ok(obj.writeValueAsString(listUsers));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get users", HttpStatus.BAD_REQUEST);
		}
	}
}
