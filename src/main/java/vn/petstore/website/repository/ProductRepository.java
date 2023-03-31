package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
