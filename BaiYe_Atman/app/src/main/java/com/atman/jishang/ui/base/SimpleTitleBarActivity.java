package com.atman.jishang.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;

import butterknife.Bind;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/11 11:25
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class SimpleTitleBarActivity extends BaiYeBaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.iv_right_ok)
    ImageView ivRightOk;
    @Bind(R.id.nav_right_ll)
    LinearLayout navRightLl;
    @Bind(R.id.ll_back)
    LinearLayout llBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_simpletootlbar_view);

    }

    private boolean isIMOpen() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();//isOpen若返回true，则表示输入法打开
    }

    @Override
    public void setContentView(int layoutResID) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvRight = (TextView) findViewById(R.id.tv_right);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivRightOk = (ImageView) findViewById(R.id.iv_right_ok);
        tvTitle.setText(getTitle() + "");
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layoutResID, null);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        llRoot.addView(v);
        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isIMOpen()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                }
            }
        });
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getTitle() != null) {
            tvTitle = (TextView) findViewById(R.id.tv_title);
            setToolbarTitle(getTitle().toString());
        }
    }

    public LinearLayout getLlBack() {
        return llBack;
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public ImageView getIvRightOk() {
        if (navRightLl==null) {
            navRightLl =  (LinearLayout) findViewById(R.id.nav_right_ll);
        }
        navRightLl.setVisibility(View.VISIBLE);
        return ivRightOk;
    }

    /**
     * 右边按钮是否显示
     * txt为文字
     */
    protected LinearLayout showRightLl() {
        if (navRightLl==null) {
            navRightLl =  (LinearLayout) findViewById(R.id.nav_right_ll);
        }
        navRightLl.setVisibility(View.VISIBLE);
        return navRightLl;
    }

    /**
     * 右边按钮是否显示
     * txt为文字
     */
    protected TextView showRightTV(String txt) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(txt + "");
        return tvRight;
    }

    protected TextView showRightTV(int n) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getResources().getString(n));
        return tvRight;
    }

    /**
     * 影藏头部
     */
    protected void hideTitleBar() {
        if (rlTitle != null) {
            rlTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 显示头部
     */
    protected void showTitleBar() {
        if (rlTitle != null) {
            rlTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置顶部标题
     *
     * @param title
     */
    protected void setToolbarTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title + "");
        }
    }

    protected void setToolbarTitle(int title) {
        if (tvTitle != null) {
            tvTitle.setText(getResources().getString(title));
        }
    }

    /**
     * 设置顶部标题(由于没有返回文字，废弃该方法)
     *
     * @param backText
     */
    protected void setToolbarBackViewText(String backText) {

    }

}
