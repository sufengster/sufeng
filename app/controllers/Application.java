package controllers;

//import models.*;

import config.Constant;
import models.Forum;
import models.Post;
import models.Region;
import models.User;
import play.cache.Cache;
import play.data.validation.Valid;

import java.util.List;

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
                checkSession();
                redirect("Application.index");
            }
        }
        render(template.get());
    }

    public static void logout() {
        session.clear();
//        index();
        checkSession();
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

    public static void listForums(){
        List<Forum> allForums = (List<Forum>) Cache.get(Constant.ALLFORUMS);
        System.out.println("size:"+allForums.size());
        renderArgs.put("allforums",allForums);
        render(template.get());
    }

    public static void forum(long fid){
        Forum forum = (Forum) Cache.get(Constant.FORUM + fid);
//        renderArgs.put("forum",forum);
        List<Post> posts =  Post.find("byForum",forum).fetch();
        render(template.get(), forum, posts);
    }

    public static void addPost(long fid){
        render(template.get(),fid);
    }

    public static void savePost(@Valid Post post){
        if(post!=null){
            System.out.println(post.toString());
            post.save();
            Cache.delete(Constant.FORUMPOSTCOUNT + post.forum.id);
        }
        forum(post.forum.getId());
    }

    public static void setting(){
        List<Region> regions = (List<Region>) Cache.get(Constant.ALLREGIONS);
        if(regions!=null && regions.size()>0) {
            System.out.println("regions size:"+regions.size());
            renderArgs.put("regions",regions);
        }
        render(template.get());
    }
    


}