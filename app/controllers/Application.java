package controllers;

//import models.*;

import config.Constant;
import models.User;
import play.cache.Cache;
import play.data.validation.Valid;

public class Application extends BaseController {
    //test
    public static void index() {
        render(template.get());
    }

    public static void register() {
        render(template.get());
    }

    public static void login(String email, String password){
        if(email!=null && password!=null){
            User user = User.find("byEmailAndPassword",email,password).first();
            if(user!=null){
                session.put(Constant.SESSIONID,user.getId());
//                render(getTemplatePath("index"));
                redirect("Application.index");
            }
        }
        render(template.get());
    }

    public static void logout() {
        session.clear();
//        index();
        redirect("Application.index");
    }

    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render(getTemplatePath("register"), user, verifyPassword);
        }
        user.create();
//        session.put("user_nickname", user.nickname);
        session.put(Constant.SESSIONID, user.getId());
        flash.success("Welcome, " + user.nickname);
        index();
    }
    


}