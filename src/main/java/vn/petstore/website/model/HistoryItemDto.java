package vn.petstore.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryItemDto {

    private Transaction transaction;

    private String id;

    private Product firstProduct;

    private Long amount;

    private Long totalPrice;

}
