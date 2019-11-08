package com.cretin.tools.openwebview.utils;

import android.animation.Animator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cretin.tools.openwebview.R;


public class TopPopWindow extends PopupWindow {
    private Activity context;
    private View mView;
    private TextView tv01, tv02, tv03;
    private AnimUtil animUtil;
    private static final long DURATION = 200;
    private static final float START_ALPHA = 0.9f;
    private static final float END_ALPHA = 1f;
    private float bgAlpha = 1f;
    private boolean bright = false;
    private OnTopItemClickListener topItemClickListener;

    private WindowManager.LayoutParams lp;

    public TopPopWindow(Activity activity, OnTopItemClickListener onItemClickListener) {
        topItemClickListener = onItemClickListener;
        context = activity;
        mView = LayoutInflater.from(activity).inflate(R.layout.layout_top_add, null);

        animUtil = new AnimUtil();
        tv01 = mView.findViewById(R.id.tv_01);
        tv02 = mView.findViewById(R.id.tv_02);
        tv03 = mView.findViewById(R.id.tv_03);
        //设置点击监听
        tv01.setOnClickListener(clickListener);
        tv02.setOnClickListener(clickListener);
        tv03.setOnClickListener(clickListener);
        setContentView(mView);
        //设置宽度
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置高度
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置显示隐藏动画
//            setAnimationStyle(R.style.AnimTools);
        //设置背景透明
//            setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        lp = context.getWindow().getAttributes();
        getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    dismiss();
                }
            }
        });
        // 设置pop关闭监听，用于改变背景透明度
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });
        //设置默认获取焦点
        setFocusable(true);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (topItemClickListener != null) {
                topItemClickListener.onItemClick(v);
            }
            dismiss();
        }
    };

    public void showAsDropDownView(View view) {
        showAsDropDown(view, 0, WebCommonUtils.dp2px(20, context) * -1);
        toggleBright();
    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
                context.getWindow().setAttributes(lp);
            }
        });
        animUtil.startAnimator();
    }


    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        context.getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public TopPopWindow(Activity activity, View view) {
        context = activity;
        mView = view;
        animUtil = new AnimUtil();
        setContentView(mView);
        //设置宽度
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置高度
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置显示隐藏动画
//            setAnimationStyle(R.style.AnimTools);
        //设置背景透明
//            setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        lp = context.getWindow().getAttributes();
        getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    dismiss();
                }
            }
        });
        // 设置pop关闭监听，用于改变背景透明度
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });
        //设置默认获取焦点
        setFocusable(true);
    }

    public interface OnTopItemClickListener {
        void onItemClick(View v);
    }
}
