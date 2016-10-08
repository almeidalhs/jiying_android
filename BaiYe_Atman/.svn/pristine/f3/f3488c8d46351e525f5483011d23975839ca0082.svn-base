package com.corelib.iimp;

/**
 * Created by GodXj on 2015/12/18.
 */
public interface IDownloadAndUploadListener {

    public interface IDownloadListener {
        /**
         * 开始下载
         */
        public void onStartDownLoad();

        /**
         * 下载进度
         *
         * @param position
         */
        public void onDownLoading(int position);

        /**
         * 下载结束 返回保存的地址
         */
        public void onEndDownLoad(String savePath);

        /**
         * 取消下载
         */
        public void onDownLoadCancelled();

        /**
         * 下载失败
         */
        public void onDownLoadError(Throwable e);
    }

    public interface IUploadListener {
        /**
         * 开始上传
         */
        public void onStartUpload();

        /**
         * 上传进度
         *
         * @param position
         */
        public void onUploading(int position);

        /**
         * 取消上传
         */
        public void onUploadCancelled();

        /**
         * 完成上传
         */
        public void onEndUpload(String result);

        /**
         * 上传失败
         *
         * @param e
         */
        public void onUploadError(Throwable e);
    }
}
