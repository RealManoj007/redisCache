package com.projectRedis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectRedis.model.User;
import com.projectRedis.repo.UserRepository;

@RestController
public class UserResourceController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/user", consumes = "application/json")
	public User add(@RequestBody User user) {
		userRepository.save(user);
		return userRepository.findById(user.getId());
	}

	@PutMapping("/user/{id}/{name}")
	public User update(@PathVariable("id") final String id, @PathVariable("name") final String name) {
		userRepository.update(new User(id, name, 12000L));
		return userRepository.findById(id);
	}

	@DeleteMapping("/user/{id}")
	public Map<String, User> delete(@PathVariable("id") final String id) {
		userRepository.delete(id);
		return all();
	}

	@GetMapping("/users")
	public Map<String, User> all() {
		Map<String, User> findAll = userRepository.findAll();
		return findAll;
	}
}
