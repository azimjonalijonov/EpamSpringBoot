package com.example.EpamSpringBoot.trainee;

import com.example.EpamSpringBoot.trainee.Trainee;
import com.example.EpamSpringBoot.trainee.TraineeRepository;
import com.example.EpamSpringBoot.training.Training;
import com.example.EpamSpringBoot.user.User;
import com.example.EpamSpringBoot.user.UserService;
import com.example.EpamSpringBoot.util.exception.ValidatorException;
import com.example.EpamSpringBoot.util.validation.impl.TraineeErrorValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private UserService userService;

    @Mock
    private TraineeErrorValidator traineeErrorValidator;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readAll_shouldReturnListOfTrainees() {
        // Arrange
        when(traineeRepository.findAll()).thenReturn(Collections.singletonList(new Trainee()));

        // Act
        List<Trainee> trainees = traineeService.readAll();

        // Assert
        assertEquals(1, trainees.size());
    }

    @Test
    void readById_existingId_shouldReturnTrainee() {
        // Arrange
        Long traineeId = 1L;
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.of(new Trainee()));

        // Act
        Trainee trainee = traineeService.readById(traineeId);

        // Assert
        assertNotNull(trainee);
    }

    @Test
    void readById_nonExistingId_shouldThrowEntityNotFoundException() {
        // Arrange
        Long traineeId = 1L;
        when(traineeRepository.findById(traineeId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> traineeService.readById(traineeId));
    }

    // Add similar tests for other methods in TraineeService...

    @Test
    void deleteById_shouldCallRepositoryDeleteById() {
        // Arrange
        Long traineeId = 1L;

        // Act
        traineeService.deleteById(traineeId);

        // Assert
        verify(traineeRepository, times(1)).deleteById(traineeId);
    }

    @Test
    void create_validTrainee_shouldReturnCreatedTrainee() {
        // Arrange
        Trainee traineeToCreate = new Trainee();
        when(traineeErrorValidator.isValidParamsForCreate(traineeToCreate)).thenReturn(true);

        // Act
        Trainee createdTrainee = traineeService.create(traineeToCreate);

        // Assert
        assertNotNull(createdTrainee);
        verify(traineeRepository, times(1)).save(traineeToCreate);
    }

    @Test
    void create_invalidTrainee_shouldThrowValidatorException() {
        // Arrange
        Trainee traineeToCreate = new Trainee();
        when(traineeErrorValidator.isValidParamsForCreate(traineeToCreate)).thenReturn(false);

        assertThrows(ValidatorException.class, () -> traineeService.create(traineeToCreate));
        verify(traineeRepository, never()).save(traineeToCreate);
    }

    @Test
    void update_validTrainee_shouldReturnUpdatedTrainee() {
        Trainee traineeToUpdate = new Trainee();
        when(traineeErrorValidator.isValidParamsForUpdate(traineeToUpdate)).thenReturn(true);
        when(traineeRepository.findById(traineeToUpdate.getId())).thenReturn(Optional.of(new Trainee()));

        Trainee updatedTrainee = traineeService.update(traineeToUpdate);

        assertNotNull(updatedTrainee);
        verify(traineeRepository, times(1)).save(any(Trainee.class));
    }

    @Test
    void update_invalidTrainee_shouldThrowValidatorException() {
        Trainee traineeToUpdate = new Trainee();
        when(traineeErrorValidator.isValidParamsForUpdate(traineeToUpdate)).thenReturn(false);

        assertThrows(ValidatorException.class, () -> traineeService.update(traineeToUpdate));
        verify(traineeRepository, never()).save(any(Trainee.class));
    }


    @Test
    void getTraineeTrainingList_validUsernameAndDuration_shouldReturnTrainings() {
        // Arrange
        String username = "testUser";
        Number duration = 5;
        User mockUser = new User();
        Trainee mockTrainee = new Trainee();
        mockTrainee.setUser(mockUser);

        when(userService.readByUsername(username)).thenReturn(mockUser);
        when(traineeRepository.findTraineeByUser(mockUser)).thenReturn(mockTrainee);
        when(mockTrainee.getTrainings()).thenReturn(Collections.singletonList(new Training()));

        // Act
        List<Training> trainings = traineeService.getTraineeTrainingList(username, duration);

        // Assert
        assertNotNull(trainings);
        assertEquals(1, trainings.size());
    }
}
