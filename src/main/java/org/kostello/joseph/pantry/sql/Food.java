package org.kostello.joseph.pantry.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Entity
@ConditionalOnProperty(
        value="useSQL",
        havingValue = "true")
public class Food {
    @Id
    private String name;

    private int quantity;

    public Food() {}

    public Food(String name, int quantity) {
        this.name = name.toLowerCase();
        this.quantity = quantity;
    }

    public int getQuantity() { return quantity; }
    public String getName() { return name; }
}
