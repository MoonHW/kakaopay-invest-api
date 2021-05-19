package com.kakaopay.service;
import com.kakaopay.entity.Product;
import com.kakaopay.repository.ProductRepository;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(e -> products.add(e));
        return products;
    }

    @Override
    public JSONArray get() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        List<Product> products =  productRepository.findByStartedAtBeforeAndFinishedAtAfter(LocalDateTime.now(),LocalDateTime.now());
        if(products.size() == 0){
            throw new RuntimeException("There is no Product found !");
        }
        for (Product product: products) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("상품 ID",product.getProductId());
            jsonObject.put("상품 제목",product.getTitle());
            jsonObject.put("총 모집금액",product.getTotalInvestingAmount());
            jsonObject.put("투자모집상태", product.getProductStatus().equalsIgnoreCase("Y") ? "모집중" : "모집완료");
            jsonObject.put("현재 모집금", product.getCurrentInvestingAmount());
            jsonObject.put("투자자수 ", product.getInvestors());
            jsonObject.put("상품 모집기간", product.getStartedAt() + " ~ " + product.getFinishedAt());
            jsonArray.add(jsonObject);
        }

        return jsonArray;

    }

}
