package com.upc;


import com.upc.service.DeliveryClientImpl;
import com.upc.service.GameClientImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DeliveryClientTest {

    @Autowired
    DeliveryClientImpl deliveryClient;

    @Test
    public void downloadGame() throws Exception {
        String url = deliveryClient.downloadGame("1", "5");
        assert(StringUtils.hasText(url));

    }

}
