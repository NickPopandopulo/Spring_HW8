package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.ProductSearchConditional;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ModelAndView getProducts(@RequestParam(required = false) Integer minPrice,
                                    @RequestParam(required = false) Integer maxPrice) {
        ModelAndView modelAndView = new ModelAndView("product/productList");

        ProductSearchConditional searchConditional = new ProductSearchConditional();
        searchConditional.setMinPrice(minPrice);
        searchConditional.setMaxPrice(maxPrice);

        modelAndView.addObject("products", productService.getProducts(searchConditional).getContent());

        return modelAndView;
    }

    @PostMapping
    public RedirectView saveProduct(@ModelAttribute Product product,
                                    @RequestParam(required = false) MultipartFile image) {

        productService.saveProductWithImage(product, image);
        return new RedirectView("/product");
    }

}
