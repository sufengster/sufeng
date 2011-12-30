package controllers;

import config.Constant;
import models.User;
import play.cache.Cache;
import play.mvc.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-26
 * Time: 下午10:34
 * To change this template use File | Settings | File Templates.
 */
public class BaseController extends Controller {
    protected static ThreadLocal<String> template = new ThreadLocal<String>();
    @Before
    static void before() {
//        System.out.println(Http.Request.current().headers.get("user-agent").value());
        // test branch.sufeng
        checkSession();
        template.set(Http.Request.current().controller+"/"+getVersion()+"/"+Http.Request.current().actionMethod+".html");  //get path
    }

//    @After(only = {"login","logout"})
//    static void afterLoginLogout(){
//        checkSession();
//    }

    static void checkSession(){

        String userid = session.get(Constant.SESSIONID);
        if(userid!=null){
            User user = getUserFromCache(userid);
            renderArgs.put("loginUser",user);
        }else{
            renderArgs.put("loginUser",null);
            System.out.println("put loginuser null");
        }
        System.out.println("loginuser:"+userid);
    }
    
    static String getTemplatePath(String template){
        return "@"+Http.Request.current().controller+"."+getVersion()+"."+template;
    }

    static String getVersion(){
        //need judge from useragent
        return "iphone";
    }

    private static User getUserFromCache(String userid){
        User user = (User) Cache.get("user_"+userid);
        if(user == null){
            try {
                user = User.findById(Long.valueOf(userid));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Cache.set("user_" + userid, user, "30mn");
        }
        return user;
    }
}
