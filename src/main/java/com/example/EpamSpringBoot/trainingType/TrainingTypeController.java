package com.example.EpamSpringBoot.trainingType;

import com.example.EpamSpringBoot.trainingType.dto.TrainingTypeDTO;
import com.example.EpamSpringBoot.user.User;
import com.example.EpamSpringBoot.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/trainingType")
public class TrainingTypeController {

	final TrainingTypeService trainingTypeService;
	private final UserService userService;

	public TrainingTypeController(TrainingTypeService trainingTypeService, UserService userService) {
		this.trainingTypeService = trainingTypeService;
		this.userService = userService;
	}

	@ApiOperation(value = "get trainingtype", response = ResponseEntity.class)

	@GetMapping("/get")
	public ResponseEntity get() {
		List<TrainingType> trainingTypeList = trainingTypeService.readAll();
		return ResponseEntity.ok(trainingTypeList);

	}

	@PostMapping("/post")
	public ResponseEntity post(@RequestParam String username, String password, @RequestBody TrainingTypeDTO trainingTypeDTO){

		if (userService.readByUsername(username) == null) {
			throw new RuntimeException("user does not exist");
		}
		User user = userService.readByUsername(username);
		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("wrong password");
		}
		if(trainingTypeDTO.getName()==null){
			throw new RuntimeException("name must be placed");
		}
		TrainingType trainingType =new TrainingType();
		trainingType.setName(trainingTypeDTO.getName());
		trainingTypeService.add(trainingType);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
