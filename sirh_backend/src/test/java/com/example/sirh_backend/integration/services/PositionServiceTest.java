package com.example.sirh_backend.integration.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Position;
import com.example.sirh_backend.repositories.PositionRepository;
import com.example.sirh_backend.services.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private PositionService positionService;

    private Position testPosition;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testPosition = new Position();
        testPosition.setName("Software Engineer");
    }

    @Test
    void getAllPositionsReturnsAllPositions() {
        when(positionRepository.findAll()).thenReturn(List.of(testPosition));

        var positions = positionService.getAllPositions();

        assertNotNull(positions);
        assertEquals(1, positions.size());
        verify(positionRepository, times(1)).findAll();
    }

    @Test
    void getPositionByIdReturnsCorrectPosition() {
        when(positionRepository.findById(1L)).thenReturn(Optional.of(testPosition));

        var position = positionService.getPositionById(1L);

        assertNotNull(position);
        assertEquals("Software Engineer", position.getName());
        verify(positionRepository, times(1)).findById(1L);
    }

    @Test
    void getPositionByIdThrowsNotFoundExceptionForInvalidId() {
        when(positionRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> positionService.getPositionById(999L));
        verify(positionRepository, times(1)).findById(999L);
    }

    @Test
    void createPositionSavesAndReturnsPosition() {
        when(positionRepository.save(testPosition)).thenReturn(testPosition);

        var savedPosition = positionService.createPosition(testPosition);

        assertNotNull(savedPosition);
        assertEquals("Software Engineer", savedPosition.getName());
        verify(positionRepository, times(1)).save(testPosition);
    }

    @Test
    void updatePositionUpdatesPositionDetails() {
        when(positionRepository.findById(1L)).thenReturn(Optional.of(testPosition));
        when(positionRepository.save(testPosition)).thenReturn(testPosition);

        testPosition.setName("Updated Position");
        var updatedPosition = positionService.updatePosition(1L, testPosition);

        assertEquals("Updated Position", updatedPosition.getName());
        verify(positionRepository, times(1)).findById(1L);
        verify(positionRepository, times(1)).save(testPosition);
    }

    @Test
    void updatePositionThrowsIllegalArgumentExceptionForNullUpdatedPosition() {
        when(positionRepository.findById(1L)).thenReturn(Optional.of(testPosition));

        assertThrows(IllegalArgumentException.class, () -> positionService.updatePosition(1L, null));
        verify(positionRepository, times(1)).findById(1L);
    }

    @Test
    void updatePositionThrowsNotFoundExceptionForInvalidId() {
        when(positionRepository.findById(999L)).thenReturn(Optional.empty());

        var updatedPosition = new Position();
        updatedPosition.setName("Invalid");

        assertThrows(NotFoundException.class, () -> positionService.updatePosition(999L, updatedPosition));
        verify(positionRepository, times(1)).findById(999L);
    }
}