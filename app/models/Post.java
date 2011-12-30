package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-30
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Post extends Model {
    @Required(message="请填写标题")
    @MaxSize(50)
    public String title;
    @Required(message="说点什么吧")
    @MaxSize(20000)
    @Lob
    public String content;
    @ManyToOne
    public User author;
    @Required
    @ManyToOne
    public Forum forum;
    public Date postedAt = new Date();
    @ManyToMany
    public List<Tag> tags;

    public String toString() {
        return title;
    }
}
