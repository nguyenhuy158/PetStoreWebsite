package vn.petstore.website.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "pet")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Pet extends Product {
    private Integer age;
}
