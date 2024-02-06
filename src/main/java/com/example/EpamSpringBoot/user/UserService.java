package com.example.EpamSpringBoot.user;

import com.example.EpamSpringBoot.util.exception.ValidatorException;
import com.example.EpamSpringBoot.util.validation.impl.UserErrorValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserErrorValidator userErrorValidator;

	@Autowired
	public UserService(UserRepository userRepository, UserErrorValidator userErrorValidator) {
		this.userRepository = userRepository;
		this.userErrorValidator = userErrorValidator;
	}

	public List<User> readAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	public User readById(Long id) {

		User user = userRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("user is not found with this id : " + id));
		return user;

	}

	public User create(User createRequest) {
		if (userErrorValidator.isValidParamsForCreate(createRequest)) {
			String username = generateUsername(createRequest.getFirstName(), createRequest.getLastName());
			String password = generatePassword();
			createRequest.setUsername(username);
			createRequest.setPassword(password);
			userRepository.save(createRequest);
			return createRequest;
		}
		else {
			throw new ValidatorException("Something wrong with parameters");
		}
	}

	public User update(User updateRequest) {
		if (userErrorValidator.isValidParamsForUpdate(updateRequest)) {
			userRepository.save(updateRequest);
			return updateRequest;
		}
		throw new ValidatorException("Something wrong with parameters");
	}

	public User updatePassword(User user) {

		return userRepository.save(user);

	}

	public User readByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public String generateUsername(String firstName, String lastName) {
		String username = firstName + "." + lastName;
		List<User> users = readAll();

		for (User user : users) {
			if (user.getUsername().equals(username)) {
				username += user.getId() + 1;
				return username;
			}
		}
		return username;

	}

	private String generatePassword() {

		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder password = new StringBuilder();
		Random random = new Random();
		for (int i = 1; i <= 10; i++) {
			password.append(characters.charAt(random.nextInt(characters.length())));
		}
		return password.toString();
	}

}
