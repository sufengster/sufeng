package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

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
    static void getTemplatePath() {
        System.out.println(Http.Request.current().headers.get("User-Agent").value());
        // test branch.
        template.set(Http.Request.current().controller+"/iphone/"+Http.Request.current().actionMethod+".html");  //get path
    }
}
