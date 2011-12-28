package models;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-28
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
import play.data.validation.*;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {

    @Email
    @Required
    public String email;

    @Required
    @MaxSize(15)
    @MinSize(5)
    public String password;

    @Required
    @MaxSize(100)
    public String nickname;
    
    public String avatar;

    public boolean isAdmin;

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    public String toString() {
        return email;
    }

}
