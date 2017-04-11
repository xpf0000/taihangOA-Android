package model;

import java.io.Serializable;

/**
 * Created by X on 2016/11/19.
 */

public class ChongzhiModel implements Serializable {

    /**
     * id : 4
     * money : 50
     * value : 80
     */

    private String id;
    private String money;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
