package vn.petstore.website.restapi;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.petstore.website.model.*;
import vn.petstore.website.repository.AdminRepository;
import vn.petstore.website.repository.CategoryRepository;
import vn.petstore.website.repository.ProductRepository;
import vn.petstore.website.repository.UserRepository;
import vn.petstore.website.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestApiController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        // adminRepository.save(new Admin("admin", "admin", 0));
        // Product gear = new Gear("plastic");
        // ArrayList<String> strings = new ArrayList<>();
        // strings.add("https://t.ly/MrUp");
        // strings.add("https://t.ly/uC8e");
        // gear.setThumbnail(strings);
        // gear.setName("Vong co");
        // gear.setPrice(1000);
        // Category category = new Category();
        // category.setName("trang bi");
        // categoryRepository.save(category);
        // gear.setCategory(category);
        // productRepository.save(gear);
    }

    @GetMapping("/product")
    public List<Product> getPets() {
        return productService.getAllProducts(null);
    }
}
