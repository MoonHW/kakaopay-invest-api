package com.kakaopay.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.kakaopay.entity.Invest;
import com.kakaopay.entity.InvestParam;
import com.kakaopay.entity.Product;
import com.kakaopay.repository.InvestRepository;
import com.kakaopay.repository.ProductRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.JSONArray;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvestServiceImpl implements InvestService{

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final InvestRepository investRepository;

    public InvestServiceImpl(ProductRepository productRepository, InvestRepository investRepository) {
        this.productRepository = productRepository;
        this.investRepository = investRepository;
    }


    @Override
    @Transactional
    public JSONObject invest(Long xUserId, InvestParam investParam) throws RuntimeException {

        Invest investObject = investRepository.findByProductIdAndUserId(investParam.getProductId(),xUserId);
        Product productObject = productRepository.findByProductId(investParam.getProductId());
        LocalDateTime investDate = LocalDateTime.now();
        JSONObject jsonObject = new JSONObject();

    if(productObject != null){
        if(productObject.getProductStatus().equalsIgnoreCase("Y")){
            if((productObject.getCurrentInvestingAmount() + investParam.getInvestAmount()) <= productObject.getTotalInvestingAmount()){
                if(investObject == null){
                    investObject = new Invest(xUserId,investParam.getProductId(),investParam.getInvestAmount(),investDate,productObject.getTotalInvestingAmount());
                    productObject.setInvestors(productObject.getInvestors() + 1);
                }else{
                    investObject.setMyInvestAmount(investObject.getMyInvestAmount() + investParam.getInvestAmount());
                    investObject.setInvestAmount(productObject.getTotalInvestingAmount());
                }
                jsonObject.put("result", "SUCCESS");
                productObject.setCurrentInvestingAmount(productObject.getCurrentInvestingAmount()+investParam.getInvestAmount());
                this.productRepository.save(productObject);
                this.investRepository.save(investObject);
                if(productObject.getCurrentInvestingAmount() == productObject.getTotalInvestingAmount()){
                    productObject.setProductStatus("N");
                    this.productRepository.save(productObject);
                }
            }else {
                jsonObject.put("result", "Invest amount exceed Total invest available amount !!");
            }
        }else{
            jsonObject.put("result", "SOLD OUT");
        }
    }else{
        jsonObject.put("result", "There is no such Product");
        throw new RuntimeException("There is no such Product");
    }

    return jsonObject;
    }

    @Override
    public JSONArray get(Long xUserId) throws RuntimeException {
        org.json.simple.JSONArray jsonArray = new org.json.simple.JSONArray();
        List<Invest> products =  investRepository.findByUserId(xUserId);
        if(products.size() == 0){
            throw new RuntimeException("There is no Product found !");
        }
        for (Invest invest: products) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("상품 ID", invest.getProductId());
            jsonObject.put("상품 제목", invest.getProduct().getTitle());
            jsonObject.put("나의 투자금",invest.getMyInvestAmount());
            jsonObject.put("총 모집금", invest.getInvestAmount());
            jsonObject.put("투자일", invest.getInvestDate());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
