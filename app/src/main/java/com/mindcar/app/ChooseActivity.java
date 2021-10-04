package com.mindcar.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseActivity extends AppCompatActivity {
    ProgressBar loading;
    private TextView labelText;
    private Spinner spinnerCities;
    private ImageView goNext;
    private ImageView spinnerField;

    public String nose;
    private String cityName;

    private int balance_int = 0;
    private int admin = -1;
    private int user_id = -1;

    List<String> itemsCity = new ArrayList<>();
    private static String URL_READ_CITIES = "http://kostya2l.beget.tech/cities.php";
    private static String URL_CITY = "http://kostya2l.beget.tech/update_city.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nose = getIntent().getStringExtra("name");
        balance_int = Integer.parseInt(getIntent().getStringExtra("balance"));
        admin = Integer.parseInt(getIntent().getStringExtra("admin"));
        user_id = Integer.parseInt(getIntent().getStringExtra("id"));

        loading = findViewById(R.id.loading);
        spinnerField = findViewById(R.id.fieldChoose);
        goNext = findViewById(R.id.goNext);
        spinnerCities = findViewById(R.id.spinner);
        labelText = findViewById(R.id.textLabel);

        readCities();

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                goNext.setVisibility(View.GONE);
                spinnerCities.setVisibility(View.GONE);
                spinnerField.setVisibility(View.GONE);
                labelText.setVisibility(View.GONE);

                inputCity();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void inputCity() {
        StringRequest request = new StringRequest(Request.Method.POST, URL_CITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        intent.putExtra("name", nose);
                        intent.putExtra("balance", getIntent().getStringExtra("balance"));
                        intent.putExtra("admin", getIntent().getStringExtra("admin"));
                        intent.putExtra("city", cityName);
                        intent.putExtra("id", getIntent().getStringExtra("id"));
                        startActivity(intent);
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
                params.put ("city", cityName);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }

    private void readCities() {
        StringRequest request = new StringRequest(Request.Method.POST, URL_READ_CITIES, new Response.Listener<String>()
        {
            JSONArray result;

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    for(int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String city = object.getString("city").trim();
                            itemsCity.add(city);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    loading.setVisibility(View.GONE);
                    goNext.setVisibility(View.VISIBLE);
                    spinnerCities.setVisibility(View.VISIBLE);
                    spinnerField.setVisibility(View.VISIBLE);
                    labelText.setVisibility(View.VISIBLE);

                    ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(ChooseActivity.this, android.R.layout.simple_spinner_item, itemsCity);
                    citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCities.setPrompt("Доступные города");
                    spinnerCities.setAdapter(citiesAdapter);

                    spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            cityName = parent.getSelectedItem().toString();
                        }
                    });
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
