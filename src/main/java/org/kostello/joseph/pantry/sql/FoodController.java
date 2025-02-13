package org.kostello.joseph.pantry.sql;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/food")
@ConditionalOnProperty(
        value="useSQL",
        havingValue = "true")
public class FoodController {
    private final PantryRepositorySQL repository;

    public FoodController(PantryRepositorySQL repository) { this.repository = repository; }

    @GetMapping("/{name}")
    public ResponseEntity<Food> getFood(@PathVariable String name) {
        Optional<Food> food = repository.findById(name.toLowerCase());
        if (food.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(food.get(), HttpStatus.OK);
    }

    @GetMapping()
    public Iterable<Food> getFood() { // Change to response entity
        return repository.findAll();
    }

    @PostMapping()
    public void createFood(@RequestParam String name, @RequestParam int quantity, HttpServletResponse response) {
        if (repository.findById(name).isEmpty()) { // If food doesn't exist, add new food
            Food newFood = new Food(name, quantity);
            repository.save(newFood);
            response.setStatus(HttpStatus.CREATED.value());
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @PutMapping()
    public Food updateFood(@RequestParam String name, @RequestParam int quantity, HttpServletResponse response) {
        Optional<Food> existingFood = repository.findById(name.toLowerCase());
        if (existingFood.isPresent()) { // If food exists, update it
            Food newFood = new Food(name, quantity + existingFood.get().getQuantity()); // Have to create since Food is record
            repository.save(newFood);
            response.setStatus(HttpStatus.CREATED.value());
            return newFood;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }
}
