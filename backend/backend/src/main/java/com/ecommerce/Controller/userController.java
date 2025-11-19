package com.ecommerce.Controller;

import com.ecommerce.Model.*;
import com.ecommerce.Repository.orderRepo;
import com.ecommerce.Repository.orderitemRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.ecommerce.Repository.cartRepo;
import com.ecommerce.Service.userService;
import com.ecommerce.Service.productService;
import com.ecommerce.Repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import  java.util.List;


@Controller

public class userController {
    @Autowired
    private userService userService;

    @GetMapping("")
    public String indexUser() {
        return "/User/indexUser";
    }

    //-------------------------------------------------Login---------------------------------------------------
    @GetMapping("/user/login")
    public String loginPage() {
        return "/User/login";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model, HttpSession session) {
        user founduser = userService.login(email, password);
        if (founduser != null) {
            session.setAttribute("session", founduser);
            model.addAttribute("user", founduser);
            return "User/userDashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "User/login";
        }
    }

    //------------------------------------------------------Register----------------------------------------------
    @GetMapping("/user/register")
    public String RegistrationForm(Model model) {
        model.addAttribute("user", new user());
        return "User/userRegister";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute user user, Model model) {
        try {
            userService.register(user);
            return "redirect:/user/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "User/userRegister";
        }
    }


    @GetMapping("/user/userDashboard")
    public String userDashboard(HttpSession session, Model model) {
        user currentUser = (user) session.getAttribute("session");

        if (currentUser == null) {
            model.addAttribute("loggedIn", false);
            model.addAttribute("error", "User is not logged in.");
            return "User/userDashboard";
        }
        model.addAttribute("loggedIn", true);
        model.addAttribute("user", currentUser);

        return "User/userDashboard";
    }


    @Autowired
    private  productRepo productRepo;
    @Autowired
    private productService productService;

    @GetMapping("/user/products")
    public String viewAllProducts(Model model) {
        List<product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "User/products";
    }


    @Autowired

    private cartRepo cartRepo;


    @PostMapping("/user/cart/add")
    public String addToCart(@RequestParam("productId") Long productId, @SessionAttribute("session") user user) {
        product product = productRepo.findById(productId).orElse(null);
        if (product != null) {
            cart cart = new cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(1);
            cartRepo.save(cart);
        }
        return "redirect:/user/products";
    }

    @GetMapping("/user/cart")
    public String viewCart(@SessionAttribute("session") user user, Model model) {
        List<cart> cart = cartRepo.findByUser(user);
        model.addAttribute("cart", cart);
        return "User/cart";
    }

    @PostMapping("/user/cart/delete")
    public String deleteCartItem(@RequestParam("cartId") Long cartId) {
        cartRepo.deleteById(cartId);
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/increase")
    public String increaseQuantity(@RequestParam("cartId") Long cartId) {
        cart item = cartRepo.findById(cartId).orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            cartRepo.save(item);
        }
        return "redirect:/user/cart";
    }

    @PostMapping("/user/cart/decrease")
    public String decreaseQuantity(@RequestParam("cartId") Long cartId) {
        cart item = cartRepo.findById(cartId).orElse(null);
        if (item != null && item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartRepo.save(item);
        } else if (item != null) {
            cartRepo.delete(item);
        }
        return "redirect:/user/cart";
    }



    @Autowired
    private orderRepo orderrepo;

    @Autowired
    private  orderitemRepo orderitemRepo;


    @PostMapping("/user/placeorder")
    public String placeOrder(@SessionAttribute("session") user user, Model model) {

        order newOrder = new order();
        newOrder.setUser(user);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setStatus("Completed");


        List<cart> cartItems = cartRepo.findByUser(user);


        double totalAmount = 0;
        for (cart cartItem : cartItems) {
            orderitem newOrderItem = new orderitem();
            newOrderItem.setProduct(cartItem.getProduct());
            newOrderItem.setQuantity(cartItem.getQuantity());
            newOrderItem.setPrice(cartItem.getProduct().getPrice());
            newOrderItem.setOrder(newOrder);

            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        newOrder.setTotalAmount(totalAmount);
        orderrepo.save(newOrder);


        for (cart cartItem : cartItems) {
            orderitem newOrderItem = new orderitem();
            newOrderItem.setProduct(cartItem.getProduct());
            newOrderItem.setQuantity(cartItem.getQuantity());
            newOrderItem.setPrice(cartItem.getProduct().getPrice());
            newOrderItem.setOrder(newOrder);
            orderitemRepo.save(newOrderItem);
        }


        cartRepo.deleteAll(cartItems);


        model.addAttribute("order", newOrder);
        return  "redirect:/user/order";

    }


    @GetMapping("/user/placeorder")
    public String showPlaceOrderPage(
            @SessionAttribute("session") user user,
            Model model
    ) {

        System.out.println("Inside GET /user/placeorder");
        List<cart> cartItems = cartRepo.findByUser(user);
        model.addAttribute("cart", cartItems);


        double total = cartItems.stream()
                .mapToDouble(ci -> ci.getProduct().getPrice() * ci.getQuantity())
                .sum();
        model.addAttribute("total", total);

        return "User/placeorder";
    }

    @GetMapping("/user/order")
    public String viewOrders(@SessionAttribute("session") user user, Model model) {
        List<order> orders = orderrepo.findByUser(user);
        model.addAttribute("orders", orders);
        return "User/vieworder";
    }

    @PostMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }



}