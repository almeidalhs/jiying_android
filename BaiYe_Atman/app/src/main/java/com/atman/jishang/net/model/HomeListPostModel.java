package com.atman.jishang.net.model;

/**
 * 描述 上传改变后的顺序
 * 作者 tangbingliang
 * 时间 16/4/21 13:28
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class HomeListPostModel {
    private int consoleId;
    private int ucSort;
    private int ucConsoleSort;
    private int ucState;

    public HomeListPostModel(){}

    public HomeListPostModel(int consoleId,int ucSort,int ucConsoleSort,int ucState){
        this.consoleId = consoleId;
        this.ucSort = ucSort;
        this.ucConsoleSort = ucConsoleSort;
        this.ucState = ucState;
    }

    public int getConsoleId() {
        return consoleId;
    }

    public int getUcConsoleSort() {
        return ucConsoleSort;
    }

    public int getUcSort() {
        return ucSort;
    }

    public int getUcState() {
        return ucState;
    }

    @Override
    public String toString() {
        return "HomeListPostModel{" +
                "consoleId=" + consoleId +
                ", ucSort=" + ucSort +
                ", ucConsoleSort=" + ucConsoleSort +
                ", ucState=" + ucState +
                '}';
    }
}
