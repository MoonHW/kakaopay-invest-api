package com.kakaopay.controller;


import com.kakaopay.entity.InvestParam;
import com.kakaopay.service.InvestServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InvestController {
    private InvestServiceImpl investService;

    public InvestController (InvestServiceImpl investService){
        this.investService = investService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/invest")
    public JSONObject get(@RequestHeader(value = "X_USER_ID") Long X_USER_ID, @RequestBody InvestParam investParam){
       return investService.invest(X_USER_ID, investParam);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/mystatus")
    public JSONArray get(@RequestHeader(value = "X_USER_ID") Long X_USER_ID){
        return investService.get(X_USER_ID);
    }

}
