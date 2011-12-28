package controllers;

import play.mvc.Before;
import play.mvc.Http;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-28
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class BaseCRUD extends CRUD{
    protected static ThreadLocal<String> template = new ThreadLocal<String>();
    @Before
    static void getTemplatePath() {
        System.out.println(Http.Request.current().headers.get("user-agent").value());
        template.set(Http.Request.current().controller+"/iphone/"+Http.Request.current().actionMethod+".html");  //get path
    }
}
