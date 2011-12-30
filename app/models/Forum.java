package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-30
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Forum extends Model {
    @Required
    public String name;
    @Required
    public Long parentid;

    public String toString() {
        return name;
    }
}
