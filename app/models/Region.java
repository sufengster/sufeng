package models;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.templates.JavaExtensions;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-12-31
 * Time: 上午1:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Region extends Model {
    @Required
    @MaxSize(50)
    @MinSize(1)
    public String name;
    public String enname;
    public String description;
    @ManyToOne
    public Region parent;

    @Column(name = "region_index")
    public String index;

    @PreUpdate
    @PrePersist
    void index() {
        this.index = JavaExtensions.noAccents(this.name).toLowerCase() + " ";
        this.index += JavaExtensions.noAccents(this.enname).toLowerCase() + " ";
        this.index += JavaExtensions.noAccents(this.parent == null?"":this.parent.name).toLowerCase() + " ";
        this.index += JavaExtensions.noAccents(this.parent == null?"":this.parent.enname).toLowerCase() + " ";
        this.index += JavaExtensions.noAccents(this.description).toLowerCase();
    }

    public static List search(String by) {
        return find("from Region r where r.index like ?", JavaExtensions.noAccents("%" + by.toLowerCase() + "%")).fetch();
    }

    public String toString() {
        return name;
    }
}
