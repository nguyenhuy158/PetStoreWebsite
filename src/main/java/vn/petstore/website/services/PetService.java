package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Pet;
import vn.petstore.website.repository.PetRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    PetRepository petRepository;

    public List<Pet> getAllPets(Integer limit) {
        return Optional
                .ofNullable(limit)
                .map(value -> petRepository.findAll(PageRequest.of(0, limit)).getContent())
                .orElseGet(() -> petRepository.findAll());
    }
}
