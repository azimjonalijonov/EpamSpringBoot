package com.example.EpamSpringBoot.general;

import com.example.EpamSpringBoot.general.dto.ChangeLoginDTO;
import com.example.EpamSpringBoot.general.dto.LoginDTO;
import com.example.EpamSpringBoot.trainee.TraineeService;
import com.example.EpamSpringBoot.trainer.TrainerService;
import com.example.EpamSpringBoot.user.User;
import com.example.EpamSpringBoot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	TraineeService traineeService;

	@Autowired
	TrainerService trainerService;

	@GetMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
		if (loginDTO.getUsername().equals(null) || loginDTO.getPassword().equals(null)) {
			throw new RuntimeException("username or password should not be null");
		}
		User user = null;
		String username = loginDTO.getUsername();
		if (traineeService.readByUsername(username) != null) {
			user = traineeService.readByUsername(username).getUser();
		}
		else if (trainerService.readByUsername(username) != null) {

			user = trainerService.readByUsername(username).getUser();
		}
		else {
			throw new RuntimeException("no user with this username");
		}
		if (!user.getPassword().equals(loginDTO.getPassword())) {
			throw new RuntimeException("password mismatch");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity update(@RequestBody ChangeLoginDTO changeLoginDTO) {
		if (changeLoginDTO.getNewPassword().equals(null) || changeLoginDTO.getOldPassword().equals(null)
				|| changeLoginDTO.getUsername().equals(null)) {
			throw new RuntimeException("old, new passwords and username must be not null");
		}
		if (userService.readByUsername(changeLoginDTO.getUsername()) == null) {
			throw new RuntimeException("user not found");
		}
		User user = userService.readByUsername(changeLoginDTO.getUsername());
		if (user.getPassword().equals(changeLoginDTO.getOldPassword())) {
			user.setPassword(changeLoginDTO.getNewPassword());
		}
		else {
			throw new RuntimeException("password incorrect");
		}
		userService.updatePassword(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
