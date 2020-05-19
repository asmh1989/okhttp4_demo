package com.my.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = findViewById(R.id.text);

        new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Request request =
                                        new Request.Builder().url("https://www.github.com").build();

                                try (Response response = client.newCall(request).execute()) {
                                    final String res = response.body().string();
                                    runOnUiThread(
                                            new Runnable() {
                                                @Override
                                                public void run() {
                                                    text.setText(res.substring(0, res.length()/100));
                                                }
                                            });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .start();
    }
}
