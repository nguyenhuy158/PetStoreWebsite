package vn.petstore.website.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "pet")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper=true)
public class Pet extends Product implements Serializable {
    private Integer age;
}
