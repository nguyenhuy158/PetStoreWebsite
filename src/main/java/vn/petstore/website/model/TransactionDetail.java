package vn.petstore.website.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    private Long amount;

    private Integer price;

    public TransactionDetail(Transaction transaction, Product product, Long amount, Integer price) {
        this.transaction = transaction;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

}
