package com.atman.jishang.widget.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.atman.jishang.R;
import com.atman.jishang.net.model.WeiXinPrePayModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.corelib.util.LogUtils;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/23 10:57
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class PayDialog {

    // 商户PID
    public static final String PARTNER = "";
    // 商户收款账号
    public static final String SELLER = "";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;
    private Context context;
    private String orderId;

    public PayDialog(){}

    public PayDialog(Context context, String orderId){
        this.context = context;
        this.orderId = orderId;
    }

    public void show(){
        //布局文件转换为view对象
        LayoutInflater inflaterDl = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout)inflaterDl.inflate(R.layout.dialog_pay, null );

        //对话框
        final Dialog dialog = new AlertDialog.Builder(context, R.style.dialog_pay).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);

        layout.findViewById(R.id.dialog_pay_cancel_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        layout.findViewById(R.id.dialog_pay_root_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        layout.findViewById(R.id.dialog_pay_zhifubao_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        layout.findViewById(R.id.dialog_pay_weixinzhifu_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWXAPI api = WXAPIFactory.createWXAPI(context, null);
                if (api.isWXAppInstalled()) {
                    dialog.dismiss();
                    ((SimpleTitleBarActivity) context).getDataManager().prePayWeiXin(orderId, WeiXinPrePayModel.class, true);
                } else {
                    ((SimpleTitleBarActivity) context).showToast("您还没有安装微信客户端");
                }

            }
        });
    }

    public void weixinPay(WeiXinPrePayModel mWeiXinPrePayModel){
        LogUtils.e(">>>>>");
        BaiYeBaseApplication.appId = mWeiXinPrePayModel.getBody().getAppid();
        IWXAPI api = WXAPIFactory.createWXAPI(context, mWeiXinPrePayModel.getBody().getAppid());
        PayReq payRequest = new PayReq();
        payRequest.appId = mWeiXinPrePayModel.getBody().getAppid();//应用ID
        payRequest.partnerId = mWeiXinPrePayModel.getBody().getMch_id();//商户号
        payRequest.prepayId = mWeiXinPrePayModel.getBody().getPrepayId();//预支付交易会话ID
        payRequest.packageValue = "WX_ADD_ID:"+mWeiXinPrePayModel.getBody().getAppid();//扩展字段
        payRequest.nonceStr = mWeiXinPrePayModel.getBody().getNonce_str();//随机字符串
        payRequest.timeStamp = String.valueOf(genTimeStamp());//时间戳

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", payRequest.appId));
        signParams.add(new BasicNameValuePair("noncestr", payRequest.nonceStr));
        signParams.add(new BasicNameValuePair("package", payRequest.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", payRequest.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", payRequest.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", payRequest.timeStamp));

        payRequest.sign = genAppSign(signParams);

//        payRequest.sign = mWeiXinPrePayModel.getBody().getSign();//签名
        api.sendReq(payRequest);
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append("JiShangAtman20160627165040888888");

        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign;
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
//    public void pay(View v, final Context context) {
//        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
//            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            ((Activity)context).finish();
//                        }
//                    }).show();
//            return;
//        }
//        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
//        /**
//         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
//         */
//        String sign = sign(orderInfo);
//        try {
//            /**
//             * 仅需对sign 做URL编码
//             */
//            sign = URLEncoder.encode(sign, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        /**
//         * 完整的符合支付宝参数规范的订单信息
//         */
//        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                // 构造PayTask 对象
//                PayTask alipay = new PayTask((SimpleTitleBarActivity)context);
//                // 调用支付接口，获取支付结果
//                String result = alipay.pay(payInfo, true);
//
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";
        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";
        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";
        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";
        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";
        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
        return orderInfo;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
}
