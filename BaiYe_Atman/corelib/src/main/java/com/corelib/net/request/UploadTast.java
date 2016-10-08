package com.corelib.net.request;

import android.os.AsyncTask;

import com.corelib.iimp.IDownloadAndUploadListener;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by GodXj on 2015/12/18.
 */
public class UploadTast extends AsyncTask<Object, Integer, Object> {

    private IDownloadAndUploadListener.IUploadListener listener = null;


    /**
     * 默认可以使用DefaultLoadListner.DefaultDownloadListener
     *
     * @param l
     */
    public UploadTast(IDownloadAndUploadListener.IUploadListener l) {
        this.listener = l;
    }

    /**
     * 执行
     *
     * @param params params[0]请求URL params[1]上传文件名(Map存放) params[2]请求参数（Map存放）
     */
    @Override
    protected Object doInBackground(Object... params) {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String BOUNDARY = "---------------------------yonglibaorenwohua";
        String result = null;
        try {
            if (params.length < 2) {
                throw new LoadException(LoadException.PARAMERROR);
            }
            String urlStr = params[0].toString();
            Map<String, String> fileMap = (Map<String, String>) params[1];
            if (fileMap == null) {
                throw new LoadException(LoadException.PARAMERROR);
            }
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(DownTask.TIMEOUT);
            conn.setReadTimeout(DownTask.TIMEOUT);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6) RenWoHua");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            out = new DataOutputStream(conn.getOutputStream());

//            // text
//            uploadText(BOUNDARY, textMap, out);
            // file
            uploadFile(BOUNDARY, fileMap, out);

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuffer strBuf = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    strBuf.append(line).append("\n");
                }
                result = strBuf.toString();
                reader.close();
                reader = null;
            } else {
                return new LoadException(LoadException.HTTPCODEERROR + ":" + conn.getResponseCode());
            }

        } catch (Exception e) {
            return e;
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return result;
    }

    private void uploadFile(String BOUNDARY, Map<String, String> fileMap, OutputStream out) throws IOException {
        if (fileMap != null) {
            Iterator iter = fileMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String inputName = (String) entry.getKey();
                String inputValue = (String) entry.getValue();
                if (inputValue == null) {
                    continue;
                }
                File file = new File(inputValue);
                String filename = file.getName();
                // String contentType = new
                // MimetypesFileTypeMap().getContentType(file);
                String contentType = "";
                if (filename.endsWith(".png")) {
                    contentType = "image/png";
                }
                if (filename.endsWith(".jpeg") || filename.endsWith(".jpg")) {
                    contentType = "image/jpeg";
                }
                if (contentType == null || contentType.equals("")) {
                    contentType = "application/octet-stream";
                }

                StringBuffer strBuf = new StringBuffer();
                strBuf.append("\r\n--").append(BOUNDARY).append("\r\n");
                strBuf.append("Content-Disposition: form-data; name=\""
                        + inputName + "\"; filename=\"" + filename
                        + "\"\r\n");
                strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
                out.write(strBuf.toString().getBytes());
                DataInputStream in = new DataInputStream(
                        new FileInputStream(file));
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
            }
        }
    }

    private void uploadText(String BOUNDARY, Map<String, String> textMap, OutputStream out) throws IOException {
        if (textMap != null) {
            StringBuffer strBuf = new StringBuffer();
            Iterator iter = textMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String inputName = (String) entry.getKey();
                String inputValue = (String) entry.getValue();
                if (inputValue == null) {
                    continue;
                }
                strBuf.append("\r\n--").append(BOUNDARY).append("\r\n");
                strBuf.append("Content-Disposition: form-data; name=\""
                        + inputName + "\"\r\n\r\n");
                strBuf.append(inputValue);
            }
            out.write(strBuf.toString().getBytes());
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (listener != null) {
            if (result instanceof Throwable) {
                listener.onUploadError((Throwable) result);
            } else {
                listener.onEndUpload(result == null ? "" : result.toString());
            }
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (listener != null) {
            listener.onUploadCancelled();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null) {
            listener.onStartUpload();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (listener != null && values.length > 0) {
            listener.onUploading(values[0]);
        }
    }
}
