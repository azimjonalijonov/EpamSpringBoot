package com.example.EpamSpringBoot.traineeTrainers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeTrainersRepository extends JpaRepository<TraineeTrainer,Long> {


}
