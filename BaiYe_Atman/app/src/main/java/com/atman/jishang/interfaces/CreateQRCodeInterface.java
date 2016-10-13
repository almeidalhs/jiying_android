package com.atman.jishang.interfaces;

/**
 * Created by tangbingliang on 16/10/13.
 */

public interface CreateQRCodeInterface {
    int QRCodeTypeCallService = 0;
    int QRCodeTypeAll = 1;
    int QRCodeTypeCompanyCulture = 2;
    int QRCodeTypeWifi = 3;
    int QRCodeTypeMenu = 4;

    interface SingleType {
        int All = 0;
        int Single = 1;
    }
}
