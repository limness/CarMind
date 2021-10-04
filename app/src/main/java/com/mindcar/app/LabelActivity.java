package com.mindcar.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.mindcar.app.ui.login.LoginActivity;

import java.io.FileInputStream;
import java.io.IOException;

public class LabelActivity extends AppCompatActivity {
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        startTime();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startTime() {
        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onFinish() {
                checkSaveOfSequrity();
            }
        }.start();
    }

    public void checkSaveOfSequrity() {
        FileInputStream fin = null;
        FileInputStream finTwo = null;

        try {
            fin = openFileInput("save_sequrity.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String login = new String (bytes);

            if(!login.isEmpty()) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        } catch(IOException ex) {
            Intent intent = new Intent(getApplicationContext(), SequrityActivity.class);
            startActivity(intent);
        } finally {
            try {
                if(fin!=null) {
                    fin.close();
                }
            }
            catch(IOException ex) {
            }
        }
    }
}
