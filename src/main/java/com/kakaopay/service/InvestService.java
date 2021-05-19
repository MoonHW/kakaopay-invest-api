package com.kakaopay.service;

import com.kakaopay.entity.InvestParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public interface InvestService {
    public JSONObject invest(Long xUserId, InvestParam investParam) throws Exception;
    public JSONArray get(Long xUserId) throws Exception;
}
