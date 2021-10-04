package com.mindcar.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mindcar.app.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {
    ImageView logo;
    ImageView comeIn;
    EditText telephone;
    EditText username;
    EditText password;
    TextView autoriz;
    ProgressBar loading;

    private static String URL_REGIST = "http://kostya2l.beget.tech/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        logo = findViewById(R.id.logo);
        autoriz = findViewById(R.id.autorization);
        comeIn = findViewById(R.id.button);
        username = findViewById(R.id.username);
        telephone = findViewById(R.id.telephone);
        password = findViewById(R.id.password);
        loading = findViewById(R.id.loading);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");
        telephone.setTypeface(custom_font);
        username.setTypeface(custom_font);
        password.setTypeface(custom_font);

        autoriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        comeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String tel = telephone.getText().toString().trim();

                if(user.isEmpty() || pass.isEmpty() || tel.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Вы не ввели пароль/логин/телефон", Toast.LENGTH_SHORT).show();
                } else {
                    regCome();
                }
            }
        });
    }

    public void regCome() {
        loading.setVisibility(View.VISIBLE);
        telephone.setVisibility(View.GONE);
        logo.setVisibility(View.GONE);
        autoriz.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        username.setVisibility(View.GONE);
        comeIn.setVisibility(View.GONE);

        final String usernam = this.username.getText().toString().trim();
        final String passwor = this.password.getText().toString().trim();
        final String telephon = this.telephone.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Регистрация прошла успешна!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else if(success.equals("2")) {
                        loading.setVisibility(View.GONE);
                        logo.setVisibility(View.VISIBLE);
                        telephone.setVisibility(View.VISIBLE);
                        autoriz.setVisibility(View.VISIBLE);
                        password.setVisibility(View.VISIBLE);
                        username.setVisibility(View.VISIBLE);
                        comeIn.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), "Данный логин уже используется!", Toast.LENGTH_SHORT).show();
                    } else {
                        loading.setVisibility(View.GONE);
                        logo.setVisibility(View.VISIBLE);
                        telephone.setVisibility(View.VISIBLE);
                        autoriz.setVisibility(View.VISIBLE);
                        password.setVisibility(View.VISIBLE);
                        username.setVisibility(View.VISIBLE);
                        comeIn.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), "Введите корректный телефон или логин или пароль", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();

                    loading.setVisibility(View.GONE);
                    logo.setVisibility(View.VISIBLE);
                    telephone.setVisibility(View.VISIBLE);
                    autoriz.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    comeIn.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Во время регистрации произошла ошибка:\n" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Register is not success! " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put ("username", usernam);
                params.put ("password", passwor);
                params.put ("telephone", telephon);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
}
