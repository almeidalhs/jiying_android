package com.atman.jishang.net.model;

import java.io.Serializable;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class ModuleListModel implements Serializable {
    private int id;
    private int moduleStatus;
    private String name;

    public ModuleListModel(String name, int id, int moduleStatus) {
        this.name = name;
        this.moduleStatus = moduleStatus;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(int moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
