package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vn.petstore.website.model.Pet;

@Component
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT * FROM Pet p WHERE p.name = :name and p.age = :age", nativeQuery = true)
    Pet findPetByNamedAndAgeNative(@Param("name") Integer name, @Param("age") String age);
}
