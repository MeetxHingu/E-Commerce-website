package com.ecommerce.Controller;

import com.ecommerce.Model.order;
import com.ecommerce.Model.user;
import com.ecommerce.Repository.orderRepo;

import  com.ecommerce.Service.productService;
import com.ecommerce.Service.adminService;
import com.ecommerce.Model.admin;
import com.ecommerce.Model.product;
import com.ecommerce.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class adminController {
    @Autowired
    private adminService adminService;


    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "Admin/login";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        admin admin = adminService.login(username, password);
        if (admin != null) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "Admin/login";
        }
    }
    @GetMapping("/admin/dashboard")
    public String dashboard(){
        return "Admin/dashboard";
    }
    @Autowired
    private userService userservice;

    @GetMapping("/admin/manage-user")
    public String manageUsers(Model model) {
        List<user> users = userservice.getAllUsers();
        model.addAttribute("users", users);
        return "Admin/manage-user";
    }
    @GetMapping("/admin/manage-user/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userservice.deleteUser(id);
        return "redirect:/admin/manage-user";
    }


    @Autowired
    private productService productService;

    @GetMapping("/admin/manage-products")
    public String manageProducts(Model model) {
        List<product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        model.addAttribute("product", new product());
        return "Admin/manage-products";
    }


    @PostMapping("/admin/manage-products/addUpdate")
    public String addProduct(@ModelAttribute product product) {
        productService.saveProduct(product);
        return "redirect:/admin/manage-products";
    }


    @GetMapping("/admin/manage-products/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/manage-products";
    }
    @Autowired

    private  orderRepo orderRepo;

    @GetMapping("/admin/orders")
    public String viewOrders(Model model) {
        List<order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "Admin/manage-orders";
    }

    @PostMapping("/admin/orders/update")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        order ord = orderRepo.findById(orderId).orElse(null);
        if (ord != null) {
            ord.setStatus(status);
            orderRepo.save(ord);
        }
        return "redirect:/admin/orders";
    }

    @GetMapping("admin/logout")
    public String logout() {
        return "redirect:/admin/login";
    }
}
