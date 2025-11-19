package com.ecommerce.Repository;

import com.ecommerce.Model.product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepo extends JpaRepository<product, Long> {
}
