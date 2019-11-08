package com.cretin.webviewutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.util.LinkifyCompat;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cretin.tools.openwebview.OpenWebActivity;
import com.cretin.tools.openwebview.WebUtilsConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.ed_url);

        findViewById(R.id.tv_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                OpenWebActivity.openWebView(MainActivity.this, url);
            }
        });

        findViewById(R.id.tv_open_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                WebUtilsConfig config =
                        new WebUtilsConfig()
                                .setTitleBackgroundColor(R.color.colorAccent)
                                .setBackText("关闭");
                OpenWebActivity.openWebView(MainActivity.this, url, config);
            }
        });

        TextView tvInfo = findViewById(R.id.tv_info);
        final SpannableString value = SpannableString.valueOf(tvInfo.getText());
        LinkifyCompat.addLinks(value, Linkify.ALL);
        tvInfo.setMovementMethod(LinkMovementMethod.getInstance());
        tvInfo.setText(value);
    }
}
