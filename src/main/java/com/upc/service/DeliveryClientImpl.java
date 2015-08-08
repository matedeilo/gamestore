package com.upc.service;

import com.store.ws.GameWS;
import com.upc.bean.Article;
import com.upc.bean.DeliveryWS;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryClientImpl {

    public String downloadGame(String userId, String gameId ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8090/ws-server-1.0/restservices/delivery/download/"+userId+"/"+gameId;

        try {
            DeliveryWS deliveryWS = restTemplate.getForObject(URL, DeliveryWS.class);
            return deliveryWS.getApkUrl();
        } catch (HttpStatusCodeException e) {
            throw new Exception(e);
        }
    }

}
