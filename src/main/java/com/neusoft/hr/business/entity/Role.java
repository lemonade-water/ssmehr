package com.neusoft.hr.business.entity;

import java.util.ArrayList;
import java.util.List;

public class Role extends BaseEntity{
    private String roleName;
    private List<Menu> menuList;

    public String getRoleName() {
        return roleName;
    }

    public Role() {
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
