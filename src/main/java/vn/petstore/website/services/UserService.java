package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.CustomUserDetails;
import vn.petstore.website.model.User;

@Service
@RequiredArgsConstructor
public class UserService {
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        return currentUser.getUser();
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
