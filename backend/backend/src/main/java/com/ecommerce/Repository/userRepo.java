package com.ecommerce.Repository;

import com.ecommerce.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<user, Long> {


    boolean existsByEmail(String email);
    user findByUsername(String username);
    user findByEmail(String email);



}
