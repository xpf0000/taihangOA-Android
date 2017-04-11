package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class DepartmentModel implements Serializable {


    /**
     * id : 20
     * name : 部门A
     * description : 666999
     * parent : 0
     * sub : []
     */

    private String id;
    private String name;
    private String description;
    private String parent;
    private List<?> sub;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<?> getSub() {
        return sub;
    }

    public void setSub(List<?> sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent='" + parent + '\'' +
                ", sub=" + sub +
                '}';
    }
}
