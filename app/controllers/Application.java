package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends BaseController {
    //test
    public static void index() {
        render(template.get());
    }

}