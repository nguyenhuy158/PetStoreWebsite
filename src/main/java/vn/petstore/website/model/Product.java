package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "product")
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @MappedSuperclass
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TransactionDetail> transactionDetailList;

    @Column(name = "name")
    private String name;

    private Integer status;
    private Double rating;

    @ElementCollection // 1
    @CollectionTable(name = "list_thumbnail", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "thumbnail") // 3
    private List<String> thumbnail;

    private Date saleDate;

    private String source;

    private Integer price;

    private Integer amount;

    private String description;
}
