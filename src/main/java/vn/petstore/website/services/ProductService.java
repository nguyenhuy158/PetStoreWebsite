package vn.petstore.website.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.dto.PaginatedProductResponse;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.ProductRepository;

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

    public PaginatedProductResponse readProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return PaginatedProductResponse.builder()
                .numberOfItems(products.getTotalElements())
                .numberOfPages(products.getTotalPages())
                .products(products.getContent())
                .build();
    }

    public PaginatedProductResponse filterBooks(String keyword, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContains(keyword, pageable);
        // products.and(productRepository.findAllByBrandContains(keyword, pageable));
        // products.and(productRepository.findAllByColorContains(keyword, pageable));
        try {
            double parseDouble = Double.parseDouble(keyword);
            products.and(productRepository.findAllByPriceEquals(parseDouble, pageable));
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
        return PaginatedProductResponse.builder()
                .numberOfItems(products.getTotalElements())
                .numberOfPages(products.getTotalPages())
                .products(products.getContent())
                .build();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(Long id) throws Exception {
        Optional<Product> result = productRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any users with ID " + id);
    }

    public void delete(Long id) throws Exception {
        Long count = productRepository.countById(id);
        if (count == null || count == 0) {
            throw new Exception("Could not find any products with ID " + id);
        }
        productRepository.deleteById(id);
    }
}
