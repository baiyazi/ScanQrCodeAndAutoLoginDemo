package com.weizu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private String url;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        url = getIntent().getExtras().getString("url");
        if(url != null){
            String name = "123";
            String userId = "qwe";
            url += "&username="+name + "&userid="+userId;
        }
        Log.d("TAG", "url : " + url);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });

        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Toast.makeText(LoginActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void requestLogin() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            Message msg = new Message();
            @Override
            public void onFailure(Call call, IOException e) {
                msg.obj="请求失败";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        String result = response.body().string();
                        if(result.equals("SUCCESS LOGIN!")){
                            msg.obj = "授权成功！";
                        }
                    }
                }else{
                    msg.obj = "出现了未知错误: " + response.code();
                }
                handler.sendMessage(msg);
            }
        });
    }
}