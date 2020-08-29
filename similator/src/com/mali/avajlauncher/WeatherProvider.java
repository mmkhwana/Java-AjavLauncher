package com.mali.avajlauncher;

import com.mali.avajlauncher.tower.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String weather[] = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider(){
    }
    public static WeatherProvider getProvider(){
        return WeatherProvider.weatherProvider;

    }

    public String getCurrentWeather(Coordinates coordinates){
        int num = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
        if (num % 4 == 0){
            return weather[0];
        }else if (num % 4 == 1){
            return weather[1];
        }else if (num % 4 == 2){
            return weather[2];
        }
        else if (num % 4 == 3){
            return weather[3];
        }else {
            return null;
        }
        

    }
}
