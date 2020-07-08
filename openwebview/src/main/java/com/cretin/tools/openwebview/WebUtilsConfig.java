package com.cretin.tools.openwebview;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.ColorRes;

/**
 * @date: on 2019-11-07
 * @author: a112233
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WebUtilsConfig implements Parcelable {
    //返回的文案
    private String backText = "~";
    //是否显示返回两个字
    private boolean showBackText = true;
    //标题栏背景 可以传drawable 也可以是颜色
    private int titleBackgroundRes = -1;
    private int titleBackgroundColor = -1;
    //返回按钮的图标
    private int backBtnRes = -1;
    //更多的图标按钮
    private int moreBtnRes = -1;
    //是否显示更多按钮
    private boolean showMoreBtn = true;
    //标题下面有一条白线 是为了分割标题和内容的 不喜欢可以去掉
    private boolean showTitleLine = false;
    //标题下面有一条线的颜色
    private int titleLineColor = -1;
    //是否显示标题 如果你的页面是全屏的可以去掉标题栏
    private boolean showTitleView = true;
    //标题颜色
    private int titleTextColor = -1;
    //返回按钮文字颜色
    private int backTextColor = -1;
    //状态栏文字颜色是否是深色
    private boolean isStateBarTextColorDark;

    public boolean isStateBarTextColorDark() {
        return isStateBarTextColorDark;
    }

    public WebUtilsConfig setStateBarTextColorDark(boolean stateBarTextColorDark) {
        isStateBarTextColorDark = stateBarTextColorDark;
        return this;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public WebUtilsConfig setTitleTextColor(@ColorRes int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    public int getBackTextColor() {
        return backTextColor;
    }

    public WebUtilsConfig setBackTextColor(@ColorRes int backTextColor) {
        this.backTextColor = backTextColor;
        return this;
    }

    public String getBackText() {
        return backText;
    }

    public WebUtilsConfig setBackText(String backText) {
        this.backText = backText;
        return this;
    }

    public boolean isShowBackText() {
        return showBackText;
    }

    public WebUtilsConfig setShowBackText(boolean showBackText) {
        this.showBackText = showBackText;
        return this;
    }

    public int getTitleBackgroundRes() {
        return titleBackgroundRes;
    }

    public WebUtilsConfig setTitleBackgroundRes(int titleBackgroundRes) {
        this.titleBackgroundRes = titleBackgroundRes;
        return this;
    }

    public int getTitleBackgroundColor() {
        return titleBackgroundColor;
    }

    public WebUtilsConfig setTitleBackgroundColor(@ColorRes int titleBackgroundColor) {
        this.titleBackgroundColor = titleBackgroundColor;
        return this;
    }

    public int getBackBtnRes() {
        return backBtnRes;
    }

    public WebUtilsConfig setBackBtnRes(int backBtnRes) {
        this.backBtnRes = backBtnRes;
        return this;
    }

    public int getMoreBtnRes() {
        return moreBtnRes;
    }

    public WebUtilsConfig setMoreBtnRes(int moreBtnRes) {
        this.moreBtnRes = moreBtnRes;
        return this;
    }

    public boolean isShowMoreBtn() {
        return showMoreBtn;
    }

    public WebUtilsConfig setShowMoreBtn(boolean showMoreBtn) {
        this.showMoreBtn = showMoreBtn;
        return this;
    }

    public boolean isShowTitleLine() {
        return showTitleLine;
    }

    public WebUtilsConfig setShowTitleLine(boolean showTitleLine) {
        this.showTitleLine = showTitleLine;
        return this;
    }

    public int getTitleLineColor() {
        return titleLineColor;
    }

    public WebUtilsConfig setTitleLineColor(int titleLineColor) {
        this.titleLineColor = titleLineColor;
        return this;
    }

    public boolean isShowTitleView() {
        return showTitleView;
    }

    public WebUtilsConfig setShowTitleView(boolean showTitleView) {
        this.showTitleView = showTitleView;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backText);
        dest.writeByte(this.showBackText ? (byte) 1 : (byte) 0);
        dest.writeInt(this.titleBackgroundRes);
        dest.writeInt(this.titleBackgroundColor);
        dest.writeInt(this.backBtnRes);
        dest.writeInt(this.moreBtnRes);
        dest.writeByte(this.showMoreBtn ? (byte) 1 : (byte) 0);
        dest.writeByte(this.showTitleLine ? (byte) 1 : (byte) 0);
        dest.writeInt(this.titleLineColor);
        dest.writeByte(this.showTitleView ? (byte) 1 : (byte) 0);
        dest.writeInt(this.titleTextColor);
        dest.writeInt(this.backTextColor);
        dest.writeByte(this.isStateBarTextColorDark ? (byte) 1 : (byte) 0);
    }

    public WebUtilsConfig() {
    }

    protected WebUtilsConfig(Parcel in) {
        this.backText = in.readString();
        this.showBackText = in.readByte() != 0;
        this.titleBackgroundRes = in.readInt();
        this.titleBackgroundColor = in.readInt();
        this.backBtnRes = in.readInt();
        this.moreBtnRes = in.readInt();
        this.showMoreBtn = in.readByte() != 0;
        this.showTitleLine = in.readByte() != 0;
        this.titleLineColor = in.readInt();
        this.showTitleView = in.readByte() != 0;
        this.titleTextColor = in.readInt();
        this.backTextColor = in.readInt();
        this.isStateBarTextColorDark = in.readByte() != 0;
    }

    public static final Creator<WebUtilsConfig> CREATOR = new Creator<WebUtilsConfig>() {
        @Override
        public WebUtilsConfig createFromParcel(Parcel source) {
            return new WebUtilsConfig(source);
        }

        @Override
        public WebUtilsConfig[] newArray(int size) {
            return new WebUtilsConfig[size];
        }
    };
}
