package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.petstore.website.model.Category;

@Component
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
