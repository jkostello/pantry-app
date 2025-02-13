package org.kostello.joseph.pantry.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PantryRepositoryTest {
    private final String FOOD_NAME = "hotdog";
    PantryRepository pantryRepository;
    Food testFood = new Food(FOOD_NAME, 5);

    @BeforeEach
    void setUp() {
        pantryRepository = new PantryRepository();
    }

    @Test
    void save() {
        Food savedFood = pantryRepository.save(testFood);
        assertEquals(savedFood, testFood);
    }

    @Test
    void findByNameFail() {
        assertNull(pantryRepository.findByName("hamburger"));
    }

    @Test
    void findByNameSuccess() {
        pantryRepository.save(testFood);
        assertNotNull(pantryRepository.findByName(FOOD_NAME));
    }

    @Test
    void findAll() {
        assertEquals(0, pantryRepository.findAll().size());
        pantryRepository.save(testFood);
        assertEquals(1, pantryRepository.findAll().size());
        assertTrue(pantryRepository.findAll().contains(testFood));
    }
}
