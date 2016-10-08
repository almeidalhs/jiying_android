package com.atman.jishang.net;

import com.atman.jishang.utils.GsonUtils;

import org.json.JSONObject;

/**
 * 网络数据基类
 *
 * @author 尹东东
 * @version 0.0.1
 * @date 2016/3/4 15:42
 */
public class BaseResult {
    private int code;
    private String message;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (getCode() == 1000) {
            return true;
        }
        return false;
    }

    public static BaseResult parse(String src){
        BaseResult result = new BaseResult();
        try{
            JSONObject jsonObject = new JSONObject(src);
            result.setCode(jsonObject.getInt("code"));
            result.setMessage(jsonObject.getString("message"));
            result.setData(jsonObject.getString("data"));
        }catch (Exception e){}
        return result;
    }

    public <T> T parseObject(Class<T> clazz) {
        return GsonUtils.parse(getData(), clazz);
    }
}
