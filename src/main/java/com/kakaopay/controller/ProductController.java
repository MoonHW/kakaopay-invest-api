package com.kakaopay.controller;

import com.kakaopay.service.ProductServiceImpl;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @RequestMapping("/product")
    public JSONArray get() throws Exception {
        return productServiceImpl.get();
    }

}
