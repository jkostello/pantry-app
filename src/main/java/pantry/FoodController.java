package pantry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodController {

    PantryRepository repository = new PantryRepository();

    @GetMapping("/food")
    public Food getFood(@RequestParam String foodName) {
        return repository.findByName(foodName);
    }

    @PutMapping("/food")
    public void createFood(@RequestParam String name, @RequestParam int quantity) {
        Food newFood = new Food(name, quantity);
        repository.save(newFood);
    }
}
