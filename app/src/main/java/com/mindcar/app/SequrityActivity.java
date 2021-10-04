package com.mindcar.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindcar.app.ui.login.LoginActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class SequrityActivity extends AppCompatActivity {
    private ImageView goNext;
    private TextView goPolicy;
    private TextView seqofservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequrity);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        goNext = findViewById(R.id.goNext);
        goPolicy = findViewById(R.id.goPolicy);
        seqofservice = findViewById(R.id.seqofservice);

        seqofservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyActivity.class);
                intent.putExtra("id", "2");
                startActivity(intent);
            }
        });

        goPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyActivity.class);
                intent.putExtra("id", "1");
                startActivity(intent);
            }
        });

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSequrity();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void saveSequrity() {
        FileOutputStream fos = null;
        FileOutputStream fosTwo = null;

        try {
            String login = "Nope";
            fos = openFileOutput("save_sequrity.txt", MODE_PRIVATE);
            fos.write(login.getBytes());
        } catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch(IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
