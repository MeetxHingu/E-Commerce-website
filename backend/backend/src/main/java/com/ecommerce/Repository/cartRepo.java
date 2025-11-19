package com.ecommerce.Repository;

import com.ecommerce.Model.cart;
import com.ecommerce.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface cartRepo extends JpaRepository<cart, Long> {
    List<cart> findByUser(user user);
}
