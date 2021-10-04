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

public class BusinessActivity extends AppCompatActivity {
    private TextView paydown;
    private TextView aboutService;
    private TextView becomePartner;
    private TextView balance;
    private TextView loginPanel;
    private TextView nameBoxUp;
    private TextView timeBoxUp;
    private TextView telephoneBoxUp;
    private TextView nameBoxCenter;
    private TextView timeBoxCenter;
    private TextView telephoneBoxCenter;
    private TextView nameBoxBottom;
    private TextView timeBoxBottom;
    private TextView telephoneBoxBottom;
    private TextView adminScore;
    private TextView idPanel;
    private TextView labelNothing;

    private ImageView search;
    private ImageView openMenu;
    private ImageView closeMenu;
    private ImageView menu;
    private ImageView exitFromApp;
    private ImageView telephone;
    private ImageView logoPanel;
    private ImageView avaLoginPanel;
    private ImageView menu_main;
    private ImageView boxUp;
    private ImageView avaBoxWhiteUp;
    private ImageView boxCenter;
    private ImageView avaBoxWhiteCenter;
    private ImageView boxBottom;
    private ImageView avaBoxWhiteBottom;
    private ImageView goNext;

    private ImageView point;
    private ImageView point2;
    private ImageView point3;
    private ImageView point4;
    private ImageView point5;

    private EditText searching;

    public String nose;
    private String cityText;

    private int balance_int = 0;
    private int admin = -1;
    private int status_panel = 0;
    private int typebusiness = -1;
    private int player_id = -1;

    private static String URL_READ_BUSINESS_AUTODETAILS = "http://kostya2l.beget.tech/repairs.php";
    private static String URL_READ_BUSINESS_REMONT = "http://kostya2l.beget.tech/remont.php";
    private static String URL_READ_BUSINESS_CIRCLE = "http://kostya2l.beget.tech/circle.php";
    private static String URL_READ_BUSINESS_WASHES = "http://kostya2l.beget.tech/washes.php";

    private static String URL_READ_BUSINESS_AUTODETAILS_MYCITY = "http://kostya2l.beget.tech/repairsbycity.php";
    private static String URL_READ_BUSINESS_REMONT_MYCITY = "http://kostya2l.beget.tech/remontbycity.php";
    private static String URL_READ_BUSINESS_CIRCLE_MYCITY = "http://kostya2l.beget.tech/circlebycity.php";
    private static String URL_READ_BUSINESS_WASHES_MYCITY = "http://kostya2l.beget.tech/washesbycity.php";

    private void loadNewFields(int newfield)
    {
        switch (status_panel)
        {
            case 0:
            {
                point.setImageResource(R.drawable.points);
                break;
            }
            case 3:
            {
                point2.setImageResource(R.drawable.points);
                break;
            }
            case 6:
            {
                point3.setImageResource(R.drawable.points);
                break;
            }
            case 9:
            {
                point4.setImageResource(R.drawable.points);
                break;
            }
            case 12:
            {
                point5.setImageResource(R.drawable.points);
                break;
            }
        }
        switch (newfield)
        {
            case 0:
            {
                point.setImageResource(R.drawable.points_dark);
                break;
            }
            case 3:
            {
                point2.setImageResource(R.drawable.points_dark);
                break;
            }
            case 6:
            {
                point3.setImageResource(R.drawable.points_dark);
                break;
            }
            case 9:
            {
                point4.setImageResource(R.drawable.points_dark);
                break;
            }
            case 12:
            {
                point5.setImageResource(R.drawable.points_dark);
                break;
            }
        }
        boxUp.setVisibility(View.GONE);
        avaBoxWhiteUp.setVisibility(View.GONE);
        nameBoxUp.setVisibility(View.GONE);
        timeBoxUp.setVisibility(View.GONE);
        telephoneBoxUp.setVisibility(View.GONE);

        boxCenter.setVisibility(View.GONE);
        avaBoxWhiteCenter.setVisibility(View.GONE);
        nameBoxCenter.setVisibility(View.GONE);
        timeBoxCenter.setVisibility(View.GONE);
        telephoneBoxCenter.setVisibility(View.GONE);

        boxBottom.setVisibility(View.GONE);
        avaBoxWhiteBottom.setVisibility(View.GONE);
        nameBoxBottom.setVisibility(View.GONE);
        timeBoxBottom.setVisibility(View.GONE);
        telephoneBoxBottom.setVisibility(View.GONE);

        status_panel = newfield;
        readBusiness(0);
    }
    private void loadFields()
    {
        boxUp = findViewById(R.id.boxUp);
        avaBoxWhiteUp = findViewById(R.id.avaBoxWhiteUp);
        nameBoxUp = findViewById(R.id.nameBoxUp);
        timeBoxUp = findViewById(R.id.timeBoxUp);
        telephoneBoxUp = findViewById(R.id.telephoneBoxUp);

        boxCenter = findViewById(R.id.boxCenter);
        avaBoxWhiteCenter = findViewById(R.id.avaBoxWhiteCenter);
        nameBoxCenter = findViewById(R.id.nameBoxCenter);
        timeBoxCenter = findViewById(R.id.timeBoxCenter);
        telephoneBoxCenter = findViewById(R.id.telephoneBoxCenter);

        boxBottom = findViewById(R.id.boxBottom);
        avaBoxWhiteBottom = findViewById(R.id.avaBoxWhiteBottom);
        nameBoxBottom = findViewById(R.id.nameBoxBottom);
        timeBoxBottom = findViewById(R.id.timeBoxBottom);
        telephoneBoxBottom = findViewById(R.id.telephoneBoxBottom);
    }

    private TextView changeCity;
    private void showMenu()
    {
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

        if (admin == 1)
        {
            adminScore.setVisibility(View.VISIBLE);
        }
    }
    private void hideMenu()
    {
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

        if (admin == 1)
        {
            adminScore.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        nose = getIntent().getStringExtra("name");
        balance_int = Integer.parseInt(getIntent().getStringExtra("balance"));
        typebusiness = Integer.parseInt(getIntent().getStringExtra("typebusiness"));
        admin = Integer.parseInt(getIntent().getStringExtra("admin"));

        cityText = getIntent().getStringExtra("city");

        player_id = Integer.parseInt(getIntent().getStringExtra("id"));
        idPanel = findViewById(R.id.idPanel);
        idPanel.setText("ID: " + player_id);


        labelNothing = findViewById(R.id.labelNothing);
        labelNothing.setText("Ничего не было найдено по вашему городу (" + cityText + "). Нажмите кнопку 'Продолжить', чтобы посмотреть все доступные сервисы");

        changeCity = findViewById(R.id.changeCity);

        goNext = findViewById(R.id.goNext);

        menu_main = findViewById(R.id.menu);

        searching = findViewById(R.id.searching);
        search = findViewById(R.id.search);
        openMenu = findViewById(R.id.burgerMenu);
        closeMenu = findViewById(R.id.closePanel);
        menu = findViewById(R.id.panelSearch);
        loginPanel = findViewById(R.id.loginPanel);

        adminScore = findViewById(R.id.adminScore);

        paydown = findViewById(R.id.paydown);
        aboutService = findViewById(R.id.aboutService);
        becomePartner = findViewById(R.id.becomePartner);
        exitFromApp = findViewById(R.id.exitFromApp);
        telephone = findViewById(R.id.telephone);
        logoPanel = findViewById(R.id.logoPanel);
        avaLoginPanel = findViewById(R.id.avaLoginPanel);
        balance = findViewById(R.id.balance);

        point = findViewById(R.id.point);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        point4 = findViewById(R.id.point4);
        point5 = findViewById(R.id.point5);

        loadFields();
        readBusiness(0); //Устанавливаем статус 0, значит вначале ищем бизы по городу юзера
        point.setImageResource(R.drawable.points_dark);

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_panel != 0)
                {
                    loadNewFields(0);
                }
            }
        });

        point2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_panel != 3)
                {
                    loadNewFields(3);
                }
            }
        });

        point3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_panel != 6)
                {
                    loadNewFields(6);
                }
            }
        });

        point4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_panel != 9) {
                    loadNewFields(9);
                }
            }
        });

        point5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_panel != 12) {
                    loadNewFields(12);
                }
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

        // Show the info about biz

        avaBoxWhiteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typebusiness == 2) {
                    Intent intent = new Intent(getApplicationContext(), RemontActivity.class);
                    intent.putExtra("name", nose);
                    intent.putExtra("balance", getIntent().getStringExtra("balance"));
                    intent.putExtra("namebusiness", nameBoxUp.getText().toString());
                    intent.putExtra("city", getIntent().getStringExtra("city"));
                    intent.putExtra("admin", getIntent().getStringExtra("admin"));
                    intent.putExtra("id", getIntent().getStringExtra("id"));
                    startActivity(intent);
                }
            }
        });

        avaBoxWhiteCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typebusiness == 2) {
                    Intent intent = new Intent(getApplicationContext(), RemontActivity.class);
                    intent.putExtra("name", nose);
                    intent.putExtra("balance", getIntent().getStringExtra("balance"));
                    intent.putExtra("namebusiness", nameBoxCenter.getText().toString());
                    intent.putExtra("city", getIntent().getStringExtra("city"));
                    intent.putExtra("admin", getIntent().getStringExtra("admin"));
                    intent.putExtra("id", getIntent().getStringExtra("id"));
                    startActivity(intent);
                }
            }
        });

        avaBoxWhiteBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typebusiness == 2) {
                    Intent intent = new Intent(getApplicationContext(), RemontActivity.class);
                    intent.putExtra("name", nose);
                    intent.putExtra("balance", getIntent().getStringExtra("balance"));
                    intent.putExtra("namebusiness", nameBoxBottom.getText().toString());
                    intent.putExtra("admin", getIntent().getStringExtra("admin"));
                    intent.putExtra("city", getIntent().getStringExtra("city"));
                    intent.putExtra("id", getIntent().getStringExtra("id"));
                    startActivity(intent);
                }
            }
        });

        menu_main.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra("balance", getIntent().getStringExtra("balance"));
                intent.putExtra("admin", getIntent().getStringExtra("admin"));
                intent.putExtra("city", getIntent().getStringExtra("city"));
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

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext.setVisibility(View.GONE);
                labelNothing.setVisibility(View.GONE);

                readBusiness(1);
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

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void readBusiness(int status) {
        String URL = "NONE";

        if (status == 0) {
            switch(typebusiness) {
                case 1: {
                    URL = URL_READ_BUSINESS_AUTODETAILS_MYCITY;
                    break;
                } case 2: {
                    URL = URL_READ_BUSINESS_REMONT_MYCITY;
                    break;
                } case 3: {
                    URL = URL_READ_BUSINESS_CIRCLE_MYCITY;
                    break;
                } case 4: {
                    URL = URL_READ_BUSINESS_WASHES_MYCITY;
                    break;
                }
            }
        } else {
            switch(typebusiness) {
                case 1: {
                    URL = URL_READ_BUSINESS_AUTODETAILS;
                    break;
                } case 2: {
                    URL = URL_READ_BUSINESS_REMONT;
                    break;
                } case 3: {
                    URL = URL_READ_BUSINESS_CIRCLE;
                    break;
                } case 4: {
                    URL = URL_READ_BUSINESS_WASHES;
                    break;
                }
            }
        }

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            JSONArray result;

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (jsonArray.length() >= 3) {
                        point.setVisibility(View.VISIBLE);
                        point2.setVisibility(View.VISIBLE);
                    }
                    if (jsonArray.length() >= 6) {
                        point3.setVisibility(View.VISIBLE);
                    }
                    if (jsonArray.length() >= 9) {
                        point4.setVisibility(View.VISIBLE);
                    }
                    if (jsonArray.length() >= 12) {
                        point5.setVisibility(View.VISIBLE);
                    }

                    boolean panel_exist = false;

                    for(int i = status_panel; i < jsonArray.length(); i++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String name = object.getString("name").trim();
                            String telephone = object.getString("telephone").trim();
                            String timeofwork = object.getString("timeofwork").trim();

                            if (i == status_panel) {
                                boxUp.setVisibility(View.VISIBLE);
                                avaBoxWhiteUp.setVisibility(View.VISIBLE);
                                nameBoxUp.setVisibility(View.VISIBLE);
                                timeBoxUp.setVisibility(View.VISIBLE);
                                telephoneBoxUp.setVisibility(View.VISIBLE);

                                nameBoxUp.setText(name);
                                timeBoxUp.setText(telephone);
                                telephoneBoxUp.setText(timeofwork);
                            } else if (i == status_panel + 1) {
                                boxCenter.setVisibility(View.VISIBLE);
                                avaBoxWhiteCenter.setVisibility(View.VISIBLE);
                                nameBoxCenter.setVisibility(View.VISIBLE);
                                timeBoxCenter.setVisibility(View.VISIBLE);
                                telephoneBoxCenter.setVisibility(View.VISIBLE);

                                nameBoxCenter.setText(name);
                                timeBoxCenter.setText(telephone);
                                telephoneBoxCenter.setText(timeofwork);
                            } else if (i == status_panel + 2) {
                                boxBottom.setVisibility(View.VISIBLE);
                                avaBoxWhiteBottom.setVisibility(View.VISIBLE);
                                nameBoxBottom.setVisibility(View.VISIBLE);
                                timeBoxBottom.setVisibility(View.VISIBLE);
                                telephoneBoxBottom.setVisibility(View.VISIBLE);

                                nameBoxBottom.setText(name);
                                timeBoxBottom.setText(telephone);
                                telephoneBoxBottom.setText(timeofwork);
                            }
                            panel_exist = true;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (!panel_exist) {
                        goNext.setVisibility(View.VISIBLE);
                        labelNothing.setVisibility(View.VISIBLE);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put ("city", cityText);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }
}
