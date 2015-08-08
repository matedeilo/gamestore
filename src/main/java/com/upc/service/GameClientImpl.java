package com.upc.service;

import com.store.ws.GameWS;
import com.upc.bean.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class GameClientImpl {

    private static final Logger log = LoggerFactory.getLogger(GameClientImpl.class);

    public Article getGameDetail(String gameId) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8090/ws-server-1.0/restservices/game/detail/"+gameId;

        try {
            GameWS game = restTemplate.getForObject(URL, GameWS.class);
            Article article = new Article();
            article.setId(game.getId());
            article.setDescription(game.getDescription());
            article.setLicensor(game.getLicensorName());
            article.setName(game.getName());
            return article;
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new Exception("notfound");
            }
            throw new Exception(e);
        }
    }
}
