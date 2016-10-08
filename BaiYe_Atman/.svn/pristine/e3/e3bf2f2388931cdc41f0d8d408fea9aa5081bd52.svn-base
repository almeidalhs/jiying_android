package com.corelib.net.request;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Environment;

import com.corelib.iimp.IDownloadAndUploadListener;

/**
 * Created by GodXj on 2015/12/18.
 */
public class DownTask extends AsyncTask<Object, Integer, Object> {

    private static final String SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/RWHDown";
    public static final int TIMEOUT = 30 * 1000;
    private IDownloadAndUploadListener.IDownloadListener listener = null;

    /**
     * 默认可以使用DefaultLoadListner.DefaultDownloadListener
     *
     * @param l
     */
    public DownTask(IDownloadAndUploadListener.IDownloadListener l) {
        this.listener = l;
    }

    // 开始
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (null != listener) {
            listener.onStartDownLoad();
        }
    }

    /**
     * 执行
     *
     * @param arg0 arg0[0]请求URL arg0[1]保存的名字 arg0[2]请求参数
     */
    @Override
    protected Object doInBackground(Object... arg0) {
        InputStream is = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        String filePath = null;
        try {
            if (arg0 == null || arg0.length < 2) {
                throw new LoadException(LoadException.PARAMERROR);
            }
            String url = arg0[0].toString();
            String fileName = arg0[1].toString();
            if (url == null || "".equals(url) || null == fileName || "".equals(fileName)) {
                throw new LoadException(LoadException.PARAMERROR);
            }
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                long length = entity.getContentLength();
                is = entity.getContent();
                if (null != is) {
                    File folderFile = new File(SAVEPATH);
                    if (folderFile.exists()) {
                        folderFile.delete();
                    }
                    folderFile.mkdir();
                    filePath = SAVEPATH + File.separator + fileName;
                    File zipFile = new File(filePath);
                    zipFile.createNewFile();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    fos = new FileOutputStream(zipFile);
                    bos = new BufferedOutputStream(fos);
                    int read;
                    long count = 0;
                    int precent = 0;
                    byte[] buffer = new byte[1024];
                    while ((read = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, read);
                        count += read;
                        precent = (int) (((double) count / length) * 100);
                        publishProgress(precent);
                    }
                }
            } else {
                throw new LoadException(LoadException.HTTPCODEERROR + ":" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            return e;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return filePath;
    }

    // 更新进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (null != listener && values.length > 0) {
            listener.onDownLoading(values[0]);
        }
    }

    // 结束
    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (null != listener) {
            if (result instanceof Throwable) {
                listener.onDownLoadError((Throwable) result);
            } else {
                listener.onEndDownLoad(result.toString());
            }
        }
    }

    // 取消
    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (null != listener) {
            listener.onDownLoadCancelled();
        }
    }
}
