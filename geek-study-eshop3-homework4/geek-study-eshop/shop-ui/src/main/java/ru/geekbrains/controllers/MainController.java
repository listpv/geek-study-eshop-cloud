package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/", "/index", "/products"})
    public String indexPage(Model model) {
        model.addAttribute("products", productService.findAllAndSplitProductsBy(3));
        return "products";
    }

    @RequestMapping("/product_details/{id}")
    public String productDetailPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow(IllegalArgumentException::new));
        return "product_details";
    }
}
