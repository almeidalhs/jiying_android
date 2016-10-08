package com.atman.jishang.ui.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.BindingEquipmentModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.common.SubmitHelper;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 绑定设备
 * 作者 tangbingliang
 * 时间 16/6/21 11:52
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class BindingEquipmentActivity extends SimpleTitleBarActivity {

    @Bind(R.id.bindingequipment_account_tx)
    TextView bindingequipmentAccountTx;
    @Bind(R.id.bindingequipment_password_et)
    MyCleanEditText bindingequipmentPasswordEt;
    @Bind(R.id.bindingequipment_commit_bt)
    Button bindingequipmentCommitBt;

    private Context mContext = BindingEquipmentActivity.this;
    private String code;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindingequipment);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, String account) {
        Intent intent = new Intent(context, BindingEquipmentActivity.class);
        intent.putExtra("account", account);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        code = getIntent().getStringExtra("account");
        bindingequipmentAccountTx.setText(code);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("绑定设备");
        getIvRightOk().setImageResource(R.mipmap.coupon_details_topright);
        showRightLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddrPopupWindow();
            }
        });
    }

    private void showAddrPopupWindow() {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
//        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择头像</font>"));
        builder.setItems(new String[]{"复制设备标识"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    Tools.copy(code, mContext);
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof BindingEquipmentModel) {
            BindingEquipmentModel mBindingEquipmentModel = (BindingEquipmentModel) response;
            if (mBindingEquipmentModel.getBody().equals("1")) {
                showToast(mBindingEquipmentModel.getBody().getMessage());
                finish();
            } else {
                showWranning(mBindingEquipmentModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.bindingequipment_commit_bt)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bindingequipment_commit_bt:
                if (initParams()) {
                    return;
                }
                getDataManager().bindingAcount(code, SubmitHelper.getMD5(pw), BindingEquipmentModel.class, true);
                break;
        }
    }

    private boolean initParams() {
        pw = bindingequipmentPasswordEt.getText().toString().trim();
        if (pw.isEmpty()) {
            showToast("请输入设备密码");
            return true;
//        } else if (pw.length() < 6) {
//            showToast("密码长度为6-17位");
//            return true;
        }
        return false;
    }

    private void showWranning(String str) {
        YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
        builder.setMessage(str);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
