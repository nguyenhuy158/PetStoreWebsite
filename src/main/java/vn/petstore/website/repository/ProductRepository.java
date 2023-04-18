package vn.petstore.website.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import vn.petstore.website.model.Product;

@Component
// public interface ProductRepository<T extends Product> extends
// CrudRepository<T, Long> {
public interface ProductRepository extends JpaRepository<Product, Long> {
    // @Query(value = "SELECT * FROM Product p WHERE p.name = :name and p.age =
    // :age", nativeQuery = true)
    // Pet findByNamedAndAgeNative(@Param("name") Integer name, @Param("age") String
    // age);

    // <S extends Product> S save(Pet entity);

    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM products p WHERE p.name LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%", countQuery = "SELECT count(*) FROM products p WHERE p.name LIKE %?1%"
                    + " OR CONCAT(p.price, '') LIKE %?1%", nativeQuery = true)
    public List<Product> search(String keyword);

    public Long countById(Long id);

    Page<Product> findAllByNameContains(String name, Pageable pageable);

    // Page<Product> findAllByColorContains(String color, Pageable pageable);
    // Page<Product> findAllByBrandContains(String brand, Pageable pageable);

    Page<Product> findAllByPriceEquals(Double price, Pageable pageable);

}
