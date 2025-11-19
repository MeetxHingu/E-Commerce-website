package com.ecommerce.Repository;

import com.ecommerce.Model.orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderitemRepo extends JpaRepository<orderitem, Long> {

}

