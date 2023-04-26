package vn.petstore.website.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.dto.PaginatedUserResponse;
import vn.petstore.website.emun.Role;
import vn.petstore.website.model.CustomUserDetails;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

            return currentUser.getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public boolean isLogin() {
        return getCurrentUser() != null;
    }

    public boolean isAdmin() {
        if (getCurrentUser() == null) {
            return false;
        }
        return getCurrentUser().getRole().name().equals(Role.ADMIN.name());
    }

    // paginated
    public User getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public List<User> getAllUsers(Integer limit) {
        return Optional
                .ofNullable(limit)
                .map(value -> userRepository.findAll(PageRequest.of(0, limit)).getContent())
                .orElseGet(() -> userRepository.findAll());
    }

    public Page<User> findPaginated(Pageable pageable) {
        List<User> users = userRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;

        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }

        return new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), users.size());
    }

    public PaginatedUserResponse readProducts(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return PaginatedUserResponse.builder()
                .numberOfItems(users.getTotalElements())
                .numberOfPages(users.getTotalPages())
                .users(users.getContent())
                .build();
    }

    public PaginatedUserResponse filterBooks(String keyword, Pageable pageable) {
        Page<User> users = userRepository.findAllByUsernameContains(keyword, pageable);
        // products.and(productRepository.findAllByBrandContains(keyword, pageable));
        // products.and(productRepository.findAllByColorContains(keyword, pageable));
        try {
            users.and(userRepository.findAllByPhoneContains(keyword, pageable));
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
        return PaginatedUserResponse.builder()
                .numberOfItems(users.getTotalElements())
                .numberOfPages(users.getTotalPages())
                .users(users.getContent())
                .build();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Long id) throws Exception {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any users with ID " + id);
    }

    public void delete(Long id) throws Exception {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new Exception("Could not find any products with ID " + id);
        }
        userRepository.deleteById(id);
    }
}
