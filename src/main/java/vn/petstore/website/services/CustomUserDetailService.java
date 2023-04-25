package vn.petstore.website.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.petstore.website.model.CustomUserDetails;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    // paginated
    // public Product getProductById(Long id) {
    // return productRepository.findById(id).isPresent() ?
    // productRepository.findById(id).get() : null;
    // }

    // public List<Product> getAllProducts(Integer limit) {
    // return Optional
    // .ofNullable(limit)
    // .map(value -> productRepository.findAll(PageRequest.of(0,
    // limit)).getContent())
    // .orElseGet(() -> productRepository.findAll());
    // }

    // public Page<Product> findPaginated(Pageable pageable) {
    // List<Product> products = productRepository.findAll();

    // int pageSize = pageable.getPageSize();
    // int currentPage = pageable.getPageNumber();
    // int startItem = currentPage * pageSize;
    // List<Product> list;

    // if (products.size() < startItem) {
    // list = Collections.emptyList();
    // } else {
    // int toIndex = Math.min(startItem + pageSize, products.size());
    // list = products.subList(startItem, toIndex);
    // }

    // return new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize),
    // products.size());
    // }

    // public PaginatedProductResponse readProducts(Pageable pageable) {
    // Page<Product> products = productRepository.findAll(pageable);
    // return PaginatedProductResponse.builder()
    // .numberOfItems(products.getTotalElements())
    // .numberOfPages(products.getTotalPages())
    // .products(products.getContent())
    // .build();
    // }

    // public PaginatedProductResponse filterBooks(String keyword, Pageable
    // pageable) {
    // Page<Product> products = productRepository.findAllByNameContains(keyword,
    // pageable);
    // // products.and(productRepository.findAllByBrandContains(keyword, pageable));
    // // products.and(productRepository.findAllByColorContains(keyword, pageable));
    // try {
    // double parseDouble = Double.parseDouble(keyword);
    // products.and(productRepository.findAllByPriceEquals(parseDouble, pageable));
    // } catch (Exception e) {
    // // System.out.println(e.getMessage());
    // }
    // return PaginatedProductResponse.builder()
    // .numberOfItems(products.getTotalElements())
    // .numberOfPages(products.getTotalPages())
    // .products(products.getContent())
    // .build();
    // }

    // public void save(Product product) {
    // productRepository.save(product);
    // }

    // public Product get(Long id) throws Exception {
    // Optional<Product> result = productRepository.findById(id);
    // if (result.isPresent()) {
    // return result.get();
    // }
    // throw new Exception("Could not find any users with ID " + id);
    // }

    // public void delete(Long id) throws Exception {
    // Long count = productRepository.countById(id);
    // if (count == null || count == 0) {
    // throw new Exception("Could not find any products with ID " + id);
    // }
    // productRepository.deleteById(id);
    // }
}
