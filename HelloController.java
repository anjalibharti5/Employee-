package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.user;
import com.example.demo.repository.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {
	@Autowired
	private userRepo userRepositiry;

	@PutMapping("/abc/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody user userData) {
		Optional<user> existingUser = userRepositiry.findById(id);
		if (existingUser.isPresent()) {
			user updatedUser = existingUser.get();
			updatedUser.setUser_name(userData.getUser_name());
			user updatedUserData = userRepositiry.save(updatedUser);
			return ResponseEntity.ok(updatedUserData);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/abc/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		try {
			Optional<user> userData = userRepositiry.findById(id);
			System.out.println(id);
			if (userData.isPresent()) {
				return ResponseEntity.ok(userData.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/create/user")

	public ResponseEntity<?> postMethodName(@RequestBody user data) {
		Long id = data.getId();

		Optional<user> existingUser = userRepositiry.findById(id);

		if (existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("User already exists with ID: " + id);
		} else {

			user savedUser = this.userRepositiry.save(data);

			return ResponseEntity.ok(savedUser);
		}
	}

}
