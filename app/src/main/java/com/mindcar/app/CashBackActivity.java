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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CashBackActivity extends AppCompatActivity {
    private ImageView search;
    private ImageView openMenu;
    private ImageView closeMenu;
    private ImageView menu;
    private ImageView exitFromApp;
    private ImageView telephone;
    private ImageView logoPanel;
    private ImageView avaLoginPanel;
    private ImageView menuMain;
    private ImageView buttonAccept;
    private ImageView fieldPrice;
    private ImageView fieldId;

    private TextView paydown;
    private TextView aboutService;
    private TextView becomePartner;
    private TextView balance;
    private TextView loginPanel;
    private TextView adminScore;
    private TextView idPanel;
    private TextView label;
    private TextView acceptedLabel;

    private EditText idField;
    private EditText priceField;
    private EditText searching;

    private int balance_int = 0;
    private int admin = -1;
    private int player_id = -1;

    public String nose;
    private static String URL_REGIST = "http://kostya2l.beget.tech/plus_balance.php";
    private static String URL_REGIST_GET = "http://kostya2l.beget.tech/get_balance.php";

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
        balance.setVisibility(View.VISIBLE);
        idPanel.setVisibility(View.VISIBLE);
        changeCity.setVisibility(View.VISIBLE);

        if (admin == 1) {
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
        balance.setVisibility(View.GONE);
        idPanel.setVisibility(View.GONE);
        changeCity.setVisibility(View.GONE);

        if (admin == 1) {
            adminScore.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_back);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nose = getIntent().getStringExtra("name");
        balance_int = Integer.parseInt(getIntent().getStringExtra("balance"));
        admin = Integer.parseInt(getIntent().getStringExtra("admin"));

        player_id = Integer.parseInt(getIntent().getStringExtra("id"));
        idPanel = findViewById(R.id.idPanel);
        idPanel.setText("ID: " + player_id);

        menuMain = findViewById(R.id.menu);
        changeCity = findViewById(R.id.changeCity);
        searching = findViewById(R.id.searching);
        search = findViewById(R.id.search);
        openMenu = findViewById(R.id.burgerMenu);
        closeMenu = findViewById(R.id.closePanel);
        menu = findViewById(R.id.panelSearch);
        loginPanel = findViewById(R.id.loginPanel);
        paydown = findViewById(R.id.paydown);
        aboutService = findViewById(R.id.aboutService);
        becomePartner = findViewById(R.id.becomePartner);
        exitFromApp = findViewById(R.id.exitFromApp);
        telephone = findViewById(R.id.telephone);
        logoPanel = findViewById(R.id.logoPanel);
        avaLoginPanel = findViewById(R.id.avaLoginPanel);
        balance = findViewById(R.id.balance);
        buttonAccept = findViewById(R.id.buttonAccept);
        idField = findViewById(R.id.idField);
        priceField = findViewById(R.id.priceField);
        fieldPrice = findViewById(R.id.fieldPrice);
        fieldId = findViewById(R.id.fieldId);
        label = findViewById(R.id.label);
        acceptedLabel = findViewById(R.id.acceptedLabel);
        adminScore = findViewById(R.id.adminScore);

        menuMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        becomePartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartamentActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
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
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        paydown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        aboutService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutServiceActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idField.getText().toString().trim();
                String price = priceField.getText().toString().trim();

                if (id.isEmpty() || price.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
                } else {
                    if (player_id == Integer.parseInt(id)) {
                        Toast.makeText(getApplicationContext(), "Укажите другой ID!", Toast.LENGTH_SHORT).show();
                    } else {
                        selectBalance(id);
                    }
                }
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");
        loginPanel.setTypeface(custom_font);
        loginPanel.setText(nose);

        balance.setTypeface(custom_font);
        balance.setText("Баланс: " + balance_int + " Р");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searching.getText().toString().trim().isEmpty()) {
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

    public void updateBalance(final String id, final String price) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1")) {
                        idField.setVisibility(View.GONE);
                        priceField.setVisibility(View.GONE);
                        buttonAccept.setVisibility(View.GONE);
                        fieldPrice.setVisibility(View.GONE);
                        fieldId.setVisibility(View.GONE);
                        label.setVisibility(View.GONE);

                        acceptedLabel.setVisibility(View.VISIBLE);
                    } else if (success.equals("2")) {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка" + "\n" + "Возможно введенный ID неверен!", Toast.LENGTH_SHORT).show();
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
                params.put ("id", id);
                params.put ("price", price);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }

    private void selectBalance(final String id) {
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String balance = object.getString("balance").trim();

                            int procent = Integer.parseInt(priceField.getText().toString().trim()) / 100 * 7;
                            int cost = Integer.parseInt(balance) + procent;
                            updateBalance(id, String.valueOf(cost));
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Произошла ошибка:\n" + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Произошла ошибка: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap <> ();
                params.put ("id", id);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
}
