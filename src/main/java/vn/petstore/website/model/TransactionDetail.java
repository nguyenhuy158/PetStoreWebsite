package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Transaction transaction;

    private Integer amount;

    private Integer price;

}
