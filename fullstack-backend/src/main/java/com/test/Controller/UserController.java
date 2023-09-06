package com.test.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.test.exception.UserNotFoundException;
import com.test.model.User;
import com.test.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController 
{
    
	@Autowired
	 private UserRepository userRepository;
	
	
//	  @PostMapping("/user")
//	  User newUser(@RequestBody User newUser)
//	  {
//		  return userRepository.save(newUser);
//		  
//	  }
//	  
	
	  @PostMapping("/user")
	  User newUser(@RequestBody User newUser)
	  {
		  return userRepository.save(newUser);
	  }
	  
//	  @GetMapping("/users")
//	  List<User> getAllUser()
//	  {
//		return userRepository.findAll();
//		   
//	  }
	  @GetMapping("/users")
	  List <User> getAllUser()
	  {
		  return userRepository.findAll();
	  }
	  
	  @GetMapping("/user/{id}")
	  User getUserById(@PathVariable  Long id)
	  {
		  return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	  }
	  
	  
	  @PutMapping("/user/{id}")
	  User updateUser(@RequestBody User newUser, @PathVariable Long id)
	  {
		  return userRepository.findById(id)
				  .map(user -> {
					 
					  user.setName(newUser.getName()); 
					  user.setLname(newUser.getLname());
					  user.setAddress(newUser.getAddress());
					  user.setCity(newUser.getCity());
					  user.setState(newUser.getState());
					  user.setPhone(newUser.getPhone());
					  user.setEmail(newUser.getEmail()); 
					  
					  return userRepository.save(user);
				  }).orElseThrow( ()->new UserNotFoundException(id));
	  }
	  
	  
	  @DeleteMapping("/user/{id}")
	  String deleteUser(@PathVariable Long id)
	  {
		  if(!userRepository.existsById(id))
		  {
			  throw new UserNotFoundException(id);
		  }
		  userRepository.deleteById(id);
		  return "User with id"+id+"has been deleted sucess.";
		  
	  }
	 
}
