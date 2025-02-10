package pantry;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PantryRepository {
    private Map<String, Food> repository;

    public PantryRepository() {
        this.repository = new HashMap<>();
    }

    public void save(Food food) {
        repository.put(food.name(), food);
    }

    public Food findByName(String name) {
        return repository.get(name);
    }
}
