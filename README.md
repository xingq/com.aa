com.jeaw.cordova.RFID
这个插件实现了RFID功能。支持Android。

添加插件的方式：
cordova plugin add https://github.com/suikelei/RFID.git

使用方式：
navigator.RFID.read(function(text){
    alert(text);
},function(error){
    alert(error);
});

navigator.RFID.read(successFn, failFn)的参数讲解：

successFn 读取RFID成功的回调函数

failFn 读取RFID失败的回调函数
