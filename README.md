# **WebViewUtils**

## 一键打开WebView

[![](https://jitpack.io/v/MZCretin/WebViewUtils.svg)](https://jitpack.io/#MZCretin/WebViewUtils)

### 系列

在工作之余，打算将一些常用的逻辑页面，模块，功能点做成library库，这样当有相似需求的时候，可以做到插拔式开发！现在系列中有以下内容

+ [App内部自动更新-AutoUpdateProject](https://github.com/MZCretin/AutoUpdateProject)
+ [选择城市-CitySelect](https://github.com/MZCretin/CitySelect)
+ [扫描二维码条形码控件-ScanCode](https://github.com/MZCretin/ScanCode)
+ [一键打开WebView件-WebViewUtils](https://github.com/MZCretin/WebViewUtils)
+ [简约动态权限申请库-FanPermission](https://github.com/MZCretin/FanPermission)
+ [弹出自定义支付密码输入框-InputPswDemo](https://github.com/MZCretin/InputPswDemo)
+ [安卓常用工具集成-AndroidUtils](https://github.com/MZCretin/AndroidUtilsProject)

### 优势

+ **使用AgentWeb实现WebView封装**
+ **提供通用配置，一键打开WebView**
+ **UI可配置，cookie可配置**

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
  		//androidx 版本
        		implementation 'com.github.MZCretin:WebViewUtils:v1.0.4-x'
            //非androidx 版本
              implementation 'com.github.MZCretin:WebViewUtils:v1.0.3'
  }
  ```

+ Step3 Open webview activity wherever you want.
  ```java
  String url = "http://www.mxnzp.com"
  WebUtilsConfig config =
     new WebUtilsConfig()
             .setTitleBackgroundColor(R.color.colorPrimary)//设置标题栏背景色
             .setBackText("关闭")//设置返回按钮的文案
             .setBackBtnRes(R.mipmap.arrow_left_white)//设置返回按钮的图标
             .setMoreBtnRes(R.mipmap.more_web)//设置更多按钮的图标
             .setShowBackText(true)//设置是否显示返回按钮的文案
             .setShowMoreBtn(true)//设置是否显示更多按钮
             .setShowTitleLine(false)//设置是否显示标题下面的分割线
             .setShowTitleView(true)//设置是否显示标题栏，网页是全屏的时候可以选择隐藏标题栏
             .setTitleBackgroundRes(-1)//设置标题栏背景资源
             .setBackTextColor(-1)//设置返回按钮的文案颜色
             .setTitleTextColor(-1)//设置标题文字颜色
             .setStateBarTextColorDark(false)//设置状态栏文字颜色是否是暗色，如果你设置了标题栏背景颜色为白色，这里需要设置true，否则状态栏看不到文案了
             .setTitleLineColor(R.color.app_title_color);//设置标题栏下面的分割线的颜色
  OpenWebActivity.openWebView(MainActivity.this, url, config);
  ```
