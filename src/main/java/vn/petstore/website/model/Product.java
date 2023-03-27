package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TransactionDetail> transactionDetailList;

    @Column(name = "name")
    private String name;

    private Integer status;
    private Double rating;
    private List<String> thumbnail;

    private Date saleDate;

    private String source;

    private Integer price;

    private Integer amount;
}

