package com.kakaopay.service;

import com.kakaopay.entity.Product;
import com.kakaopay.entity.ProductParam;
import org.json.simple.JSONArray;


public interface ProductService {
    JSONArray get() throws  Exception;
}
