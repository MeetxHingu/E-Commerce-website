package com.ecommerce.Repository;

import com.ecommerce.Model.admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepo extends JpaRepository<admin, Long> {

    admin findByUsername(String username);
}
