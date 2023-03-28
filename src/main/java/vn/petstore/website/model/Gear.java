package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "gear")
@EqualsAndHashCode(callSuper = true)
public class Gear extends Product implements Serializable {

    private String material;
}
