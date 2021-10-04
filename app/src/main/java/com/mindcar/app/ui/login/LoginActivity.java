package com.mindcar.app.ui.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.mindcar.app.ChooseActivity;
import com.mindcar.app.MenuActivity;
import com.mindcar.app.R;
import com.mindcar.app.RegActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity/* implements LocationListener*/ {

    ImageView logo;
    ImageView comeIn;
    EditText username;
    EditText pass;
    TextView reg;
    ProgressBar loading;

    public String nameOur;

    private static String URL_REGIST = "http://kostya2l.beget.tech/index.php";
    private final static String FILE_LOGIN = "content_login.txt"; //сохраняем данные, чтобы не было повторной авторизации
    private final static String FILE_PASSWORD = "content_password.txt"; //сохраняем данные, чтобы не было повторной авторизации


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        logo = findViewById(R.id.logo);
        reg = findViewById(R.id.registration);
        comeIn = findViewById(R.id.button);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        loading = findViewById(R.id.loading);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");
        reg.setTypeface(custom_font);
        username.setTypeface(custom_font);
        pass.setTypeface(custom_font);

        checkSaveOfAutorization();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegActivity.class));
            }
        });

        comeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if(user.isEmpty() || password.isEmpty())
                   Toast.makeText(getApplicationContext(), "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                else
                    loginCome(password, user);
            }
        });
    }

    // сохранение файла
    public void saveAutorization(){

        FileOutputStream fos = null;
        FileOutputStream fosTwo = null;
        try {
           // EditText textBox = (EditText) findViewById(R.id.save_text);
            String login = username.getText().toString();
            fos = openFileOutput(FILE_LOGIN, MODE_PRIVATE);
            fos.write(login.getBytes());

            String protect = pass.getText().toString();
            fosTwo = openFileOutput(FILE_PASSWORD, MODE_PRIVATE);
            fosTwo.write(protect.getBytes());

            //Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos != null)
                    fos.close();
                if(fosTwo != null)
                    fosTwo.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    // открытие файла
    public void checkSaveOfAutorization(){

        FileInputStream fin = null;
        FileInputStream finTwo = null;
       // TextView textView = (TextView) findViewById(R.id.open_text);
        try {
            fin = openFileInput(FILE_LOGIN);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String login = new String (bytes);
            username.setText(login);

            finTwo = openFileInput(FILE_PASSWORD);
            byte[] bytes2 = new byte[finTwo.available()];
            finTwo.read(bytes2);
            String protect = new String (bytes2);
            pass.setText(protect);

            if(!login.isEmpty() && !protect.isEmpty())
            {
                loginCome(protect, login);
            }
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();

                if(finTwo != null)
                    finTwo.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loginCome(final String password, final String name)
    {
        loading.setVisibility(View.VISIBLE);
        logo.setVisibility(View.GONE);
        reg.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        username.setVisibility(View.GONE);
        comeIn.setVisibility(View.GONE);

        StringRequest request = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if(success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            //String name = object.getString("username").trim();
                            String balance = object.getString("balance").trim();
                            String admin = object.getString("admin").trim();
                            String id = object.getString("id").trim();
                            String city = object.getString("city").trim();
                            nameOur = name;


                           /* while (false) { //yourCity.equals("None")
                                //latitude = gps.getLatitude();
                                //longitude = gps.getLongitude();
                                //yourCity = getCity(latitude, longitude);

                                //loginCome(password, name);
                            }*/
                           // Toast.makeText(getApplicationContext(), "! " + longitude + " " + latitude, Toast.LENGTH_SHORT).show();

                            saveAutorization();

                            Toast.makeText(getApplicationContext(), "Авторизация прошла успешна!", Toast.LENGTH_SHORT).show();

                            if(city.equals("None"))
                            {
                                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("balance", balance);
                                intent.putExtra("admin", admin);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("city", city);
                                intent.putExtra("balance", balance);
                                intent.putExtra("admin", admin);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        }
                    }
                    else if(success.equals("2") || success.equals("3"))
                    {
                        loading.setVisibility(View.GONE);
                        logo.setVisibility(View.VISIBLE);
                        reg.setVisibility(View.VISIBLE);
                        pass.setVisibility(View.VISIBLE);
                        username.setVisibility(View.VISIBLE);
                        comeIn.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), "Логин или пароль неверен" + success, Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException ex)
                {
                    ex.printStackTrace();

                    loading.setVisibility(View.GONE);
                    logo.setVisibility(View.VISIBLE);
                    reg.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    comeIn.setVisibility(View.VISIBLE);

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
                Map <String, String> params = new HashMap <> ();
                params.put ("username", name);
                params.put ("password", password);
                return params;
            }
        };
        RequestQueue stringRequest = Volley.newRequestQueue(this);
        stringRequest.add(request);
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {

            //final TextView yourlat = findViewById(R.id.labelNews);
            //final EditText yourlong = (EditText) findViewById(R.id.yourLong);

            try {
                getLocation(); //this function can change value of mInterval.

                if (locationText.isEmpty()) {
                    //Toast.makeText(getApplicationContext(), "Trying to retrieve coordinates.", Toast.LENGTH_LONG).show();
                }
                else {

                    //yourlat.setText(locationLatitude.toString());
                    //yourlong.setText(locationLongitude.toString());

                    yourlat = getCity(Double.parseDouble(locationLatitude), Double.parseDouble(locationLongitude));

                    Thread.currentThread().interrupt();

                }
            } finally {

                mHandler.postDelayed(mStatusChecker, 200);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, (LocationListener) this);
        }
        GPSTracker
    }

    @Override
    public void onLocationChanged(Location location) {

        locationText = location.getLatitude() + "," + location.getLongitude();
        locationLatitude = location.getLatitude() + "";
        locationLongitude = location.getLongitude() + "";
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LoginActivity.this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }*/
}
