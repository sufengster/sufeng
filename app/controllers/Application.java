package controllers;

//import models.*;

import models.User;
import play.data.validation.Valid;

public class Application extends BaseController {
    //test
    public static void index() {
        render(template.get());
    }

    public static void register() {
        render(template.get());
    }

    public static void saveUser(@Valid User user, String verifyPassword) {
        validation.required(verifyPassword);
        validation.equals(verifyPassword, user.password).message("Your password doesn't match");
        if(validation.hasErrors()) {
            render(getTemplatePath("register"), user, verifyPassword);
        }
        user.create();
        session.put("user", user.nickname);
        flash.success("Welcome, " + user.nickname);
        index();
    }

}