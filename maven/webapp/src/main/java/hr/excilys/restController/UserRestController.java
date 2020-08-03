package hr.excilys.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hr.excilys.model.CustomUserDetails;
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
			//CustomUserDetails user = userService.loadUserByUsername("toto");
			System.out.println("----" + user);
			return ResponseEntity.ok(obj.writeValueAsString(user));
		} catch (JsonProcessingException jsonExc) {
			jsonExc.printStackTrace();

			return new ResponseEntity<String>("Cannot get user", HttpStatus.BAD_REQUEST);
		}
	}
}
