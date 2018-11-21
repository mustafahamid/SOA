package fr.insa.soa.ExchangeSemester.restServices;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.ExchangeSemester.dao.UserRepository;
import fr.insa.soa.ExchangeSemester.model.User;
import fr.insa.soa.ExchangeSemester.services.UserService;

@RestController
public class SignUpService {

	@Autowired
	// This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	 

	@PutMapping(value="/service/signup", produces="application/json")
	public String addAcount(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		UserService userService = new UserService(userRepository);
		
		System.out.println("user infos : " + " " + user.getFirstName() + " " +  user.getLastName() + " " +  user.getLogin() + " " +  user.getPassword() + " " +  user.getStatus() + " " +  user.getId());
		
		boolean result = userService.saveUser(user);
		
		if(result) {
			return "{\"success\": \"true\"}";
		} 
		else {
			return "{\"success\": \"false\"}";
		}
	}
	
	@GetMapping(value="/service/signup", produces="application/json")
	public Iterable<User> test() {
		return userRepository.findAll();
	}
	
/*	@DeleteMapping(value="/service/signup")
	public String deleteAccount(@RequestBody User user) {
		Iterable<User> listUsers = userRepository.findAll();

		for (User myUser : listUsers) {
			if (myUser.getLogin().equals(user.getLogin())) {
				found = true;
			}
		}
	}*/
	
}