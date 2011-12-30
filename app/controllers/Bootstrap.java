package controllers;

import config.Constant;
import models.Forum;
import models.Region;
import play.cache.Cache;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-30
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
@OnApplicationStart
public class Bootstrap extends Job{
    public void doJob(){
        List<Forum> allForums = Forum.findAll();
        if(allForums!=null && allForums.size()>0){
            Cache.set(Constant.ALLFORUMS, allForums);
            for(Forum f: allForums){
                Cache.set(Constant.FORUM+f.getId(),f);
            }
        }

        List<Region> allRegions = Region.findAll();
        if(allRegions!=null && allRegions.size()>0){
            Cache.set(Constant.ALLREGIONS, allRegions);
            for(Region r: allRegions){
                Cache.set(Constant.REGION+r.getId(),r);
            }
        }


    }
}
