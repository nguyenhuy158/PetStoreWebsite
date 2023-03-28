package vn.petstore.website.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.petstore.website.model.Admin;
import vn.petstore.website.repository.AdminRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public boolean isValid(Admin admin) {
        Admin byUsername = adminRepository.findByUsername(admin.getUsername());
        if (byUsername == null) {
            return false;
        }
        return byUsername.getPassword().equals(admin.getPassword());
    }
}
