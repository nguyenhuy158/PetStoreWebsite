package vn.petstore.website.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.petstore.website.model.Product;

import java.util.List;

@Component
@Repository
public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByName(String name, Pageable pageable);
}
