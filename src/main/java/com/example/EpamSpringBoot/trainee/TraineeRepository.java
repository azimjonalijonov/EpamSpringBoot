package com.example.EpamSpringBoot.trainee;

import com.example.EpamSpringBoot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository  extends JpaRepository<Trainee,Long> {
    Trainee findTraineeByUser(User user);
    void deleteByUser(User user);
}
