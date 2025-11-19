package com.ecommerce.Service;

import com.ecommerce.Model.admin;
import com.ecommerce.Model.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminService {

    @Autowired
    private com.ecommerce.Repository.adminRepo adminRepo;

    @Autowired
    private com.ecommerce.Repository.productRepo productRepo;

    public admin login(String username, String password) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            admin admin = new admin();
            admin.setId(1L);
            admin.setUsername(username);
            admin.setPassword(password);
            return admin;
        }
        return null;
    }

    public void addProduct(product product) {
        productRepo.save(product);
    }
}
