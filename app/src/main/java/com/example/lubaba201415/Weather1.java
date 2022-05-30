package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather1 extends AppCompatActivity {
    public static int mCondition;
    public static String mCityName;
    public static String mDescription;
    public static double mTemp;
    EditText editTextCountry;

    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    // App ID to use OpenWeather data
    final String APP_ID = "96bf76f06d33bd08a65dfc1b092065fb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather1);
    }

    public void goResult(View view) {
        Toast.makeText(getBaseContext(), "Please Wait", Toast.LENGTH_LONG).show();
        view.setEnabled(false);
        editTextCountry = (EditText) findViewById(R.id.editTextCity);
        String tempUrl = "";
        String city = editTextCountry.getText().toString();

        // tempUrl  = WEATHER_URL + "?q" + city + "," + country + "&appid=" + APP_ID;
        tempUrl  = WEATHER_URL + "?q=" + city + "&appid=" + APP_ID;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("responses",response);
                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectweather = jsonArray.getJSONObject(0);
                    String description = jsonObjectweather.getString("description");

                    mDescription = description;
                    int id = jsonObjectweather.getInt("id");
                    mCondition = id;
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                    double temp = jsonObjectMain.getDouble("temp")- 273.15;
                    mTemp = temp;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");

                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String speed = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectclouds = jsonResponse.getJSONObject("clouds");
                    JSONObject jsonObjectsys = jsonResponse.getJSONObject("sys");

                    mCityName = city;
                    Log.i("Ressss",speed);
                    view.setEnabled(true);
                    editTextCountry.setText("");
                    Intent goResultActivity = new Intent(Weather1.this, Weather2.class);
                    startActivity(goResultActivity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("Error",error.toString());
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}