package models;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-28
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class User extends Model {

    @Email
    @Required
    public String email;

    @Required
    public String password;

    public String fullname;
    
    public String avatar;

    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }

    public String toString() {
        return email;
    }

}
