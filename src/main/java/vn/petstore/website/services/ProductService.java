package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : null;
    }

    public List<Product> getAllProducts(Integer limit) {
        return Optional
                .ofNullable(limit)
                .map(value -> productRepository.findAll(PageRequest.of(0, limit)).getContent())
                .orElseGet(() -> productRepository.findAll());
    }

    public Page<Product> findPaginated(Pageable pageable) {
        List<Product> products = productRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());
    }
}
