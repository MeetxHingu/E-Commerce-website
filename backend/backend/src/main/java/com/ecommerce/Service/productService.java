package com.ecommerce.Service;

import com.ecommerce.Model.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    private com.ecommerce.Repository.productRepo productRepo;

    public List<product> getAllProducts() {
        return productRepo.findAll();
    }

    public product getProductById(Long id) {

        return productRepo.findById(id).orElse(null);
    }


//    public product saveProduct(product product) {
//
//        return productRepo.save(product);
//    }
    public void saveProduct(product p) {
        if (p.getId() == null) {
            // New product
            productRepo.save(p);
        } else {
            // Existing product — only save if it actually exists
            if (productRepo.existsById(p.getId())) {
                productRepo.save(p);
            }
        }
    }

        public void deleteProduct (Long id){

            productRepo.deleteById(id);
        }
    }


