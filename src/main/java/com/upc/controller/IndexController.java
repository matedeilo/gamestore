package com.upc.controller;

import com.store.ws.GamesService;
import com.upc.bean.Article;
import com.upc.bean.Category;
import com.upc.bean.Device;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Bean
    public JaxWsProxyFactoryBean jaxWsProxyFactoryBean(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://localhost:8090/ws-server-1.0/services/gameServices");
        factoryBean.setServiceClass(GamesService.class);
        return factoryBean;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model){
        GamesService client = (GamesService) jaxWsProxyFactoryBean().create();
        model.put("article150071", client.getGames("150071"));
        model.put("article150064", client.getGames("150064"));
        return "home";
    }

    @RequestMapping("detail/{articleId}")
    public String gamedetail(Map<String, Object> model, @PathVariable String articleId){
        model.put("article", generateArticleData());
        return "detail";
    }

    @RequestMapping("purchase")
    public String purchase(Map<String, Object> model){
        model.put("article", generateArticleData());
        return "purchaseConfirmation";
    }

    @RequestMapping("mygames")
    public String mygames(Map<String, Object> model){
        model.put("article", generateArticleData());
        return "mygames";
    }

    @RequestMapping("login")
    public String login(Map<String, Object> model){
        return "login";
    }

    @RequestMapping("admin")
    public String admin(Map<String, Object> model){
        return "admin";
    }

    @RequestMapping("suscripcionAdmin")
    public String suscripcionAdmin(Map<String, Object> model){
        return "suscripcionAdmin";
    }

    @RequestMapping("suscripcionCancel")
    public String suscripcionCancel(Map<String, Object> model){
        return "suscripcionCancelConfirma";
    }


    @RequestMapping("articuloAdmin")
    public String articuloAdmin(Map<String, Object> model){
        return "articuloAdmin";
    }

    private Article generateArticleData(){
        Article article = new Article();
        article.setId(1415);
        article.setName("Spiderman");
        article.setLicensor("Gameloft");
        article.setDescription("Lorem ipsum");
        article.setDefaultUrl("http://url.com");
        Category category = new Category();
        category.setId(1);
        category.setName("Estrenos");
        article.setCategory(category);
        return article;
    }


}
