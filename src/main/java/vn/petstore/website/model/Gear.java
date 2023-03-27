package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "gear")
public class Gear extends Product implements Serializable {

    private String material;
}
