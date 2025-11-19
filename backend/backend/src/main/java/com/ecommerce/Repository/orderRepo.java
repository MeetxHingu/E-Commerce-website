package com.ecommerce.Repository;

import com.ecommerce.Model.order;
import com.ecommerce.Model.user;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepo extends JpaRepository<order, Long> {
    List<order> findByUser(user user);

}
