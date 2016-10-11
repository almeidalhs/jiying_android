package com.atman.jishang.net.model;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class ModuleListModel {
    private int id;
    private int moduleStatus;

    public ModuleListModel(int moduleStatus, int id) {
        this.moduleStatus = moduleStatus;
        this.id = id;
    }
}
