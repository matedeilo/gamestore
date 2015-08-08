package com.upc.controller;

import com.store.ws.GamesService;
import com.upc.bean.Article;
import com.upc.bean.Category;
import com.upc.bean.User;
import com.upc.service.DeliveryClientImpl;
import com.upc.service.GameClientImpl;
import com.upc.service.SubscriptionClientImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    GameClientImpl gameClient;

    @Autowired
    SubscriptionClientImpl subscriptionClient;

    @Autowired
    DeliveryClientImpl deliveryClient;

    @Bean
    public JaxWsProxyFactoryBean jaxWsProxyFactoryBean(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress("http://localhost:8090/ws-server-1.0/services/gameServices");
        factoryBean.setServiceClass(GamesService.class);
        return factoryBean;
    }

    @RequestMapping("/")
    public String index(@ModelAttribute User user, Model model){
        GamesService client = (GamesService) jaxWsProxyFactoryBean().create();
        model.addAttribute("article150071", client.getGames("150071"));
        model.addAttribute("article150064", client.getGames("150064"));
        return "home";
    }

    @RequestMapping("detail/{articleId}")
    public String gamedetail(HttpServletRequest request, Model model, @ModelAttribute User user, @PathVariable String articleId) {
        try {
            Article article = gameClient.getGameDetail(articleId);
            model.addAttribute("article", gameClient.getGameDetail(articleId));
            return "detail";
        } catch (Exception e){
            if(e.getMessage().equals("notfound")) {
                model.addAttribute("message", "Juego no fue encontrado");
            }
            return "error";
        }
    }

    @RequestMapping("purchase/{articleId}")
    public String purchase(Model model, @ModelAttribute User user, @PathVariable String articleId,
                           HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
        if (session.getAttribute("user") == null) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("message", "Inicia sesion para realizar la compra");
            return "redirect:/detail/"+articleId;
        }
        User userSession = (User) session.getAttribute("user");
        subscriptionClient.createSubscription(userSession.getNumber());
        String routeApk = deliveryClient.downloadGame(userSession.getNumber(), articleId);
        model.addAttribute("apk", routeApk);

        userSession.setHasSubscrition(true);
        session.setAttribute("user", userSession);
        return "purchaseConfirmation";
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
