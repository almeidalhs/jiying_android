package com.corelib.util;


import com.corelib.base.BaseApplication;
import com.seawind.corelib.R;

public class YLBMsgTextUtils {

	/**
	 * 标志性错误
	 * 
	 * @param flag
	 *            标志例如异常
	 * @return
	 */
	public static String getMsg(int flag) {
		switch (flag) {
		case YLBConstacts.Error.NETWORKERROR:
			return getStringById(R.string.networkError);
		case YLBConstacts.Error.CLIENTERROR:
			return getStringById(R.string.clientError);
		case YLBConstacts.Error.SERVERERROR:
			return getStringById(R.string.serverError);
		case YLBConstacts.Error.AUTHFAILUREERROR:
			return getStringById(R.string.authFailureError);
		case YLBConstacts.Error.PARSEERROR:
			return getStringById(R.string.parseError);
		case YLBConstacts.Error.NOCONNECTIONERROR:
			return getStringById(R.string.noConnectionError);
		case YLBConstacts.Error.TIMEOUTERROR:
			return getStringById(R.string.timeoutError);
		default:
			return "";
		}
	}

	public static String getStringById(int resId) {
		return BaseApplication.getContext().getString(resId);
	}

}
