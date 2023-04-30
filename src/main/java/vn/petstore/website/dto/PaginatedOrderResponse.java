package vn.petstore.website.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.petstore.website.model.Transaction;

@Data
@Builder
public class PaginatedOrderResponse {
    private List<Transaction> orders;
    private Long numberOfItems;
    private int numberOfPages;
}
