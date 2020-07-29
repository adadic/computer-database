package hr.excilys.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import hr.excilys.dto.DTORole;
import hr.excilys.dto.DTOUser;
import hr.excilys.service.UserService;

@Controller 
@RequestMapping(value="/register",method = {RequestMethod.GET, RequestMethod.PUT})
public class RegisterRestController {

	@Autowired
	UserService userService;


	private static final int ADDSUCCESS = 2;
	private static final int ADDERROR = -1;
	
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
//		@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
//        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PostMapping
		
		public ResponseEntity<String> createUser(@Valid  DTOUser user, BindingResult errors) {
		HttpHeaders headers = new HttpHeaders();
		 headers.add("Allow", "GET, POST");	
		if(errors.hasErrors()) {
			System.out.println(errors.getFieldError());
				String message = errors.getFieldError().getDefaultMessage();
	           System.out.println(errors.getFieldError().getDefaultMessage());
	           return new ResponseEntity<String>(message,headers, HttpStatus.METHOD_NOT_ALLOWED);
	          
	        }
			System.out.println(user.toString());
			
		//ModelAndView modelAndView = new ModelAndView("redirect:/login");
		DTORole dtoRole = new DTORole("1","USER");
		
		user.setRole(dtoRole);
		
		try {
			userService.addUser(user);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<String>(e.getMessage(),headers, HttpStatus.METHOD_NOT_ALLOWED);
				
			}
		return new ResponseEntity<String>("New user added",headers, HttpStatus.OK);
		
		
		
		
	    //headers.add("Custom-Header", "User added");
	   
		
		
		
		
//		
//		
//
//		if (!userService.addUser(user)) {
//			modelAndView.addObject("msg", ADDERROR);
//			modelAndView.addObject("role", userService.getRoles());
//			System.out.println("false !");
//		} else {
//			modelAndView.addObject("msg", ADDSUCCESS);
//			modelAndView.setViewName("redirect:/dashboard");
//			System.out.println("true !");
//		}
//		
		
		
		}


	}
