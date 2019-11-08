# **ScanCode**

## 扫描二维码条形码控件

[![](https://jitpack.io/v/MZCretin/ScanCode.svg)](https://jitpack.io/#MZCretin/ScanCode)

### 系列

在工作之余，打算将一些常用的逻辑页面，模块，功能点做成library库，这样当有相似需求的时候，可以做到插拔式开发！现在系列中有以下内容

+ [App内部自动更新-AutoUpdateProject](https://github.com/MZCretin/AutoUpdateProject)
+ [选择城市-CitySelect](https://github.com/MZCretin/CitySelect)
+ [扫描二维码条形码控件-ScanCode](https://github.com/MZCretin/CitySeScanCode)

### 优势

+ **使用zBar进行二维码/条形码识别**
+ **可打开/关闭闪光灯**
+ **可打开相册扫描**
+ **使用简单，几行代码，即可拥有整个扫描识别功能**

### demo下载

[Demo下载](https://raw.githubusercontent.com/MZCretin/WebViewUtils/master/pic/demo.apk)

扫描二维码下载：

<img src="./pic/erweima.png"/>

### 效果预览

<div style="background:#e3e3e3; color:#FFF" align=center ><img width="250" height="500" src="./pic/111.jpg"/><img width="250" height="500" src="./pic/222.jpg"/></div>

### 使用方式

+ Step1 Add it in your root build.gradle at the end of repositories.

  ```java
  allprojects {
  	repositories {
  		...
  		maven { url 'https://jitpack.io' }
  	}
  }
  ```

+ Step2 Add the dependency.

  ```java
  dependencies {
  		implementation 'com.github.MZCretin:ScanCode:latest_version'
  }
  ```

+ Step3 Open scan activity wherever you want.
  ```java
  ScanConfig config = new ScanConfig()
                  .setShowFlashlight(true)//是否需要打开闪光灯
                  .setShowGalary(true)//是否需要打开相册
                  .setNeedRing(true);//是否需要提示音
  //ScanConfig 也可以不配置 默认都是打开
  CaptureActivity.launch(this, config);
  ```

+ Step4 Receive the result on the 'onActivityResult' in activity or fragment.
  ```java
  if (requestCode == CaptureActivity.REQUEST_CODE_SCAN) {
      // 扫描二维码回传
      if (resultCode == RESULT_OK) {
          if (data != null) {
              //获取扫描结果
              Bundle bundle = data.getExtras();
              String result = bundle.getString(CaptureActivity.EXTRA_SCAN_RESULT);
              tv_scanResult.setText("扫描结果：" + result);
          }
      }
  }
  ```

+ Step5 Add follow codes in app build.gradle inner android label. Otherwise you can not load .so files.
  ```java
  sourceSets {
      main {
          jniLibs.srcDirs = ['libs']
      }
  }
  ```