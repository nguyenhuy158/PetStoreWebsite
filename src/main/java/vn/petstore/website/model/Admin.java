package vn.petstore.website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Admin implements Serializable {

    @Id
    private String username;

    private String password;

    private Integer status;
}
