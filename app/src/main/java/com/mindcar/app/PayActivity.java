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

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {
    private ImageView search;
    private ImageView openMenu;
    private ImageView closeMenu;
    private ImageView menu;
    private ImageView exitFromApp;
    private ImageView telephone;
    private ImageView logoPanel;
    private ImageView avaLoginPanel;
    private ImageView menu_main;
    private ImageView goAccept;
    private ImageView fieldTelephone;
    private ImageView fieldprice;

    private EditText searching;
    private EditText telephoneField;
    private EditText priceField;

    private TextView loginPanel;
    private TextView paydown;
    private TextView aboutService;
    private TextView becomePartner;
    private TextView balanceUI;
    private TextView textTile;
    private TextView acceptPlease;
    private TextView adminScore;
    private TextView idPanel;

    public String nose;
    private int balance = 0;
    private int admin = -1;
    private int player_id = -1;

    private static String URL_REGIST = "http://kostya2l.beget.tech/minus_balance.php";
    private static String URL_REGIST_INPUT = "http://kostya2l.beget.tech/score.php";

    private TextView changeCity;

    private void showMenu() {
        menu.setVisibility(View.VISIBLE);
        closeMenu.setVisibility(View.VISIBLE);
        logoPanel.setVisibility(View.VISIBLE);
        avaLoginPanel.setVisibility(View.VISIBLE);
        loginPanel.setVisibility(View.VISIBLE);
        exitFromApp.setVisibility(View.VISIBLE);
        telephone.setVisibility(View.VISIBLE);
        paydown.setVisibility(View.VISIBLE);
        aboutService.setVisibility(View.VISIBLE);
        becomePartner.setVisibility(View.VISIBLE);
        balanceUI.setVisibility(View.VISIBLE);
        idPanel.setVisibility(View.VISIBLE);
        changeCity.setVisibility(View.VISIBLE);

        if(admin == 1) {
            adminScore.setVisibility(View.VISIBLE);
        }
    }

    private void hideMenu() {
        menu.setVisibility(View.GONE);
        closeMenu.setVisibility(View.GONE);
        logoPanel.setVisibility(View.GONE);
        avaLoginPanel.setVisibility(View.GONE);
        loginPanel.setVisibility(View.GONE);
        exitFromApp.setVisibility(View.GONE);
        telephone.setVisibility(View.GONE);
        paydown.setVisibility(View.GONE);
        aboutService.setVisibility(View.GONE);
        becomePartner.setVisibility(View.GONE);
        balanceUI.setVisibility(View.GONE);
        idPanel.setVisibility(View.GONE);
        changeCity.setVisibility(View.GONE);

        if(admin == 1) {
            adminScore.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nose = getIntent().getStringExtra("name");
        balance = Integer.parseInt(getIntent().getStringExtra("balance"));
        admin = Integer.parseInt(getIntent().getStringExtra("admin"));

        player_id = Integer.parseInt(getIntent().getStringExtra("id"));
        idPanel = findViewById(R.id.idPanel);
        idPanel.setText("ID: " + player_id);

        menu_main = findViewById(R.id.menu);
        searching = findViewById(R.id.searching);
        search = findViewById(R.id.search);
        openMenu = findViewById(R.id.burgerMenu);
        closeMenu = findViewById(R.id.closePanel);
        menu = findViewById(R.id.panelSearch);
        loginPanel = findViewById(R.id.loginPanel);
        changeCity = findViewById(R.id.changeCity);
        adminScore = findViewById(R.id.adminScore);
        paydown = findViewById(R.id.paydown);
        aboutService = findViewById(R.id.aboutService);
        becomePartner = findViewById(R.id.becomePartner);
        exitFromApp = findViewById(R.id.exitFromApp);
        telephone = findViewById(R.id.telephone);
        logoPanel = findViewById(R.id.logoPanel);
        avaLoginPanel = findViewById(R.id.avaLoginPanel);
        balanceUI = findViewById(R.id.balanceUI);
        goAccept = findViewById(R.id.goAccept);
        textTile = findViewById(R.id.textTile);
        fieldTelephone = findViewById(R.id.fieldTelephone);
        telephoneField = findViewById(R.id.telephoneField);
        fieldprice = findViewById(R.id.fieldprice);
        priceField = findViewById(R.id.priceField);
        acceptPlease = findViewById(R.id.acceptPlease);

        goAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telephone = telephoneField.getText().toString().trim();
                String price = priceField.getText().toString().trim();

                if(telephone.isEmpty() || price.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Успешно Net!" + Integer.parseInt(priceField.getText().toString()), Toast.LENGTH_SHORT).show();
                    if(Integer.parseInt(price) > 500) {
                        if(Integer.parseInt(price) > balance) {
                            Toast.makeText(getApplicationContext(), "Сумма вывода привышает суммы баланса", Toast.LENGTH_SHORT).show();
                        } else {
                            goAccept.setVisibility(View.GONE);
                            textTile.setVisibility(View.GONE);
                            fieldTelephone.setVisibility(View.GONE);
                            telephoneField.setVisibility(View.GONE);
                            fieldprice.setVisibility(View.GONE);
                            priceField.setVisibility(View.GONE);
                            acceptPlease.setVisibility(View.VISIBLE);

                            balance -= Integer.parseInt(price);
                            balanceUI.setText("Баланс: " + balance + " Р");

                            updateBalance(String.valueOf(balance));
                            inputFile(price, telephone);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Минимальная сумма вывода: 500 рублей", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        menu_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balanceUI", getIntent().getStringExtra("balanceUI"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        becomePartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartamentActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balanceUI", getIntent().getStringExtra("balanceUI"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        aboutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutServiceActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balanceUI", getIntent().getStringExtra("balanceUI"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balanceUI", getIntent().getStringExtra("balanceUI"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        adminScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CashBackActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balanceUI", getIntent().getStringExtra("balanceUI"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        loginPanel.setTypeface(custom_font);
        loginPanel.setText(nose);

        balanceUI.setTypeface(custom_font);
        balanceUI.setText("Баланс: " + balance + " Р");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searching.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "В данный момент функция недоступна!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        exitFromApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput("content_login.txt", MODE_PRIVATE);
                    File f = new File(System.getProperty("user.dir"),"content_login.txt");
                    fos.close();
                    f.delete();

                    FileOutputStream fospass = openFileOutput("content_password.txt", MODE_PRIVATE);
                    File fpass = new File(System.getProperty("user.dir"),"content_password.txt");
                    fospass.close();
                    fpass.delete();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Во время выхода произошла ошибка:\n" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });

        closeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMenu();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void updateBalance(final String balance) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        //Toast.makeText(getApplicationContext(), "Успешно!", Toast.LENGTH_SHORT).show();
                    } else if(success.equals("2")) {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка #101" + "\n" + "Сообщите об этом разработчику приложения", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Произошла ошибка #102" + "\n" + "Сообщите об этом разработчику приложения " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Произошла ошибка #103" + "\n" + "Сообщите об этом разработчику приложения", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put ("username", nose);
                params.put ("balance", balance);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
    public void inputFile(final String balance, final String telephone) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST_INPUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        Toast.makeText(getApplicationContext(), "Успешная запись", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Произошла ошибка #104" + "\n" + "Сообщите об этом разработчику приложения " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Произошла ошибка #105" + "\n" + "Сообщите об этом разработчику приложения", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put ("username", nose);
                params.put ("price", balance);
                params.put ("telephone", telephone);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
}
