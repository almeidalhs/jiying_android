package com.atman.jishang.interfaces;

import android.content.Context;
import android.widget.Toast;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/5 09:54
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MyUMShareListenner implements UMShareListener {

    private Context context;
    public MyUMShareListenner (Context context){
        this.context = context;
    }

    @Override
    public void onResult(SHARE_MEDIA platform) {
//        Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
//        Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA platform) {
//        Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
    }
}
