package com.atman.jishang.net.upload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.UpLoadHeadImgModel;
import com.atman.jishang.utils.save.FileUtils;
import com.atman.jishang.utils.save.ImageUtils;
import com.choicepicture_library.tools.Bimp;
import com.corelib.base.BaseAppCompatActivity;
import com.corelib.net.request.DefaultLoadListener;
import com.corelib.net.request.UploadTast;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/3 14:03
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UpLoadPicture extends AsyncTask<Uri, Void, String> {

    private Context context;
    private int mWidth;
    private int mHeight;
    private String mShopBanner;
    private uploadListener Lis;
    private Boolean isMul = false;
    private String mUrl;

    public UpLoadPicture(){}

    public UpLoadPicture(Context context, String mUrl, uploadListener Lis){
        this.context = context;
        this.Lis = Lis;
        this.mUrl = mUrl;
    }

    public UpLoadPicture(Context context, String mUrl, boolean isMul, uploadListener Lis){
        this.context = context;
        this.Lis = Lis;
        this.mUrl = mUrl;
        this.isMul = isMul;
    }

    @Override
    protected void onPreExecute() {
        ((BaseAppCompatActivity)context).showLoading();
    }

    @Override
    protected String doInBackground(Uri... params) {
        try {
            Uri uri = params[0];
            LogUtils.e("uri.toString():"+uri.toString());
            Bitmap bitmap = null;
            if (isMul) {
                bitmap = Bimp.revitionImageSize(uri.toString());
            } else {
                try {
                    InputStream in = context.getContentResolver().openInputStream(uri);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(in, null, options);
                    in.close();
                    int i = 0;
                    while (true) {
                        if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                            in = context.getContentResolver().openInputStream(uri);
                            options.inSampleSize = (int) Math.pow(2.0D, i);
                            options.inJustDecodeBounds = false;
                            bitmap = BitmapFactory.decodeStream(in, null, options);
                            break;
                        }
                        i += 1;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] data = stream.toByteArray();

            Bitmap croppedImage;

            //获得图片大小
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            mWidth = options.outWidth;
            mHeight = options.outHeight;
            Rect r = new Rect(0, 0, options.outWidth, options.outHeight);
            croppedImage = decodeRegionCrop(data, r);
            String imagePath = ImageUtils.saveToFile(FileUtils.getInst().getSystemPhotoPath(),
                    true, ImageUtils.comp(croppedImage));
            croppedImage.recycle();
            return imagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (!TextUtils.isEmpty(result)) {
            submitPhoto(result);
        } else {
            Lis.fail("照片上传失败，稍后再试");
        }
    }

    private void submitPhoto(String path) {
        UploadTast task = new UploadTast(new DefaultLoadListener.DefaultUploadListener() {

            @Override
            public void onUploadError(Throwable e) {
                super.onUploadError(e);
                e.printStackTrace();
                LogUtils.e("=======onUploadError=" + e.getMessage());
                ((BaseAppCompatActivity)context).cancelLoading();
                Lis.fail(e.getMessage().toString());
            }

            @Override
            public void onEndUpload(String result) {
                super.onEndUpload(result);
                LogUtils.e("=======onEndload=" + result);
                Gson gson = new Gson();
                try {
                    UpLoadHeadImgModel mUpLoadHeadImgModel = gson.fromJson(result, UpLoadHeadImgModel.class);
                    if (mUpLoadHeadImgModel.getResult().equals("1") && mUpLoadHeadImgModel.getBody().size() > 0) {
                        mShopBanner = mUpLoadHeadImgModel.getBody().get(0).getUrl();
                        Lis.success(mShopBanner);
                    } else {
                        Lis.fail("图片上传失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((BaseAppCompatActivity)context).cancelLoading();
            }

            @Override
            public void onUploading(int position) {
                super.onUploading(position);
            }
        });
        Object[] params = new Object[2];
        params[0] = Urls.RWH_HOST_UP + mUrl;
        LogUtils.e("URL:"+params[0]);
        Map<String, String> files = new HashMap<>();
        files.put("files", path);
        params[1] = files;
        task.execute(params);
    }

    private Bitmap decodeRegionCrop(byte[] data, Rect rect) {
        InputStream is = null;
        System.gc();
        Bitmap croppedImage = null;
        try {
            is = new ByteArrayInputStream(data);
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
            croppedImage = decoder.decodeRegion(rect, new BitmapFactory.Options());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
//            IOUtil.closeStream(is);
        }
        Bitmap rotatedImage = Bitmap.createBitmap(croppedImage, 0, 0, mWidth, mHeight, null, true);
        if (rotatedImage != croppedImage && croppedImage != null) {
            croppedImage.recycle();
        }
        return rotatedImage;
    }

    public interface uploadListener {

        void success(String imageUrl);

        void fail(String failInfo);
    }
}
