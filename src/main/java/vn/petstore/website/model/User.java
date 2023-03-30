package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Transaction> transactionList;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    private String address;

    private String email;

    private String phone;

    private String gender;

    // @ElementCollection // 1
    // @CollectionTable(name = "list_products", joinColumns = @JoinColumn(name =
    // "id")) // 2
    // @Column(name = "productList") // 3
    // private List<Product> productList;
}
