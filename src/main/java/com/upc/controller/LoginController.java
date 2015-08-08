package com.upc.controller;

import com.upc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String gamedetail(@ModelAttribute User user, Model model, HttpSession session) {
    ResultValidate resultValidate = validate(user);
    if (resultValidate.isValid()) {
        session.setAttribute("user", user);
    } else {
        model.addAttribute("error", resultValidate.getMessage());
    }
    return "redirect:/";
    }

    private ResultValidate validate(User user){
        if (user.getNumber().equals("997635323")){
            return new ResultValidate("success", true);
        } else if(user.getNumber().equals("997635324")){
            return new ResultValidate("Es plan prepago", false);
        } else {
            return new ResultValidate("Numero no registrado", false);
        }
    }

    class ResultValidate {
        private String message;
        private boolean valid;

        public ResultValidate(String message, boolean valid){
            this.message = message;
            this.valid = valid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }

}
