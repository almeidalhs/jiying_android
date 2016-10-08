package com.corelib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.corelib.base.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.security.PublicKey;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageInfoUtil {
    private static String TAG = PackageInfoUtil.class.getSimpleName();

    public static boolean deleteApkFromDiskByUri(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            LogUtils.w("filePath is empty or null.");
            return false;
        }
        File file = new File(URI.create(filePath));
        if (file.exists()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    public static PackageInfo getPackageInfoByName(Context context, String pkgName) {
        PackageInfo packageInfo = null;
        if (context != null && pkgName != null) {
            try {
                //getPackageInfo第二个参数传0表示只获取该包名下的ApplicationInfo信息，
                //传-1表示获取AndroidManifest中配置的所有内容
                packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
            } catch (Exception ignore) {
            }
        }
        return packageInfo;
    }


    public static PublicKey getApkSignInfo(String apkFilePath){
        byte[] readBuffer = new byte[8192];
        java.security.cert.Certificate[] certs = null;
        try{
            JarFile jarFile = new JarFile(apkFilePath);
            Enumeration entries = jarFile.entries();
            while(entries.hasMoreElements()){
                JarEntry je = (JarEntry)entries.nextElement();
                if(je.isDirectory()){
                    continue;
                }
                if(je.getName().startsWith("META-INF/")){
                    continue;
                }
                java.security.cert.Certificate[] localCerts = loadCertificates(jarFile,je,readBuffer);
                //  System.out.println("File " + apkFilePath + " entry " + je.getName()+ ": certs=" + certs + " ("+ (certs != null ? certs.length : 0) + ")");
                if (certs == null) {
                    certs = localCerts;
                }else{
                    for(int i=0; i<certs.length; i++){
                        boolean found = false;
                        for (int j = 0; j < localCerts.length; j++) {
                            if (certs[i] != null && certs[i].equals(localCerts[j])) {
                                found = true;
                                break;
                            }
                        }
                        if (!found || certs.length != localCerts.length) {
                            jarFile.close();
                            return null;
                        }
                    }
                }
            }
            jarFile.close();
            //Log.i("wind cert=",certs[0].toString());
            return certs[0].getPublicKey();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static java.security.cert.Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while(is.read(readBuffer,0,readBuffer.length)!=-1) {
            }
            is.close();
            return (java.security.cert.Certificate[])(je!=null?je.getCertificates():null);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception reading " + je.getName() + " in " + jarFile.getName() + ": " + e);
        }
        return null;
    }


    public static byte[] getSign(Context context) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm
                .getInstalledPackages(PackageManager.GET_SIGNATURES);
        Iterator<PackageInfo> iter = apps.iterator();

        while (iter.hasNext()) {
            PackageInfo info = iter.next();
            String packageName = info.packageName;
            //按包名 取签名
            if (packageName.equals("com.yonglibao.renwohua")) {
                return info.signatures[0].toByteArray();
            }
        }
        return null;
    }

    public static String getVersionName() {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            return packageManager.getPackageInfo(BaseApplication.getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            //FIXME
            return "4.0.2";
        }
    }

    public static String getChannel() {
        InputStream in = null;
        String channel = "";
        try {
            in = BaseApplication.getContext().getAssets().open("channel",
                    AssetManager.ACCESS_BUFFER);
            Reader r = new InputStreamReader(in, "utf-8");
            StringBuilder out = new StringBuilder();
            char[] buf = new char[1024];
            int c;
            while ((c = r.read(buf)) >= 0) {
                out.append(buf, 0, c);
            }
            channel = out.toString();
            in.close();
        } catch (IOException e) {
            in = null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return channel;
    }


}
