package vn.petstore.website.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.petstore.website.model.Product;

@Data
@Builder
public class PaginatedProductResponse {
    private List<Product> products;
    private Long numberOfItems;
    private int numberOfPages;
}
