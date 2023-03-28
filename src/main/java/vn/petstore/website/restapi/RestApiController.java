package vn.petstore.website.restapi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.petstore.website.model.Admin;
import vn.petstore.website.model.Pet;
import vn.petstore.website.model.Product;
import vn.petstore.website.repository.AdminRepository;
import vn.petstore.website.repository.UserRepository;
import vn.petstore.website.services.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestApiController {

    @Autowired
    PetService petService;
    @Autowired
    PetService petRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @PostConstruct
    public void init() {
        adminRepository.save(new Admin("admin", "admin", 0));
//        petRepository.
    }

    @GetMapping("/product")
    public List<Product> getPets() {
        return petService.getAllProducts(null);
    }
}
