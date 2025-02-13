package org.kostello.joseph.pantry.sql;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(
        value="useSQL",
        havingValue = "true")
public interface PantryRepositorySQL extends CrudRepository<Food, String> {

}
