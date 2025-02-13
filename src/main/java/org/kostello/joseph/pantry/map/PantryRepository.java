package org.kostello.joseph.pantry.map;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PantryRepository {
    private final Map<String, Food> repository = new HashMap<>();

    public Food save(Food food) {
        repository.put(food.name(), food);
        return food; // Testing
    }

    public Food findByName(String name) {
        return repository.get(name);
    }

    public Collection<Food> findAll() {
        return repository.values();
    }
}
