package com.upc;

import com.store.ws.GameWS;
import com.store.ws.GamesService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameServicesTest {

    @Test
    public void getGames(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://localhost:8090/ws-server-1.0/services/gameServices");
        factoryBean.setServiceClass(GamesService.class);

        GamesService client = (GamesService) factoryBean.create();
        List<GameWS> games = client.getGames("150064");

        Assert.assertTrue(games.size() > 0);
    }

}
