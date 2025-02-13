package org.kostello.joseph.pantry.map;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/food")
@ConditionalOnProperty(
        value="useSQL",
        havingValue = "false",
        matchIfMissing = true)
public class FoodController {
    private final PantryRepository repository;

    public FoodController(PantryRepository repository) { this.repository = repository; }

    @GetMapping("/{name}")
    public ResponseEntity<Food> getFood(@PathVariable String name) {
        Food food = repository.findByName(name);
        if (food == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping()
    public Collection<Food> getFood() { // Change to response entity
        return repository.findAll();
    }

    @PostMapping()
    public void createFood(@RequestParam String name, @RequestParam int quantity, HttpServletResponse response) {
        if (repository.findByName(name) == null) { // If food doesn't exist, add new food
            Food newFood = new Food(name, quantity);
            repository.save(newFood);
            response.setStatus(HttpStatus.CREATED.value());
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @PutMapping()
    public Food updateFood(@RequestParam String name, @RequestParam int quantity, HttpServletResponse response) {
        Food existingFood = repository.findByName(name);
        if (existingFood != null) { // If food exists, update it
            Food newFood = new Food(name, quantity + existingFood.quantity()); // Have to create since Food is record
            repository.save(newFood);
            response.setStatus(HttpStatus.CREATED.value());
            return newFood;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    // TODO: Add delete mapping
}
