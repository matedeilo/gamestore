package com.upc.service;

import com.upc.bean.DeliveryWS;
import com.upc.bean.Subscription;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionClientImpl {

    public void createSubscription(String userId) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8090/ws-server-1.0/restservices/subscription/create";

        try {
            Subscription subscription = new Subscription();
            subscription.setUser_id(userId);
            subscription.setType("standard");
            String result = restTemplate.postForObject(URL, subscription, String.class);
            System.out.println(result);
        } catch (HttpStatusCodeException e) {
            throw new Exception(e);
        }
    }

}
