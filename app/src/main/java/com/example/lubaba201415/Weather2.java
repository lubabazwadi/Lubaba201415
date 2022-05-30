package com.example.lubaba201415;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Weather2 extends AppCompatActivity {
    ImageView WeatherImageValue;
    TextView RcityName,description,temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather2);
        WeatherImageValue = (ImageView) findViewById(R.id.weatherImage);
        RcityName = (TextView) findViewById(R.id.city);
        description = (TextView) findViewById(R.id.description);
        temp = (TextView) findViewById(R.id.temp) ;

        RcityName.setText(Weather1.mCityName);
        description.setText(Weather1.mDescription);
        int roundedValue = (int) Math.rint(Weather1.mTemp);

        temp.setText(roundedValue+" °C");
        int getImageName = updateWeatherIcon(Weather1.mCondition);
        WeatherImageValue.setImageResource(getImageName);
    }

    // Get the weather image name from OpenWeatherMap's condition (marked by a number code)
    private static int updateWeatherIcon(int condition) {
        if (condition == 800)
            return  R.drawable.sunny;
        else if (condition >= 801 && condition <= 804)
            return R.drawable.cloudy;
        return R.drawable.unknown;
    }
}