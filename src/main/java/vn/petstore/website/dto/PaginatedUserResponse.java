package vn.petstore.website.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.petstore.website.model.User;

@Data
@Builder
public class PaginatedUserResponse {
    private List<User> users;
    private Long numberOfItems;
    private int numberOfPages;
}
