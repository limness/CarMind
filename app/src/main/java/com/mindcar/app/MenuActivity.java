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
import java.util.Map;

public class MenuActivity extends AppCompatActivity {

    private ImageView search;
    private ImageView openMenu;
    private ImageView closeMenu;
    private ImageView menu;
    private ImageView exitFromApp;
    private ImageView telephone;
    private ImageView logoPanel;
    private ImageView avaLoginPanel;
    private ImageView helpInBuy;
    private ImageView autodetails;
    private ImageView circleMont;
    private ImageView remont;
    private ImageView menu_main;
    private ImageView carWash;

    private TextView city;
    private TextView paydown;
    private TextView loginPanel;
    private TextView aboutService;
    private TextView becomePartner;
    private TextView balance;
    private TextView idPanel;
    private ImageView pointOne;
    private ImageView pointTwo;
    private ImageView boxBottom;
    private ImageView avaBoxWhiteBottom;

    private TextView nameBoxBottom;
    private TextView timeBoxBottom;
    private TextView telephoneBoxBottom;
    private TextView adminScore;

    private EditText searching;

    private int statusOfPanel = 0;
    private int balance_int = 0;
    private int admin = -1;
    private int player_id = -1;

    public String nose;
    private String cityText;
    private static String URL_REGIST = "http://kostya2l.beget.tech/repairs.php";
    private static String URL_NEWS = "http://kostya2l.beget.tech/news.php";

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

    private void checkOfPanel(int old_panel) {
        if (old_panel == -1) {
            if (statusOfPanel == 0) {
                pointOne.setImageResource(R.drawable.points_dark);
            } else if (statusOfPanel == 1) {
                pointTwo.setImageResource(R.drawable.points_dark);
            }
        } else {
            if (old_panel == 1) {
                remont.setVisibility(View.GONE);
                carWash.setVisibility(View.GONE);
                circleMont.setVisibility(View.GONE);
                helpInBuy.setVisibility(View.VISIBLE);
                autodetails.setVisibility(View.VISIBLE);

                pointOne.setImageResource(R.drawable.points);
                pointTwo.setImageResource(R.drawable.points_dark);
            } else if (old_panel == 2) {
                helpInBuy.setVisibility(View.GONE);
                autodetails.setVisibility(View.GONE);
                remont.setVisibility(View.VISIBLE);
                carWash.setVisibility(View.VISIBLE);
                circleMont.setVisibility(View.VISIBLE);

                pointOne.setImageResource(R.drawable.points_dark);
                pointTwo.setImageResource(R.drawable.points);
            }
        }
    }

    private void checkButtons() {
        helpInBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BuyHelpActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        carWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("typebusiness", "4");
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        autodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("typebusiness", "1");
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        remont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("typebusiness", "2");
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        circleMont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("typebusiness", "3");
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nose = getIntent().getStringExtra("name");
        cityText = getIntent().getStringExtra("city");
        balance_int = Integer.parseInt(getIntent().getStringExtra("balance"));
        admin = Integer.parseInt(getIntent().getStringExtra("admin"));

        player_id = Integer.parseInt(getIntent().getStringExtra("id"));
        idPanel = findViewById(R.id.idPanel);
        idPanel.setText("ID: " + player_id);

        menu_main = findViewById(R.id.menu);
        city = findViewById(R.id.labelNews);
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
        adminScore = findViewById(R.id.adminScore);
        carWash = findViewById(R.id.carWash);
        helpInBuy = findViewById(R.id.helpInBuy);
        autodetails = findViewById(R.id.autodetails);
        circleMont = findViewById(R.id.circleMont);
        remont = findViewById(R.id.remont);
        pointOne = findViewById(R.id.pointOne);
        pointTwo = findViewById(R.id.pointTwo);
        boxBottom = findViewById(R.id.boxBottom);
        avaBoxWhiteBottom = findViewById(R.id.avaBoxWhiteBottom);
        nameBoxBottom = findViewById(R.id.nameBoxBottom);
        timeBoxBottom = findViewById(R.id.timeBoxBottom);
        telephoneBoxBottom = findViewById(R.id.telephoneBoxBottom);

        city.setText(cityText);

        // Set the buttons by status on main frame
        checkOfPanel(-1);

        // Handlers
        checkButtons();
        readNews();

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

        adminScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CashBackActivity.class);
                intent.putExtra("name", nose);
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        pointOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusOfPanel != 1) {
                    statusOfPanel = 1;
                    checkOfPanel(2);
                }
            }
        });

        pointTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusOfPanel != 2) {
                    statusOfPanel = 2;
                    checkOfPanel(1);
                }
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

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
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
                    File f = new File(System.getProperty("user.dir"), "content_login.txt");
                    fos.close();
                    f.delete();

                    FileOutputStream fospass = openFileOutput("content_password.txt", MODE_PRIVATE);
                    File fpass = new File(System.getProperty("user.dir"), "content_password.txt");
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
    private void loadTable () {
        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Toast.makeText(getApplicationContext(), "Авторизация прошла успешна!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }
                    } else if (success.equals("2")) {
                        Toast.makeText(getApplicationContext(), "Логин или пароль неверен" + success, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Во время авторизации произошла ошибка:\n" + ex.toString(), Toast.LENGTH_SHORT).show();
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
                return super.getParams();
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }


    private void readNews() {
        String URL = "NONE";

        StringRequest request = new StringRequest(Request.Method.POST, URL_NEWS, new Response.Listener<String>() {
            JSONArray result;

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String name = object.getString("name").trim();
                            String telephone = object.getString("telephone").trim();
                            String timeofwork = object.getString("timeofwork").trim();

                            boxBottom.setVisibility(View.VISIBLE);
                            avaBoxWhiteBottom.setVisibility(View.VISIBLE);
                            nameBoxBottom.setVisibility(View.VISIBLE);
                            timeBoxBottom.setVisibility(View.VISIBLE);
                            telephoneBoxBottom.setVisibility(View.VISIBLE);

                            nameBoxBottom.setText(name);
                            timeBoxBottom.setText(telephone);
                            telephoneBoxBottom.setText(timeofwork);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Во время загрузки данных произошла ошибка\n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Во время загрузки данных произошла ошибка\n" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
}
