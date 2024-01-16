package com.example.EpamSpringBoot.traineeTrainers;

import org.springframework.stereotype.Service;

@Service
public class TraineeTrainerService {

	private final TraineeTrainerDAO traineeTrainerDAO;

	public TraineeTrainerService(TraineeTrainerDAO traineeTrainerDAO) {
		this.traineeTrainerDAO = traineeTrainerDAO;
	}

	public TraineeTrainer add(TraineeTrainer traineeTrainer) {
		return traineeTrainerDAO.createOrUpdate(traineeTrainer);
	}

}
