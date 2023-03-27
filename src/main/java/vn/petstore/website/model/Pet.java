package vn.petstore.website.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "pet")
@Builder
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Integer age;

    private String description;

    private Integer price;

    private Integer amount;

    private Date saleDate;
    
    private List<String> thumbnail;

    private Integer status;

    private Double rating;
}
