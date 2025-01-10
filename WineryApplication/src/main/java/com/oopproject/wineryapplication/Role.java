package com.oopproject.wineryapplication;

import java.util.HashMap;

public class Role {
    private String roleName;
    private HashMap<String, Runnable> roleActions;

    public Role(String roleName) {
        this.roleName = roleName;
        this.roleActions = new HashMap<>();
    }

    public String getRoleName() {
        return roleName;
    }

    public HashMap<String, Runnable> getRoleActions() {
        return roleActions;
    }

    public void addRoleAction(String actionName, Runnable handler) {
        roleActions.put(actionName, handler);
    }
}
