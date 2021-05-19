package com.kakaopay.controller;

import com.kakaopay.entity.Invest;
import com.kakaopay.entity.InvestParam;
import com.kakaopay.service.ProductService;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvestControllerTest {

    private long testProductId = 1;
    private long testProductAmount = 10;

    @Autowired
    private ProductService productService;
    @Autowired
    private InvestController investController;

    @Test
    public void 나의투자상품조회API테스트() throws  Exception {
        long testUserID = 22333;
        List<Invest> response = this.investController.get(testUserID);
        assertThat(response).isNotNull();
    }

    @Test
    public void 투자API테스트_성공() throws Exception{

        InvestParam testInvest = new InvestParam();
        testInvest.setProductId(this.testProductId);
        testInvest.setInvestAmount(this.testProductAmount);
        long testUserID = 22334;
        JSONObject response = this.investController.get(testUserID,testInvest);
        assertThat(response).isNotNull();
        assertThat(response.get("result")).isEqualTo("SUCCESS");
    }

    @Test
    public void 투자API테스트_실패_총수량오버() throws Exception{

        InvestParam testInvest = new InvestParam();
        testInvest.setProductId(this.testProductId);
        testInvest.setInvestAmount(this.testProductAmount + 1000000);
        long testUserID = 22333;
        JSONObject response = this.investController.get(testUserID,testInvest);
        assertThat(response.get("result")).isEqualTo("Invest amount exceed Total invest available amount !!");
    }

    @Test
    public void 투자API테스트_실패_상품상태불가() throws Exception{

        InvestParam testInvest = new InvestParam();
        testInvest.setProductId(4);
        testInvest.setInvestAmount(this.testProductAmount);
        long testUserID = 22333;
        JSONObject response = this.investController.get(testUserID,testInvest);
        assertThat(response.get("result")).isEqualTo("SOLD OUT");

    }
}
