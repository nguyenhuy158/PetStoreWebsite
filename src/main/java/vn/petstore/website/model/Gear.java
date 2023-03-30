package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "gear")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Gear extends Product {
    private String material;

}
