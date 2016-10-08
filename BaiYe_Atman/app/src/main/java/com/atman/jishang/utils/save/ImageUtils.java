/**
 * Copyright (C) 2014 xiaobudian Inc.
 */
package com.atman.jishang.utils.save;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.atman.jishang.ui.base.BaiYeBaseApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 图片工具类
 */
public class ImageUtils {
    public final static int PHOTOGRAPH_CODE = 11;
    public final static int SELECT_PHOTO_CODE = 12;
    public final static int REPHOTOGRAGH_CODE = 13;

    public static int getMiniSize(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        return Math.min(options.outHeight, options.outWidth);
    }

    public static boolean isSquare(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        return options.outHeight == options.outWidth;
    }

    public static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    //压缩好比例大小后再进行质量压缩
    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //图片是不是正方形
    public static boolean isSquare(Uri imageUri) {
        ContentResolver resolver = BaiYeBaseApplication.getApp().getContentResolver();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(resolver.openInputStream(imageUri), null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return options.outHeight == options.outWidth;
    }

    //保存图片文件
    public static String saveToFile(String fileFolderStr, boolean isDir, Bitmap croppedImage) throws FileNotFoundException, IOException {
        File jpgFile;
        if (isDir) {
            File fileFolder = new File(fileFolderStr);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
            String filename = format.format(date) + ".jpg";
            if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                FileUtils.getInst().mkdir(fileFolder);
            }
            jpgFile = new File(fileFolder, filename);
        } else {
            jpgFile = new File(fileFolderStr);
            if (!jpgFile.getParentFile().exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                FileUtils.getInst().mkdir(jpgFile.getParentFile());
            }
        }
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流

        croppedImage.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        IOUtil.closeStream(outputStream);
        return jpgFile.getPath();
    }

    //保存图片文件
    public static String replaceToFile(String fileFolderStr, boolean isDir, Bitmap croppedImage, String filename) throws FileNotFoundException, IOException {
        File jpgFile;
        if (isDir) {
            File fileFolder = new File(fileFolderStr);
            if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                FileUtils.getInst().mkdir(fileFolder);
            }
            jpgFile = new File(fileFolder, filename);
        } else {
            jpgFile = new File(fileFolderStr);
            if (!jpgFile.getParentFile().exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                FileUtils.getInst().mkdir(jpgFile.getParentFile());
            }
        }
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流

        croppedImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        IOUtil.closeStream(outputStream);
        return jpgFile.getPath();
    }



    //从文件中读取Bitmap
    public static Bitmap decodeBitmapWithOrientation(String pathName, int width, int height) {
        return decodeBitmapWithSize(pathName, width, height, false, 0);
    }

    public static Bitmap decodeBitmapWithOrientationMax(String pathName, int width, int height,int degrees) {
        return decodeBitmapWithSize(pathName, width, height, true,degrees);
    }

    private static Bitmap decodeBitmapWithSize(String pathName, int width, int height,
                                               boolean useBigger,int degrees) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inInputShareable = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFile(pathName, options);

        int decodeWidth = width, decodeHeight = height;
//        final int degrees = getImageDegrees(pathName);
//        final int degrees = 90;
        Log.e(">>>>>", degrees+"");
        if (degrees == 90 || degrees == 270) {
            decodeWidth = height;
            decodeHeight = width;
        }

        if (useBigger) {
            options.inSampleSize = (int) Math.min(((float) options.outWidth / decodeWidth),
                    ((float) options.outHeight / decodeHeight));
        } else {
            options.inSampleSize = (int) Math.max(((float) options.outWidth / decodeWidth),
                    ((float) options.outHeight / decodeHeight));
        }

        options.inJustDecodeBounds = false;
        Bitmap sourceBm = BitmapFactory.decodeFile(pathName, options);
        return imageWithFixedRotation(sourceBm, degrees);
    }

    public static Bitmap rotatingImage(Bitmap bm, int degrees) {
        if (bm == null || bm.isRecycled())
            return null;

        if (degrees == 0)
            return bm;

        final Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap result = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),bm.getHeight(), matrix, true);
        if (result != bm)
            bm.recycle();
        return result;

    }

    public static int getImageDegrees(String pathName) {
        int degrees = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(pathName);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            Log.e(">>>>>", "orientation:"+orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degrees = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degrees = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degrees = 270;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return degrees;
    }

    public static Bitmap imageWithFixedRotation(Bitmap bm, int degrees) {
        if (bm == null || bm.isRecycled())
            return null;

        if (degrees == 0)
            return bm;

        final Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap result = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        if (result != bm)
            bm.recycle();
        return result;

    }


    public static float getImageRadio(ContentResolver resolver, Uri fileUri) {
        InputStream inputStream = null;
        try {
            inputStream = resolver.openInputStream(fileUri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            int initWidth = options.outWidth;
            int initHeight = options.outHeight;
            float rate = initHeight > initWidth ? (float) initHeight / (float) initWidth
                    : (float) initWidth / (float) initHeight;
            return rate;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
            IOUtil.closeStream(inputStream);
        }

    }

    public static Bitmap byteToBitmap(byte[] imgByte) {
        InputStream input = null;
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        input = new ByteArrayInputStream(imgByte);
        SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(
                input, null, options));
        bitmap = (Bitmap) softRef.get();
        if (imgByte != null) {
            imgByte = null;
        }

        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }


    public static Map<String, Album> findGalleries(Context mContext, List<String> paths, long babyId) {
        paths.clear();
        paths.add(FileUtils.getInst().getSystemPhotoPath());
        String[] projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_ADDED };//FIXME 拍照时间为新增照片时间
        Cursor cursor = mContext.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,//指定所要查询的字段
                MediaStore.Images.Media.SIZE + ">?",//查询条件
                new String[] { "100000" }, //查询条件中问号对应的值
                MediaStore.Images.Media.DATE_ADDED + " desc");

        cursor.moveToFirst();
        //文件夹照片
        Map<String, Album> galleries = new HashMap<String, Album>();
        while (cursor.moveToNext()) {
            String data = cursor.getString(1);
            if (data.lastIndexOf("/") < 1) {
                continue;
            }
            String sub = data.substring(0, data.lastIndexOf("/"));
            if (!galleries.keySet().contains(sub)) {
                String name = sub.substring(sub.lastIndexOf("/") + 1, sub.length());
                if (!paths.contains(sub)) {
                    paths.add(sub);
                }
                galleries.put(sub, new Album(name, sub, new ArrayList<PhotoItem>()));
            }

            galleries.get(sub).getPhotos().add(new PhotoItem(data, (long) (cursor.getInt(2)) * 1000));
        }
        //系统相机照片
        ArrayList<PhotoItem> sysPhotos = FileUtils.getInst().findPicsInDir(
                FileUtils.getInst().getSystemPhotoPath());
        if (!sysPhotos.isEmpty()) {
            galleries.put(FileUtils.getInst().getSystemPhotoPath(), new Album("胶卷相册", FileUtils
                    .getInst().getSystemPhotoPath(), sysPhotos));
        } else {
            galleries.remove(FileUtils.getInst().getSystemPhotoPath());
            paths.remove(FileUtils.getInst().getSystemPhotoPath());
        }
        return galleries;
    }



    //异步加载图片
    public static interface LoadImageCallback {
        public void callback(Bitmap result);
    }

    public static void asyncLoadImage(Context context, Uri imageUri, LoadImageCallback callback) {
        new LoadImageUriTask(context, imageUri, callback).execute();
    }

    private static class LoadImageUriTask extends AsyncTask<Void, Void, Bitmap> {
        private final Uri         imageUri;
        private final Context     context;
        private LoadImageCallback callback;

        public LoadImageUriTask(Context context, Uri imageUri, LoadImageCallback callback) {
            this.imageUri = imageUri;
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                InputStream inputStream;
                if (imageUri.getScheme().startsWith("http")
                        || imageUri.getScheme().startsWith("https")) {
                    inputStream = new URL(imageUri.toString()).openStream();
                } else {
                    inputStream = context.getContentResolver().openInputStream(imageUri);
                }
                return BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            callback.callback(result);
        }
    }

    //异步加载缩略图
    public static void asyncLoadSmallImage(Context context, Uri imageUri, LoadImageCallback callback) {
        new LoadSmallPicTask(context, imageUri, callback).execute();
    }

    private static class LoadSmallPicTask extends AsyncTask<Void, Void, Bitmap> {

        private final Uri         imageUri;
        private final Context     context;
        private LoadImageCallback callback;

        public LoadSmallPicTask(Context context, Uri imageUri, LoadImageCallback callback) {
            this.imageUri = imageUri;
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            return getResizedBitmap(context, imageUri, 300, 300);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            callback.callback(result);
        }

    }

    //得到指定大小的Bitmap对象
    public static Bitmap getResizedBitmap(Context context, Uri imageUri, int width, int height) {
        InputStream inputStream = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            inputStream = context.getContentResolver().openInputStream(imageUri);
            BitmapFactory.decodeStream(inputStream, null, options);

            options.outWidth = width;
            options.outHeight = height;
            options.inJustDecodeBounds = false;
            IOUtil.closeStream(inputStream);
            inputStream = context.getContentResolver().openInputStream(imageUri);
            return BitmapFactory.decodeStream(inputStream, null, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(inputStream);
        }
        return null;
    }


}
