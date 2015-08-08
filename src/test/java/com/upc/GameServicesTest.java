package com.upc;

import com.store.ws.GameWS;
import com.store.ws.GamesService;
import com.upc.bean.Article;
import com.upc.service.GameClientImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GameServicesTest {

    @Autowired
    GameClientImpl gameClient;

    @Test
    public void getGames(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://localhost:8090/ws-server-1.0/services/gameServices");
        factoryBean.setServiceClass(GamesService.class);

        GamesService client = (GamesService) factoryBean.create();
        List<GameWS> games = client.getGames("150064");

        Assert.assertTrue(games.size() > 0);
    }

    @Test
    public void getGameDetail() throws Exception {
        Article article = gameClient.getGameDetail("1");
        assert(article != null);
    }

    @Test
    public void whenGameDetailNotFoundThenThrowException() {
        try {
            Article article = gameClient.getGameDetail("100");
        } catch (Exception e){
            assert(e.getMessage().equals("notfound"));
        }
    }

}
