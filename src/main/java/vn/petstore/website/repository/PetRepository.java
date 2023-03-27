package vn.petstore.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import vn.petstore.website.model.Pet;

@Component
public interface PetRepository extends JpaRepository<Pet, Long> {
}
