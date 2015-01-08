package com.jeaw.qxjszb.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

import android.util.Log;

import android.hardware.uhf.magic.reader;

public class RFID extends CordovaPlugin{
	
	private static final String LOG_TAG = "RFID";
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("read")) {
        	try {
        		String msg = reader.getUserAreaContent();
        		if(msg.startsWith("error")){
        			if(msg.equals("error01")){
    					msg = "终端设备不能打开标签模块,请重试";
    				}
    				if(msg.equals("error02")){
    					msg = "不能读取EPC编码,请重试";
    				}
    				callbackContext.error(msg);
        		}else{
        			callbackContext.success(msg);
        		}
			} catch (Exception e) {
				Log.d(LOG_TAG, "RFID标签读取异常",e);
				callbackContext.error("RFID标签读取异常,请重试");
			}
        } else if (action.equals("write")){
        	try {
        		String msg = "功能未开发！";
        		callbackContext.success(msg);
			} catch (Exception e) {
				Log.d(LOG_TAG, "RFID标签写入异常！",e);
				callbackContext.error("RFID标签写入异常！");
			}
        }
        else {
            return false;
        }
        return true;
    }
}
