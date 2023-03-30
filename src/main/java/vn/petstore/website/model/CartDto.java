package vn.petstore.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    Product product;
    Long quantity = 1L;

}
