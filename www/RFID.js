

var exec = require('cordova/exec');

var RFID = {
    read:function(successCallback, errorCallback) {
    	if (successCallback == null) {
            successCallback = function () {
            };
        }
        
    	if (errorCallback == null) {
            errorCallback = function () {
            };
        }

        if (typeof errorCallback != "function") {
            console.log("RFID.read failure: failure parameter not a function");
            return;
        }

        if (typeof successCallback != "function") {
            console.log("RFID.read failure: success callback parameter must be a function");
            return;
        }
        exec(successCallback, errorCallback, "RFID", "read", []);
    },
    	
    wirte:function(successCallback, errorCallback) {
    	if (successCallback == null) {
            successCallback = function () {
            };
        }
        
    	if (errorCallback == null) {
            errorCallback = function () {
            };
        }

        if (typeof errorCallback != "function") {
            console.log("RFID.wirte failure: failure parameter not a function");
            return;
        }

        if (typeof successCallback != "function") {
            console.log("RFID.wirte failure: success callback parameter must be a function");
            return;
        }
        exec(successCallback, errorCallback, "RFID", "wirte", []);
    }
};

module.exports = RFID;
