package com.example.EpamSpringBoot.training;

import com.example.EpamSpringBoot.util.validation.impl.TrainingErrorValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

	private final TrainingRepository trainingRepository;

	private final TrainingErrorValidator trainingErrorValidator;

	public TrainingService(TrainingRepository trainingRepository, TrainingErrorValidator trainingErrorValidator) {
		this.trainingRepository = trainingRepository;
		this.trainingErrorValidator = trainingErrorValidator;
	}

	public List<Training> readAll() {
		return trainingRepository.findAll();
	}

	public Training readById(Long id) {
		return trainingRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("training is not found with this id : " + id));
	}

	public Training create(Training createRequest) {
		if (trainingErrorValidator.isValidParamsForCreate(createRequest)) {
			return trainingRepository.save(createRequest);
		}
		throw new RuntimeException("Some thing is wrong with provided entity");
	}

	public Training update(Training updateRequest) {
		if (trainingErrorValidator.isValidParamsForUpdate(updateRequest)) {

			return trainingRepository.save(updateRequest);
		}
		throw new RuntimeException("Some thing is wrong with provided entity");
	}

	public void deleteById(Long id) {
		trainingRepository.deleteById(id);
	}

	// public Training addTraining(Training training) {
	// return trainingDAO.createOrUpdate(training);
	//
	// }

}
