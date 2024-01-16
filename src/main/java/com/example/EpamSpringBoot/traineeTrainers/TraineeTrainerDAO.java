package com.example.EpamSpringBoot.traineeTrainers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class TraineeTrainerDAO {

	private final SessionFactory sessionFactory;

	public TraineeTrainerDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public TraineeTrainer createOrUpdate(TraineeTrainer traineeTrainer) {
		// Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			// transaction = session.beginTransaction();
			session.saveOrUpdate(traineeTrainer);
			// transaction.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			// if (transaction != null) {
			// transaction.rollback();
			// }
		}
		return traineeTrainer;
	}

}
