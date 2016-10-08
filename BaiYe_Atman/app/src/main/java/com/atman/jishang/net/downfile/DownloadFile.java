package com.atman.jishang.net.downfile;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.atman.jishang.ui.base.BaiYeBaseActivity;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.net.model.ConfigModel;
import com.choicepicture_library.tools.FileUtils;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/5 11:54
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DownloadFile extends AsyncTask<String, Void, String> {
    private Context context;
    private ImageView iv_one;
    private ImageView iv_two;

    public DownloadFile(Context context, ImageView iv_one, ImageView iv_two){
        this.context = context;
        this.iv_one = iv_one;
        this.iv_two = iv_two;
    }

    public void down(String _urlStr){

        String newFilename = "config.text";
        String path = FileUtils.SDPATH_FILE + newFilename;
        File file = new File(path);
        //如果目标文件已经存在，则删除。产生覆盖旧文件的效果
        if(!file.exists()) {
            LogUtils.e("file.delete():"+file.delete());
        }
        try {
            // 构造URL
            URL url = new URL(_urlStr);
            // 打开连接
            URLConnection con = url.openConnection();
            //获得文件的长度
            int contentLength = con.getContentLength();
            System.out.println("长度 :"+contentLength);
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(newFilename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String reStr = "";
        String newFilename = "config.txt";
        String path = FileUtils.SDPATH_FILE + newFilename;
        String _urlStr = params[0];
        LogUtils.e(">>>>:"+path);
        File file = new File(path);
        LogUtils.e("file.delete():"+file.delete());
        //如果目标文件不存在，则下载
//        if(!file.exists()) {
//            LogUtils.e("file.delete():"+file.delete());
//        } else {
//            reStr = FileUtils.readSDFile(newFilename);
//        }
        try {
            FileUtils.createSDFileDir("");
            // 构造URL
            URL url = new URL(_urlStr);
            // 打开连接
            URLConnection con = url.openConnection();
            //获得文件的长度
            int contentLength = con.getContentLength();
            System.out.println("长度 :"+contentLength);
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(path);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
            reStr = FileUtils.readSDFile(newFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ConfigModel mConfigModel = new Gson().fromJson(result, ConfigModel.class);
        if (mConfigModel != null) {
            ((BaiYeBaseActivity) context).setBitmapToImageView
                    (iv_one, mConfigModel.getLaunch_ad_imgback_url(), 0);
            ((BaiYeBaseActivity) context).setBitmapToImageView
                    (iv_two, mConfigModel.getLaunch_ad_imgcontent_url(), 0);
            BaiYeBaseApplication.mExperienceUS = mConfigModel.getKDemoAccount();
            BaiYeBaseApplication.mExperiencePW = mConfigModel.getKDemoAccountPW();
            BaiYeBaseApplication.mSMS_URL = mConfigModel.getKAndroidUrl();
            BaiYeBaseApplication.mWEB_URL = mConfigModel.getLaunch_ad_web_url();
        }
    }
}
