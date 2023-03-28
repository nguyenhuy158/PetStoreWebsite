package vn.petstore.website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

    @Id
    private String username;

    private String password;

    private Integer status;
}
