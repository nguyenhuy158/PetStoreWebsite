package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(Integer limit) {
        return Optional
                .ofNullable(limit)
                .map(value -> productRepository.findAll(PageRequest.of(0, limit)).getContent())
                .orElseGet(() -> productRepository.findAll());
    }
}
