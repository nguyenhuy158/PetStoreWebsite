package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import vn.petstore.website.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
