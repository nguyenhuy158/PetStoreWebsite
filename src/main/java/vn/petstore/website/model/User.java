package vn.petstore.website.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.petstore.website.emun.Role;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    // @ElementCollection // 1
    // @CollectionTable(name = "list_products", joinColumns = @JoinColumn(name =
    // "id")) // 2
    // @Column(name = "productList") // 3
    // private List<Product> productList;
}
