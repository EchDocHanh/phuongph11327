/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import model.BaiHat;

/**
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) {
        ObjectMapper mapper = new JsonMapper();
       
        BaiHat bh = new BaiHat();
        bh.setTenBH("xx");
        try {
            
            String json = mapper.writeValueAsString(bh);
            toJson toJson1 = new toJson("post",json);
            String json2 = mapper.writeValueAsString(toJson1);
            
            System.out.println(json2);
            
            JsonNode jsonNode = mapper.readTree(json2);
            String data = jsonNode.get("data").asText();
            String TenBH = jsonNode.get("type").asText();
            
               System.out.println(TenBH);
               BaiHat bh2 = mapper.readValue(data,BaiHat.class);
               System.out.println(bh2.getTenBH());
         
        } catch (Exception e) {
            System.out.println(e);
        }
    }
 
}
