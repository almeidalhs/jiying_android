package com.corelib.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.corelib.base.BaseApplication;

/**
 * Created by loyee on 15-12-10.
 */
public class DeviceUtils {
    public static String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) BaseApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        if(deviceid==null) {
            //emulator nullpointException
            return "";
        }
        return deviceid;
    }

}
