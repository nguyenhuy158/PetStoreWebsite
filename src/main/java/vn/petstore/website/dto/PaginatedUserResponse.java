package vn.petstore.website.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.petstore.website.model.CustomUserDetails;

@Data
@Builder
public class PaginatedUserResponse {
    private List<CustomUserDetails> products;
    private Long numberOfItems;
    private int numberOfPages;
}
