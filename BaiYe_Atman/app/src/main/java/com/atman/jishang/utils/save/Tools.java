package com.atman.jishang.utils.save;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/5 10:16
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class Tools {

    /**
     * 实现文本复制功能
     * add by wangqianzhou
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        Toast.makeText(context, "复制成功" ,Toast.LENGTH_SHORT).show();
    }
}
